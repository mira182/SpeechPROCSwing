/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.parameters.intensity;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.base.Throwables;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
import feec.cz.brno.speechproc.gui.api.charts.IIntensityCharts;
import feec.cz.brno.speechproc.gui.api.charts.VisibleAction;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author mira
 */
public class IntensityCharts implements IIntensityCharts {
    
    private static final Logger logger = LogManager.getLogger(IntensityCharts.class);

    private JScrollPane controlPanel;
    private JPanel panel;

    public IntensityCharts() {
        panel = new JPanel(new GridLayout(1, 1));
        controlPanel = new JScrollPane(panel);
    }
    
    @Override
    public List<XYSeries> getSeriesFromFile(File csvFile) {
        logger.debug("Creating data series from " + csvFile.getAbsolutePath());
        
        List<XYSeries> series = new ArrayList<>();
        final XYSeries seriesIntensity = new XYSeries("Intensity of " + csvFile.getName().substring(0, csvFile.getName().lastIndexOf("-")));
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile), ' ');

            // header
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                Double Time = CalcUtilities.getDouble(readNextLine[0]);
                Double intensity = CalcUtilities.getDouble(readNextLine[1]);

                seriesIntensity.add(Time, intensity);
            }
            
            series.add(seriesIntensity);
               
            reader.close();
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create intensity chart.", ex);
            JTextArea ta = new JTextArea(Throwables.getStackTraceAsString(ex), 10, 30);
            ta.setEditable(false);
            JScrollPane scrolPane = new JScrollPane(ta);
            scrolPane.setPreferredSize(new Dimension(700, 240));
            JOptionPane.showMessageDialog(null, scrolPane, "Failure reason.", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            logger.error("Couldn't create intensity chart.", ex);
            JTextArea ta = new JTextArea(Throwables.getStackTraceAsString(ex), 10, 30);
            ta.setEditable(false);
            JScrollPane scrolPane = new JScrollPane(ta);
            scrolPane.setPreferredSize(new Dimension(700, 240));
            JOptionPane.showMessageDialog(null, scrolPane, "Failure reason.", JOptionPane.ERROR_MESSAGE);
        }
        return series;
    }
    
    @Override
    public ChartPanel createChart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        getSeriesFromFile(csvFile).forEach(series -> dataset.addSeries(series));
        
        logger.debug("Creating intensity chart.");
        JFreeChart chart = ChartFactory.createXYLineChart("Intensity", "Time [s]", "Intensity [dB]", dataset, PlotOrientation.VERTICAL, true, true, true);
        
        addControlPanel((XYPlot) chart.getPlot());
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);
        
        return chartPanel;
    }

    @Override
    public ChartPanel createComparedChart(File csvFile1, File csvFile2) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        getSeriesFromFile(csvFile1).forEach(series -> dataset.addSeries(series));
        getSeriesFromFile(csvFile2).forEach(series -> dataset.addSeries(series));

        logger.debug("Creating intensity compared chart.");
        JFreeChart chart = ChartFactory.createXYLineChart("Intensity", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

        addControlPanel((XYPlot) chart.getPlot());
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);
        
        return chartPanel;
    }
    
    private void addControlPanel(XYPlot plot) {
        XYItemRenderer renderer = plot.getRenderer();
        for (int i = 0; i < plot.getDataset().getSeriesCount(); i++) {
            JCheckBox jcb = new JCheckBox(new VisibleAction(renderer, plot.getDataset().getSeriesKey(i).toString(), i));
            jcb.setSelected(true);
            renderer.setSeriesVisible(i, true);
            panel.add(jcb);
        }
    }

    @Override
    public ChartPanel createStatsChart(File csvFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JScrollPane getControlPanel() {
        return controlPanel;
    }
}

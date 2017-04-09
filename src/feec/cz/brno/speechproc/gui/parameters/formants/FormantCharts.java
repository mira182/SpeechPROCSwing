package feec.cz.brno.speechproc.gui.parameters.formants;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
import feec.cz.brno.speechproc.gui.api.charts.Chart;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class FormantCharts implements Chart {
    
    private static final Logger logger = LogManager.getLogger(FormantCharts.class);

    @Override
    public ChartPanel createChart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        getSeriesFromFile(csvFile).forEach(series -> dataset.addSeries(series));
        
        JFreeChart chart = ChartFactory.createScatterPlot("Formants", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

        applySettings(chart);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);

        return chartPanel;
    }

    @Override
    public ChartPanel createComparedChart(File csvFile1, File csvFile2) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        getSeriesFromFile(csvFile1).forEach(series -> dataset.addSeries(series));
        getSeriesFromFile(csvFile2).forEach(series -> dataset.addSeries(series));

        JFreeChart chart = ChartFactory.createScatterPlot("Formants", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

        applySettings(chart);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);

        return chartPanel;
    }
    
    @Override
    public ChartPanel createStatsChart(File csvFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<XYSeries> getSeriesFromFile(File csvFile) {
        logger.debug("Creating data series from " + csvFile.getAbsolutePath());
        List<XYSeries> series = new ArrayList<>();
        // Set up series
        final XYSeries seriesF1 = new XYSeries("Formant 1 of " + csvFile.getName());
        final XYSeries seriesF2 = new XYSeries("Formant 2 of " + csvFile.getName());
        final XYSeries seriesF3 = new XYSeries("Formant 3 of " + csvFile.getName());
        // header
        try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',')) {
            // header
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                Double Time = CalcUtilities.getDouble(readNextLine[0]);

                Double F1 = CalcUtilities.getDouble(readNextLine[3]);
                Double F2 = CalcUtilities.getDouble(readNextLine[5]);
                Double F3 = CalcUtilities.getDouble(readNextLine[7]);

                seriesF1.add(Time, F1);
                seriesF2.add(Time, F2);
                seriesF3.add(Time, F3);
            }

            series.add(seriesF1);
            series.add(seriesF2);
            series.add(seriesF3);
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        }
        return series;
    }

    @Override
    public void applySettings(JFreeChart chart) {
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = xyPlot.getRenderer();
        Ellipse2D.Double circle = new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0);
        for (int seriesIndex = 0; seriesIndex < chart.getXYPlot().getSeriesCount(); seriesIndex++) {
            renderer.setSeriesShape(seriesIndex, circle);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.parameters.intensity;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
import feec.cz.brno.speechproc.gui.api.charts.Chart;
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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author mira
 */
public class IntensityCharts implements Chart {
    
    private static final Logger logger = LogManager.getLogger(IntensityCharts.class);

    @Override
    public List<XYSeries> getSeriesFromFile(File csvFile) {
        logger.debug("Creating data series from " + csvFile.getAbsolutePath());
        
        List<XYSeries> series = new ArrayList<>();
        final XYSeries seriesIntensity = new XYSeries("Intensity of " + csvFile.getName());
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
        } catch (IOException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        }
        return series;
    }
    
    @Override
    public ChartPanel createChart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        getSeriesFromFile(csvFile).forEach(series -> dataset.addSeries(series));
        
        JFreeChart chart = ChartFactory.createXYLineChart("Intensity", "Time [s]", "Intensity [dB]", dataset, PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);
        
        return chartPanel;
    }

    @Override
    public ChartPanel createComparedChart(File csvFile1, File csvFile2) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        getSeriesFromFile(csvFile1).forEach(series -> dataset.addSeries(series));
        getSeriesFromFile(csvFile2).forEach(series -> dataset.addSeries(series));

        JFreeChart chart = ChartFactory.createXYLineChart("Intensity", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);
        
        return chartPanel;
    }

    @Override
    public ChartPanel createStatsChart(File csvFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applySettings(JFreeChart chart) {
    }
}

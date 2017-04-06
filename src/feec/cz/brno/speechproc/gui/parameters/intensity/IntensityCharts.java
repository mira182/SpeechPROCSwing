/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.parameters.intensity;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import feec.cz.brno.speechproc.gui.api.charts.CompareChart;

/**
 *
 * @author mira
 */
public class IntensityCharts implements CompareChart {
    
    private static final Logger logger = LogManager.getLogger(IntensityCharts.class);

    @Override
    public ChartPanel createChart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader = null;
        ChartPanel chartPanel = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ' ');
            // Set up series
            final XYSeries seriesIntensity = new XYSeries("Intensity");
            double intensity = 0;

            // header
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                double Time = CalcUtilities.getDouble(readNextLine[0]);
                intensity = CalcUtilities.getDouble(readNextLine[1]);

                seriesIntensity.add(Time, intensity);
            }
            dataset.addSeries(seriesIntensity);
            
            JFreeChart chart = ChartFactory.createXYLineChart("Intensity", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);
            
            chartPanel = new ChartPanel(chart);
            
            reader.close();
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        }
        return chartPanel;
    }

    @Override
    public ChartPanel createComparedChart(File csvFile1, File csvFile2) {
        ChartPanel chartPanel = null;
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader1 = null;
        CSVReader reader2 = null;
        try {
            reader1 = new CSVReader(new FileReader(csvFile1), ' ');
            reader2 = new CSVReader(new FileReader(csvFile2), ' ');
            
            final XYSeries seriesIntensity1 = new XYSeries("Intensity of " + csvFile1.getName());
            final XYSeries seriesIntensity2 = new XYSeries("Intensity of " + csvFile2.getName());
            double intensity = 0;

            // header
            String[] readNextLine = reader1.readNext();
            while ((readNextLine = reader1.readNext()) != null) {
                // add values to dataset
                double Time = CalcUtilities.getDouble(readNextLine[0]);
                intensity = CalcUtilities.getDouble(readNextLine[1]);

                seriesIntensity1.add(Time, intensity);
            }
            dataset.addSeries(seriesIntensity1);
            
            readNextLine = reader2.readNext();
            while ((readNextLine = reader2.readNext()) != null) {
                // add values to dataset
                double Time = CalcUtilities.getDouble(readNextLine[0]);
                intensity = CalcUtilities.getDouble(readNextLine[1]);

                seriesIntensity2.add(Time, intensity);
            }
            dataset.addSeries(seriesIntensity2);
            

            JFreeChart chart = ChartFactory.createXYLineChart("Intensity", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

            chartPanel = new ChartPanel(chart);

            reader1.close();
            reader2.close();
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        }
        return chartPanel;
    }

    @Override
    public ChartPanel createStatsChart(File csvFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

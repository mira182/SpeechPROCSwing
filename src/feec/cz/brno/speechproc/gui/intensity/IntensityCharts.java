/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.intensity;

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

/**
 *
 * @author mira
 */
public class IntensityCharts {
    
    private static final Logger logger = LogManager.getLogger(IntensityCharts.class);

    public ChartPanel createIntensityChart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader = null;
        ChartPanel chartPanel = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ' ');

            // Set up series
//            final XYSeries seriesIntensity = new XYSeries("Intensity");
            final XYSeries seriesIntensity = new XYSeries("Intensity");

            double intensity = 0;

            // header
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                double Time = CalcUtilities.getDouble(readNextLine[0]);

                seriesIntensity.add(Time, intensity);
            }

            dataset.addSeries(seriesIntensity);

            JFreeChart chart = ChartFactory.createXYLineChart("F0 pitch", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

            chartPanel = new ChartPanel(chart);
            reader.close();
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create intensity chart.", ex);
        }
        return chartPanel;
    }
}

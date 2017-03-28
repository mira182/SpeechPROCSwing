/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.f0;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
import feec.cz.brno.speechproc.gui.formants.FormantCharts;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
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
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author mira
 */
public class F0PitchCharts {
    
    private static final Logger logger = LogManager.getLogger(FormantCharts.class);

    public ChartPanel createF0Chart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader = null;
        ChartPanel chartPanel = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ' ');

            // Set up series
//            final XYSeries seriesIntensity = new XYSeries("Intensity");
            final XYSeries seriesF0min = new XYSeries("F0 min");
            final XYSeries seriesF0max = new XYSeries("F0 max");
            final XYSeries seriesF0mean = new XYSeries("mean F0");
            final XYSeries seriesF0stdev = new XYSeries("F0 stdev");
            final XYSeries seriesF0vr = new XYSeries("F0 VR");

            double F0min = 0;
            double F0max = 0;
            double F0mean = 0;
            double F0stdev = 0;
            double F0vr = 0;

            // header
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                double Time = CalcUtilities.getDouble(readNextLine[0]);
                F0mean = CalcUtilities.getDouble(readNextLine[2]);
                F0min = CalcUtilities.getDouble(readNextLine[3]);
                F0max = CalcUtilities.getDouble(readNextLine[4]);
                F0stdev = CalcUtilities.getDouble(readNextLine[5]);
                F0vr = CalcUtilities.getDouble(readNextLine[6]);

                seriesF0min.add(Time, F0min);
                seriesF0max.add(Time, F0max);
                seriesF0mean.add(Time, F0mean);
                seriesF0stdev.add(Time, F0stdev);
                seriesF0vr.add(Time, F0vr);
            }

            dataset.addSeries(seriesF0min);
            dataset.addSeries(seriesF0max);
            dataset.addSeries(seriesF0mean);
            dataset.addSeries(seriesF0stdev);
            dataset.addSeries(seriesF0vr);

            JFreeChart chart = ChartFactory.createScatterPlot("F0 pitch", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true) ;

            XYPlot xyPlot = (XYPlot) chart.getPlot();
            XYItemRenderer renderer = xyPlot.getRenderer();
            Ellipse2D.Double circle = new Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0);
            renderer.setSeriesShape(0, circle);
            renderer.setSeriesShape(1, circle);
            renderer.setSeriesShape(2, circle);
            renderer.setSeriesShape(3, circle);
            renderer.setSeriesPaint(1, Color.BLUE);
            renderer.setSeriesPaint(2, Color.RED);
            renderer.setSeriesPaint(3, Color.YELLOW);

            chartPanel = new ChartPanel(chart);
            reader.close();
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create formants chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create formants chart.", ex);
        }
        return chartPanel;
    }
}

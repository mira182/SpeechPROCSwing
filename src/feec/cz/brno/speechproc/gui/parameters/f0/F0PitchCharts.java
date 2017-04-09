/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.parameters.f0;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
import feec.cz.brno.speechproc.gui.api.charts.Chart;
import feec.cz.brno.speechproc.gui.parameters.formants.FormantCharts;
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
public class F0PitchCharts implements Chart {
    
    private static final Logger logger = LogManager.getLogger(FormantCharts.class);

    @Override
    public ChartPanel createChart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        getSeriesFromFile(csvFile).forEach(series -> dataset.addSeries(series));

        JFreeChart chart = ChartFactory.createXYLineChart("F0 pitch", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

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

        JFreeChart chart = ChartFactory.createXYLineChart("F0 pitch", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

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
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile), ' ');

            // Set up series
            final XYSeries seriesF0min = new XYSeries("F0 min of " + csvFile.getName());
            final XYSeries seriesF0max = new XYSeries("F0 max of " + csvFile.getName());
            final XYSeries seriesF0mean = new XYSeries("mean F0 of " + csvFile.getName());
            final XYSeries seriesF0stdev = new XYSeries("F0 stdev of " + csvFile.getName());
            final XYSeries seriesF0vr = new XYSeries("F0 VR of " + csvFile.getName());

            // header
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                Double Time = CalcUtilities.getDouble(readNextLine[0]);
                Double F0mean = CalcUtilities.getDouble(readNextLine[2]);
                Double F0min = CalcUtilities.getDouble(readNextLine[3]);
                Double F0max = CalcUtilities.getDouble(readNextLine[4]);
                Double F0stdev = CalcUtilities.getDouble(readNextLine[5]);
                Double F0vr = CalcUtilities.getDouble(readNextLine[6]);

                seriesF0min.add(Time, F0min);
                seriesF0max.add(Time, F0max);
                seriesF0mean.add(Time, F0mean);
                seriesF0stdev.add(Time, F0stdev);
                seriesF0vr.add(Time, F0vr);
            }

            series.add(seriesF0min);
            series.add(seriesF0max);
            series.add(seriesF0mean);
            series.add(seriesF0stdev);
            series.add(seriesF0vr);
            
            reader.close();
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create pitch chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create pitch chart.", ex);
        }
        
        return series;
    }

    @Override
    public void applySettings(JFreeChart chart) {
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = xyPlot.getRenderer();
        Ellipse2D.Double circle = new Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0);
        for (int serieIndex = 0; serieIndex < chart.getXYPlot().getSeriesCount(); serieIndex++) {
            renderer.setSeriesShape(serieIndex, circle);
        }
    }
}

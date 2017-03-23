package feec.cz.brno.speechproc.visualize.formants.chart;

import au.com.bytecode.opencsv.CSVReader;
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
public class FormantCharts {
    
    private static final Logger logger = LogManager.getLogger(FormantCharts.class);

    public ChartPanel createFormantChart(File csvFile, boolean formant2, boolean formant3, boolean formant4) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader;
        ChartPanel chartPanel = null;
        List<Double> value = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile), ',');
            
            // Set up series
//            final XYSeries seriesIntensity = new XYSeries("Intensity");
            final XYSeries seriesF1 = new XYSeries("Formant 1");
            final XYSeries seriesF2 = new XYSeries("Formant 2");
            final XYSeries seriesF3 = new XYSeries("Formant 3");
            final XYSeries seriesF4 = new XYSeries("Formant 4");
            
            double F1;
            double F2 = 0;
            double F3 = 0;
            double F4 = 0;
      
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                double Time = getDouble(readNextLine[0]);
//                double intensity = Double.valueOf(readNextLine[1]);
                F1 = getDouble(readNextLine[3]);
                value.add(F1);
                if (formant2) F2 = getDouble(readNextLine[5]);
                if (formant3) F3 = getDouble(readNextLine[7]);
                if (formant4) F4 = getDouble(readNextLine[9]);
                
//                seriesIntensity.add(Time, intensity);

                seriesF1.add(Time, F1);
                if (formant2) seriesF2.add(Time, F2);
                if (formant3) seriesF2.add(Time, F3);
                if (formant4) seriesF2.add(Time, F4);
            }

//            dataset.addSeries(seriesIntensity);
            dataset.addSeries(seriesF1);
            if (formant2) dataset.addSeries(seriesF2);
            if (formant3) dataset.addSeries(seriesF3);
            if (formant4) dataset.addSeries(seriesF4);

//            JFreeChart chart = ChartFactory.createXYLineChart("Title", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.HORIZONTAL, true, true, true);
            JFreeChart chart = ChartFactory.createScatterPlot("Formants", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

            XYPlot xyPlot = (XYPlot) chart.getPlot();
            XYItemRenderer renderer = xyPlot.getRenderer();
            
            Ellipse2D.Double circle = new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0);
            renderer.setSeriesShape(0, circle);
            renderer.setSeriesShape(1, circle);
            renderer.setSeriesShape(2, circle);
            renderer.setSeriesShape(3, circle);
            renderer.setSeriesShape(4, circle);

            chartPanel = new ChartPanel(chart);

        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create formants chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create formants chart.", ex);
        }
        return chartPanel;
    }
 
    private double getDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
}

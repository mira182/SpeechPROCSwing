package feec.cz.brno.speechproc.visualize;

import au.com.bytecode.opencsv.CSVReader;
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
public class FormantCharts {
    
    private static final Logger logger = LogManager.getLogger(FormantCharts.class);

    public ChartPanel createFormantChart(File csvFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader;
        ChartPanel chartPanel = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ',');
            
            // Set up series
//            final XYSeries seriesIntensity = new XYSeries("Intensity");
            final XYSeries seriesF1 = new XYSeries("F1");
            final XYSeries seriesF2 = new XYSeries("F2");
            final XYSeries seriesF3 = new XYSeries("F3");
            final XYSeries seriesF4 = new XYSeries("F4");
      
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                double Time = Double.valueOf(readNextLine[0]);
//                double intensity = Double.valueOf(readNextLine[1]);
                double F1 = Double.valueOf(readNextLine[3]);
                double F2 = Double.valueOf(readNextLine[5]);
                double F3 = Double.valueOf(readNextLine[7]);
                double F4 = Double.valueOf(readNextLine[9]);
                
//                seriesIntensity.add(Time, intensity);

                seriesF1.add(Time, F1);
                seriesF2.add(Time, F2);
                seriesF2.add(Time, F3);
                seriesF2.add(Time, F4);
            }

//            dataset.addSeries(seriesIntensity);
            dataset.addSeries(seriesF1);
            dataset.addSeries(seriesF2);
            dataset.addSeries(seriesF3);
            dataset.addSeries(seriesF4);

//            JFreeChart chart = ChartFactory.createXYLineChart("Title", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.HORIZONTAL, true, true, true);
            JFreeChart chart = ChartFactory.createScatterPlot("Title", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.HORIZONTAL, true, true, true);

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

}

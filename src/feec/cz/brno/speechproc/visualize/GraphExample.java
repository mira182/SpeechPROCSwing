package feec.cz.brno.speechproc.visualize;

import au.com.bytecode.opencsv.CSVReader;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GraphExample {

    private File csvFile;

    public GraphExample(File csvFile) {
        this.csvFile = csvFile;
    }

    public ChartPanel visualize() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader;
        ChartPanel chartPanel = null;
        try {
            //        ChartFactory.createLineChart("Title", categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.HORIZONTAL, true, true, true)

            reader = new CSVReader(new FileReader(csvFile), ',');
            String[] readNextLine = reader.readNext();

            // Set up series
            final XYSeries seriesIntensity = new XYSeries("Intensity");
            final XYSeries seriesF1 = new XYSeries("F1");
            final XYSeries seriesF2 = new XYSeries("F2");

            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                double Time = Double.valueOf(readNextLine[0]);
//                double intensity = Double.valueOf(readNextLine[1]);
                double F1 = Double.valueOf(readNextLine[3]);
                double F2 = Double.valueOf(readNextLine[5]);
//                seriesIntensity.add(Time, intensity);
                seriesF1.add(Time, F1);
                seriesF2.add(Time, F2);
            }

//            dataset.addSeries(seriesIntensity);
            dataset.addSeries(seriesF1);
            dataset.addSeries(seriesF2);

//            JFreeChart chart = ChartFactory.createXYLineChart("Title", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.HORIZONTAL, true, true, true);
            JFreeChart chart = ChartFactory.createScatterPlot("Title", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.HORIZONTAL, true, true, true);

            XYPlot xyPlot = (XYPlot) chart.getPlot();
            XYItemRenderer renderer = xyPlot.getRenderer();
            renderer.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
            renderer.setSeriesShape(1, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));

            chartPanel = new ChartPanel(chart);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GraphExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chartPanel;
    }

}

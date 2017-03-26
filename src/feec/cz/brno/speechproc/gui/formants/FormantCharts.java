package feec.cz.brno.speechproc.gui.formants;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
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

    public ChartPanel createFormantChart(File csvFile, boolean formant2, boolean formant3) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        CSVReader reader = null;
        ChartPanel chartPanel = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ',');
            
            // Set up series
//            final XYSeries seriesIntensity = new XYSeries("Intensity");
            final XYSeries seriesF1 = new XYSeries("Formant 1");
            final XYSeries seriesF2 = new XYSeries("Formant 2");
            final XYSeries seriesF3 = new XYSeries("Formant 3");
            
            double F1;
            double F2 = 0;
            double F3 = 0;
      
            // header
            String[] readNextLine = reader.readNext();
            while ((readNextLine = reader.readNext()) != null) {
                // add values to dataset
                double Time = CalcUtilities.getDouble(readNextLine[0]);
//                double intensity = Double.valueOf(readNextLine[1]);
                F1 = CalcUtilities.getDouble(readNextLine[3]);
                if (formant2) F2 = CalcUtilities.getDouble(readNextLine[5]);
                if (formant3) F3 = CalcUtilities.getDouble(readNextLine[7]);
                
//                seriesIntensity.add(Time, intensity);

                seriesF1.add(Time, F1);
                if (formant2) seriesF2.add(Time, F2);
                if (formant3) seriesF2.add(Time, F3);
            }

//            dataset.addSeries(seriesIntensity);
            dataset.addSeries(seriesF1);
            if (formant2) dataset.addSeries(seriesF2);
            if (formant3) dataset.addSeries(seriesF3);

            JFreeChart chart = ChartFactory.createScatterPlot("Formants", "Time [s]", "Frequency [Hz]", dataset, PlotOrientation.VERTICAL, true, true, true);

            XYPlot xyPlot = (XYPlot) chart.getPlot();
            XYItemRenderer renderer = xyPlot.getRenderer();
            Ellipse2D.Double circle = new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0);
            renderer.setSeriesShape(0, circle);
            renderer.setSeriesShape(1, circle);
            renderer.setSeriesShape(2, circle);

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

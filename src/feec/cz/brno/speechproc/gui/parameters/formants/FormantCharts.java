package feec.cz.brno.speechproc.gui.parameters.formants;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.calc.utility.CalcUtilities;
import feec.cz.brno.speechproc.gui.api.charts.IFormantCharts;
import feec.cz.brno.speechproc.gui.api.charts.LegendXYItemLabelGenerator;
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
public class FormantCharts implements IFormantCharts {
    
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

    @Override
    public ChartPanel createVowelSpaceAreaChart(File aVowel, File eVowel, File iVowel, File oVowel, File uVowel) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries vowelASeries = new XYSeries("Vowel A");
        XYSeries vowelESeries = new XYSeries("Vowel E");
        XYSeries vowelISeries = new XYSeries("Vowel I");
        XYSeries vowelOSeries = new XYSeries("Vowel O");
        XYSeries vowelUSeries = new XYSeries("Vowel U");
        
        vowelASeries.add(CalcUtilities.mean(getFormantValues(aVowel, 1)), CalcUtilities.mean(getFormantValues(aVowel, 2)));
        vowelESeries.add(CalcUtilities.mean(getFormantValues(eVowel, 1)), CalcUtilities.mean(getFormantValues(eVowel, 2)));
        vowelISeries.add(CalcUtilities.mean(getFormantValues(iVowel, 1)), CalcUtilities.mean(getFormantValues(iVowel, 2)));
        vowelOSeries.add(CalcUtilities.mean(getFormantValues(oVowel, 1)), CalcUtilities.mean(getFormantValues(oVowel, 2)));
        vowelUSeries.add(CalcUtilities.mean(getFormantValues(uVowel, 1)), CalcUtilities.mean(getFormantValues(uVowel, 2)));
        
        dataset.addSeries(vowelASeries);
        dataset.addSeries(vowelESeries);
        dataset.addSeries(vowelISeries);
        dataset.addSeries(vowelOSeries);
        dataset.addSeries(vowelUSeries);

        JFreeChart chart = ChartFactory.createScatterPlot("Formants", "Formant 1 [Hz]", "Formant 2 [Hz]", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        chart.getXYPlot().getDomainAxis().setInverted(true);
        chart.getXYPlot().getRangeAxis().setInverted(true);
        
        applySettings(chart);
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setBaseItemLabelGenerator(new LegendXYItemLabelGenerator(xyPlot.getLegendItems()));
        renderer.setBaseItemLabelsVisible(true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);

        return chartPanel;
    }

    @Override
    public List<Double> getFormantValues(File csvFile, int formant) {
        List<Double> values = new ArrayList<>();
        // header
        try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',')) {
            // header
            String[] readNextLine = reader.readNext();
            Double formantValue = 0.0;
            while ((readNextLine = reader.readNext()) != null) {
                switch (formant) {
                    case 1:
                        formantValue = CalcUtilities.getDouble(readNextLine[3]);
                        break;
                    case 2:
                        formantValue = CalcUtilities.getDouble(readNextLine[5]);
                        break;
                    case 3:
                        formantValue = CalcUtilities.getDouble(readNextLine[7]);
                        break;
                }
                values.add(formantValue);
            }
        } catch (FileNotFoundException ex) {
            logger.error("Couldn't create vowel space chart.", ex);
        } catch (IOException ex) {
            logger.error("Couldn't create vowel space chart.", ex);
        }
        return values;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.api.charts;

import java.io.File;
import java.util.List;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author hynstm
 */
public interface IChart {
    
    List<XYSeries> getSeriesFromFile(File csvFile);
    
    void applySettings(JFreeChart chart);

    ChartPanel createChart(File csvFile);
    
    ChartPanel createStatsChart(File csvFile);
    
    ChartPanel createComparedChart(File csvFile1, File csvFile2);
}

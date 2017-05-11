/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.api.charts;

import java.io.File;
import java.util.List;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;

/**
 * Common interface for creating charts.
 * @author hynstm
 */
public interface IChart {
    
    List<XYSeries> getSeriesFromFile(File csvFile);
    
    JScrollPane getControlPanel();

    ChartPanel createChart(File csvFile);
    
    ChartPanel createStatsChart(File csvFile);
    
    ChartPanel createComparedChart(File csvFile1, File csvFile2);
}

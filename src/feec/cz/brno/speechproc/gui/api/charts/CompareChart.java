/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.api.charts;

import java.io.File;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author hynstm
 */
public interface CompareChart {

    ChartPanel createChart(File csvFile);
    
    ChartPanel createStatsChart(File csvFile);
    
    ChartPanel createComparedChart(File csvFile1, File csvFile2);
}

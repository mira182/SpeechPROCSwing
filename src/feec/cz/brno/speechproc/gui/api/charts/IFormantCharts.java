/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.api.charts;

import java.io.File;
import java.util.List;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author mira
 */
public interface IFormantCharts extends IChart {
    
    List<Double> getFormantValues(File csvFile, int formant);
    
    ChartPanel createVowelSpaceAreaChart(File csvVowel1, File csvVowel2, File csvVowel3, File csvVowel4, File csvVowel5);
    
}

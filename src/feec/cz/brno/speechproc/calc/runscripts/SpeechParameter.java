/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts;

import java.io.File;

import static feec.cz.brno.speechproc.main.SpeechProc.FS;
import static feec.cz.brno.speechproc.main.SpeechProc.JAR_FOLDER_PATH;

/**
 * Interface containing constants related to speech parameters.
 * @author mira
 */
public interface SpeechParameter {
    
    public static final String OUTPUT_FILE_PARAM = "outputFile";
    public static final String OUTPUT_FILE_STATS_PARAM = "outputStatsFileName";
    public static final String OUTPUT_FOLDER_NAME = "output";
    
    /**
     * Output folder of calculated values (csv files)
     */
    public static final File OUTPUT_FOLDER = new File(JAR_FOLDER_PATH + FS + OUTPUT_FOLDER_NAME);
    
    // TODO might be deleted
    public static final String MEAN_PARAM = "mean";
    public static final String MEDIAN_PARAM = "median";
    public static final String STDEV_PARAM = "stdev";
    public static final String MIN_PARAM = "min";
    public static final String MAX_PARAM = "max";
    public static final String JITTER_PARAM = "jitter";
    public static final String SHIMMER_PARAM = "shimmer";
}

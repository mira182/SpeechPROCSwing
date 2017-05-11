/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.swingworkers.intensity;

import feec.cz.brno.speechproc.calc.runscripts.SpeechParameter;
import java.io.File;

import static feec.cz.brno.speechproc.main.SpeechProc.FS;
import static feec.cz.brno.speechproc.main.SpeechProc.JAR_FOLDER_PATH;

/**
 * Interface contains constants for intensity calculation.
 * @author mira
 */
public interface IIntensity extends SpeechParameter {
    
    public static final String SCRIPT_FILE_RELATIVE_TO_JAR = "/praat/intensity.praat";
    
    public static final File OUTPUT_FOLDER_INTENSITY = new File(JAR_FOLDER_PATH + FS + OUTPUT_FOLDER_NAME + FS + "intensity");
    public static final File SCRIPT_FILE_INTENSITY = new File(JAR_FOLDER_PATH + FS + SCRIPT_FILE_RELATIVE_TO_JAR);
}

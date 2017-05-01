/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.swingworkers.f0;

import feec.cz.brno.speechproc.calc.runscripts.SpeechParameter;
import java.io.File;

import static feec.cz.brno.speechproc.main.SpeechProc.FS;
import static feec.cz.brno.speechproc.main.SpeechProc.JAR_FOLDER_PATH;

/**
 *
 * @author mira
 */
public interface IF0 extends SpeechParameter {
    
    public static final String SCRIPT_FILE_RELATIVE_TO_JAR = "/praat/F0.praat";
    
    public static final File OUTPUT_FOLDER_F0 = new File(JAR_FOLDER_PATH + FS + OUTPUT_FOLDER_NAME + FS + "f0");
    public static final File SCRIPT_FILE_F0 = new File(JAR_FOLDER_PATH + FS + SCRIPT_FILE_RELATIVE_TO_JAR);
    
}

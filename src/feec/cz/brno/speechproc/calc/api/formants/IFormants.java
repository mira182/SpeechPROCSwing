/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.params.SpeechParameter;
import java.io.File;

import static feec.cz.brno.speechproc.main.SpeechProc.FS;
import static feec.cz.brno.speechproc.main.SpeechProc.USER_DIR;

/**
 *
 * @author mira
 */
public interface IFormants extends SpeechParameter {
    
    public static final int COLUMN_FORMANT_1 = 3;
    public static final int COLUMN_FORMANT_2 = 5;
    public static final int COLUMN_FORMANT_3 = 7;
    
    public static final double MAXIMUX_FORMANTS = 3.0;
    
    public static final File OUTPUT_FOLDER_FORMANTS = new File(USER_DIR + FS + "tmpFiles" + FS + "formants" + FS);
}

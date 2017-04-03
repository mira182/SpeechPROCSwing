/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.f0;

import feec.cz.brno.speechproc.calc.api.SpeechParameter;
import java.io.File;

import static feec.cz.brno.speechproc.main.SpeechProc.FS;
import static feec.cz.brno.speechproc.main.SpeechProc.USER_DIR;

/**
 *
 * @author mira
 */
public interface IF0 extends SpeechParameter {
    
    public static final File OUTPUT_FOLDER_F0 = new File(USER_DIR + FS + "tmpFiles" + FS + "f0");
    
}

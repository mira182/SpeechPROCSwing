/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mira
 */
public interface IFormants {
    
    int COLUMN_FORMANT_1 = 3;
    int COLUMN_FORMANT_2 = 5;
    int COLUMN_FORMANT_3 = 7;
    
    double MAXIMUX_FORMANTS = 3.0;
    
    void formantListings(ScriptParameters parameters) throws IOException, InterruptedException, ScriptRunException;
}

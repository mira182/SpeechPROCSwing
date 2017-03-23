/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author mira
 */
public interface IFormants {
    
    String OUTPUT_FILE_PARAM = "outputFile";
    
    File formantListings(ScriptParameters parameters) throws IOException, InterruptedException, ScriptRunException;
    
    void formant1(List<File> soundFiles, List<ScriptParameter> parameters);
    
    void formant2(List<File> soundFiles, List<ScriptParameter> parameters);
    
    void formant3(List<File> soundFiles, List<ScriptParameter> parameters);
}

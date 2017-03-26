/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.f0;

import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import java.io.File;
import java.io.IOException;

import static feec.cz.brno.speechproc.calc.api.formants.IFormants.OUTPUT_FILE_PARAM;

/**
 *
 * @author mira
 */
public class IF0Impl implements IF0 {

    @Override
    public File f0Pitch(ScriptParameters parameters) throws IOException, InterruptedException, ScriptRunException {
        PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/F0.praat").getFile()), parameters);
        praat.runScript();

        return new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
    }
    
}

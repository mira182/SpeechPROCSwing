/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
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
public class FormantsImpl implements IFormants {

    @Override
    public File formantListings(ScriptParameters parameters) throws IOException, InterruptedException, ScriptRunException {
        PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/formants.praat").getFile()), parameters);
        praat.runScript();
        
        return new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
    }

    @Override
    public void formant1(List<File> soundFiles, List<ScriptParameter> parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void formant2(List<File> soundFiles, List<ScriptParameter> parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void formant3(List<File> soundFiles, List<ScriptParameter> parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

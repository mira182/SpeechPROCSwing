/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameter;
import java.io.File;
import java.util.List;

/**
 *
 * @author mira
 */
public class FormantsImpl implements IFormants {

    @Override
    public void formantListings(List<File> soundFiles, List<ScriptParameter> parameters) {
        for (File soundFile : soundFiles) {
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            
            PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/formants.praat").getFile()), parameters);
            praat.runScript();
        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mira
 */
public class FormantsImpl implements IFormants {

    @Override
    public void formantListings(ScriptParameters parameters) throws IOException, InterruptedException, ScriptRunException {
        PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/formants.praat").getFile()), parameters);
        praat.runScript();
    }
}

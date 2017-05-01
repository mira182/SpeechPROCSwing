/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts;

import feec.cz.brno.speechproc.calc.runscripts.scriptparams.ScriptParameters;
import feec.cz.brno.speechproc.gui.settings.Settings;
import java.io.File;


/**
 *
 * @author mira
 */
public class OctaveScript extends ScriptRunnerAbstract {
    
    private final File octaveScript;
    private final ScriptParameters scriptParameters;

    public OctaveScript(File octaveScript, ScriptParameters scriptParameters) {
        this.octaveScript = octaveScript;
        this.scriptParameters = scriptParameters;
    }

    @Override
    public String buildCommand() {
        return String.format(Settings.getInstance().getOctavePath() + OCTAVE_COMMAND, octaveScript.getAbsolutePath());
    }
    
}

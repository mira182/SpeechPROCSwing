/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts;

import feec.cz.brno.speechproc.calc.runscripts.scriptparams.ScriptParameters;
import java.io.File;

/**
 *
 * @author mira
 */
public class MatlabScript extends ScriptRunnerAbstract {
    
    private final File matlabScript;
    private final ScriptParameters scriptParameters;

    public MatlabScript(File matlabScript, ScriptParameters scriptParameters) {
        this.matlabScript = matlabScript;
        this.scriptParameters = scriptParameters;
    }

    @Override
    public String buildCommand() {
        return String.format(MATLAB_COMMAND, matlabScript.getAbsolutePath());        
    }
    
}

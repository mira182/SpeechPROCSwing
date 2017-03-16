/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.runscript;

/**
 *
 * @author mira
 */
public class OctaveScript extends ScriptRunnerAbstract {
    
    private String scriptName;

    
    
    @Override
    public String buildCommand() {
        return ScriptRunnerAbstract.OCTAVE_COMMAND;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts;

import java.io.IOException;

/**
 *
 * @author mira
 */
public interface ScriptRunner {
    
    void runScript() throws IOException, InterruptedException, ScriptRunException;
}

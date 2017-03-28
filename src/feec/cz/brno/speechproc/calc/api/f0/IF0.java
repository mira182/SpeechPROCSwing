/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.f0;

import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mira
 */
public interface IF0 {
    
    void f0Pitch(ScriptParameters parameters) throws IOException, InterruptedException, ScriptRunException;
}

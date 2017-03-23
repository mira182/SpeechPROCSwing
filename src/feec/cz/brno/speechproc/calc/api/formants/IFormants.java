/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameter;
import java.io.File;
import java.util.List;

/**
 *
 * @author mira
 */
public interface IFormants {
    
    void formantListings(List<File> soundFiles, List<ScriptParameter> parameters);
    
    void formant1(List<File> soundFiles, List<ScriptParameter> parameters);
    
    void formant2(List<File> soundFiles, List<ScriptParameter> parameters);
    
    void formant3(List<File> soundFiles, List<ScriptParameter> parameters);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts;

/**
 * Custom exception for script run failure.
 * 
 * @author hynstm
 */
public class ScriptRunException extends Exception {

    public ScriptRunException(String string) {
        super(string);
    }
    
}

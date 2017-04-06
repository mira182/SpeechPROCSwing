/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts.scriptparams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author hynstm
 */
public class ScriptParameters implements Iterable<ScriptParameter> {
    private List<ScriptParameter> parameters = new ArrayList<>();
    
    public void add(ScriptParameter param) {
        parameters.add(param);
    }
    
    public ScriptParameter getParameter(String name) {
        return parameters.stream()
                .filter(param -> param.getParameterName().equals(name))
                .findAny()
                .orElse(null);
    }
    
    public Object getParameterValue(String name) {
        return parameters.stream()
                .filter(param -> param.getParameterName().equals(name))
                .findAny()
                .orElse(null).getValue();
    }

    @Override
    public Iterator<ScriptParameter> iterator() {
        return parameters.iterator();
    }
}

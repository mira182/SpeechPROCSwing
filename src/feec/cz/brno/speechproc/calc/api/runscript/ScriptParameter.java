/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.runscript;

/**
 *
 * @author hynstm
 */
public class ScriptParameter<T> {
    private String paramName;
    private T paramValue;

    public ScriptParameter(String paramName, T paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public T getParamValue() {
        return paramValue;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setParamValue(T paramValue) {
        this.paramValue = paramValue;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.params;

import java.io.File;

/**
 *
 * @author hynstm
 */
public class ScriptResult {
    
    private File soundFile;
    private ResultStatus status;
    private ResultCategory category;

    public ScriptResult(File soundFile, ResultStatus status) {
        this.soundFile = soundFile;
        this.status = status;
    }

    public File getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(File soundFile) {
        this.soundFile = soundFile;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public ResultCategory getCategory() {
        return category;
    }

    public void setCategory(ResultCategory category) {
        this.category = category;
    }
}

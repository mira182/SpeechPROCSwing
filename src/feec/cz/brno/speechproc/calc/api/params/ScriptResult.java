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
    private File csvResult;
    private File csvStatsResult;
    private ScriptParameters additionalParams;
    
    public ScriptResult(File soundFile, ResultStatus status, ResultCategory category) {
        this(soundFile, status, category, null, null, null);
    }

    public ScriptResult(File soundFile, ResultStatus status, ResultCategory category, File csvResult) {
        this(soundFile, status, category, csvResult, null, null);
    }
    
    public ScriptResult(File soundFile, ResultStatus status, ResultCategory category, File csvResult, File csvStatsFile) {
        this(soundFile, status, category, csvResult, csvStatsFile, null);
    }

    public ScriptResult(File soundFile, ResultStatus status, ResultCategory category, File csvResult, ScriptParameters additionalParams) {
        this(soundFile, status, category, csvResult, null, additionalParams);
    }

    public ScriptResult(File soundFile, ResultStatus status, ResultCategory category, File csvResult, File csvStatsResult, ScriptParameters additionalParams) {
        this.soundFile = soundFile;
        this.status = status;
        this.category = category;
        this.csvResult = csvResult;
        this.csvStatsResult = csvStatsResult;
        this.additionalParams = additionalParams;
    }

    public ScriptResult(ResultStatus resultStatus, ResultCategory resultCategory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public File getCsvResult() {
        return csvResult;
    }

    public void setCsvResult(File csvResult) {
        this.csvResult = csvResult;
    }

    public File getCsvStatsResult() {
        return csvStatsResult;
    }

    public void setCsvStatsResult(File csvStatsResult) {
        this.csvStatsResult = csvStatsResult;
    }

    public ScriptParameters getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(ScriptParameters additionalParams) {
        this.additionalParams = additionalParams;
    }
    
}

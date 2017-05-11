/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts.result;

import java.io.File;

/**
 * Result of script running. Used for showing in result panel.
 * @author mira
 */
public class ScriptResult {
    
    private File soundFile;
    private ResultStatus status;
    private ResultCategory category;
    private File csvResult;
    private File csvStatsResult;
    private Exception failureReason;

    public ScriptResult(File soundFile, ResultStatus status, ResultCategory category, File csvResult, File csvStatsResult, Exception failureReason) {
        this.soundFile = soundFile;
        this.status = status;
        this.category = category;
        this.csvResult = csvResult;
        this.csvStatsResult = csvStatsResult;
        this.failureReason = failureReason;
    }

    public ScriptResult(File soundFile, ResultStatus resultStatus, ResultCategory resultCategory, Exception ex) {
        this(soundFile, resultStatus, resultCategory, null, null, ex);
    }

    public ScriptResult(File soundFile, ResultStatus resultStatus, ResultCategory resultCategory, File csvResultFile, File csvStatsFile) {
        this(soundFile, resultStatus, resultCategory, csvResultFile, csvStatsFile, null);
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

    public Exception getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(Exception failureReason) {
        this.failureReason = failureReason;
    }
}

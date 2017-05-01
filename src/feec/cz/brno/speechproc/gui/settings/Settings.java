/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.settings;

import feec.cz.brno.speechproc.main.SpeechProc;

/**
 *
 * @author mira
 */
public class Settings {
    
    private static Settings settings;

    private Settings() {}
    
    public static Settings getInstance() {
        if (settings == null) {
            settings = new Settings();
        }
        return settings;
    }
    
    private String praatPath;
    private String matlabPath;
    private String octavePath;

    public String getPraatPath() {
        if (SpeechProc.isWindows()) {
            return "\"" + praatPath + "\"";
        } else {
            return praatPath;
        }
    }

    public void setPraatPath(String praatPath) {
        this.praatPath = praatPath;
    }

    public String getMatlabPath() {
        if (SpeechProc.isWindows()) {
            return "\"" + matlabPath + "\"";
        } else {
            return matlabPath;
        }
    }

    public void setMatlabPath(String matlabPath) {
        this.matlabPath = matlabPath;
    }

    public String getOctavePath() {
        if (SpeechProc.isWindows()) {
            return "\"" + octavePath + "\"";
        } else {
            return octavePath;
        }
    }

    public void setOctavePath(String octavePath) {
        this.octavePath = octavePath;
    }

    @Override
    public String toString() {
        return "Settings{" + "praatPath=" + praatPath + ", matlabPath=" + matlabPath + ", octavePath=" + octavePath + '}';
    }
    
}

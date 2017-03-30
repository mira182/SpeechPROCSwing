/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.intensity;

import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.gui.intensity.IntensityResultPanel;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static feec.cz.brno.speechproc.calc.api.f0.IF0.OUTPUT_FOLDER_F0;

/**
 *
 * @author mira
 */
public class IntensityImpl extends SwingWorker<Boolean, Void> implements IIntensity {

    private final static Logger logger = LogManager.getLogger(IntensityImpl.class);

    private final List<File> soundFiles;

    public IntensityImpl(final List<File> soundFiles) {
        this.soundFiles = soundFiles;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        if (!OUTPUT_FOLDER_INTENSITY.exists()) {
            OUTPUT_FOLDER_INTENSITY.mkdirs();
        }

        int processedFiles = 0;
        for (File soundFile : soundFiles) {
            ScriptParameters parameters = new ScriptParameters();
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_INTENSITY.getAbsolutePath() + soundFile.getName() + "-intensity.csv")));
            parameters.add(new ScriptParameter(OUTPUT_FILE_STATS_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + soundFile.getName() + "-intensity-stats.csv")));

            try {
                PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/intensity.praat").getFile()), parameters);
                praat.runScript();

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                File csvStatsFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_STATS_PARAM).getValue()));

                IntensityResultPanel intensityPanel = new IntensityResultPanel(soundFile, csvResultFile, csvStatsFile);
//                centerTabbedPanel.add("Intensity of " + soundFile.getName(), intensityPanel);
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
//                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
            setProgress(100 * ++processedFiles / soundFiles.size());
        }
        return true;
    }
    
}

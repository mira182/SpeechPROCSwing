/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.f0;

import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.gui.f0.F0ParamsDialog;
import feec.cz.brno.speechproc.gui.f0.F0ResultPanel;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author mira
 */
public class F0Impl extends SwingWorker<Boolean, Void> implements IF0 {

    private final static Logger logger = LogManager.getLogger(F0Impl.class);

    private final F0ParamsDialog paramsDialog;
    private final List<File> soundFiles;

    
    
    public F0Impl(F0ParamsDialog paramsDialog, final List<File> soundFiles) {
        this.soundFiles = soundFiles;
        this.paramsDialog = paramsDialog;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        if (!OUTPUT_FOLDER_F0.exists()) {
            OUTPUT_FOLDER_F0.mkdirs();
        }

        int processedFiles = 0;
        for (File soundFile : soundFiles) {
            ScriptParameters parameters = new ScriptParameters();
            parameters.add(new ScriptParameter("timeStep", paramsDialog.getTimeStep()));
            parameters.add(new ScriptParameter("pitch_min", paramsDialog.getPitchMin()));
            parameters.add(new ScriptParameter("pitch_max", paramsDialog.getPitchMax()));
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + soundFile.getName() + "-pitch.csv")));
            parameters.add(new ScriptParameter(OUTPUT_FILE_STATS_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + soundFile.getName() + "-pitch-stats.csv")));

            try {
                PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/F0.praat").getFile()), parameters);
                praat.runScript();

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                File csvStatsFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_STATS_PARAM).getValue()));

                F0ResultPanel f0panel = new F0ResultPanel(soundFile, csvResultFile, csvStatsFile, paramsDialog.isMeanCalc(), paramsDialog.isMedianCalc(),
                        paramsDialog.isStdevCalc(), paramsDialog.isJitterCalc(), paramsDialog.isShimmerCalc(), paramsDialog.isMinCalc(), paramsDialog.isMaxCalc());
//                centerTabbedPanel.add("F0 pitch of " + soundFile.getName(), f0panel);
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
//                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
            setProgress(100 * ++processedFiles / soundFiles.size());
        }
        return true;
    }
    
}

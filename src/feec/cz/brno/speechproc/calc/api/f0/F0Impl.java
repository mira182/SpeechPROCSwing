/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.f0;

import feec.cz.brno.speechproc.calc.api.params.ResultCategory;
import feec.cz.brno.speechproc.calc.api.params.ResultStatus;
import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.params.ScriptResult;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.gui.f0.F0ParamsDialog;
import feec.cz.brno.speechproc.gui.f0.F0ResultPanel;
import feec.cz.brno.speechproc.gui.results.ResultsTableModel;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author mira
 */
public class F0Impl extends SwingWorker<Boolean, ScriptResult> implements IF0 {

    private final static Logger logger = LogManager.getLogger(F0Impl.class);

    private final JFrame parent;
    private final F0ParamsDialog paramsDialog;
    private final ResultsTableModel resultsTableModel;
    private final List<File> soundFiles;
    
    public F0Impl(JFrame parent, F0ParamsDialog paramsDialog, final List<File> soundFiles, ResultsTableModel resultsTableModel) {
        this.parent = parent;
        this.soundFiles = soundFiles;
        this.paramsDialog = paramsDialog;
        this.resultsTableModel = resultsTableModel;
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
                
                ScriptParameters additionalParams = new ScriptParameters();
                additionalParams.add(new ScriptParameter(MEAN_PARAM, paramsDialog.isMeanCalc()));
                additionalParams.add(new ScriptParameter(MEDIAN_PARAM, paramsDialog.isMedianCalc()));
                additionalParams.add(new ScriptParameter(STDEV_PARAM, paramsDialog.isStdevCalc()));
                additionalParams.add(new ScriptParameter(JITTER_PARAM, paramsDialog.isJitterCalc()));
                additionalParams.add(new ScriptParameter(SHIMMER_PARAM, paramsDialog.isShimmerCalc()));
                additionalParams.add(new ScriptParameter(MIN_PARAM, paramsDialog.isMinCalc()));
                additionalParams.add(new ScriptParameter(MAX_PARAM, paramsDialog.isMaxCalc()));
                
                publish(new ScriptResult(soundFile, ResultStatus.OK, ResultCategory.F0, csvResultFile, csvStatsFile, additionalParams));
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                publish(new ScriptResult(ResultStatus.FAILED, ResultCategory.F0));
            }
            setProgress(100 * ++processedFiles / soundFiles.size());
        }
        return true;
    }

    @Override
    protected void process(List<ScriptResult> list) {
        for (ScriptResult result : list) {
            resultsTableModel.addRow(result);
        }
    }

    @Override
    protected void done() {
        parent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        JOptionPane.showMessageDialog(parent, "Pitch calculation is finished.", "Done", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}

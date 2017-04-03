/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.f0;

import feec.cz.brno.speechproc.calc.api.params.ResultCategory;
import feec.cz.brno.speechproc.calc.api.params.ResultStatus;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.params.ScriptResult;
import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.gui.f0.F0ParamsDialog;
import feec.cz.brno.speechproc.gui.results.ResultsTableModel;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static feec.cz.brno.speechproc.main.SpeechProc.FS;


/**
 *
 * @author mira
 */
public class F0Impl extends SwingWorker<Boolean, ScriptResult> implements IF0 {

    private final static Logger logger = LogManager.getLogger(F0Impl.class);

    private final JLabel progressLabel;
    private final JFrame parent;
    private final F0ParamsDialog paramsDialog;
    private final ResultsTableModel resultsTableModel;
    private final List<File> soundFiles;
    
    public F0Impl(JFrame parent, F0ParamsDialog paramsDialog, final List<File> soundFiles, ResultsTableModel resultsTableModel, JLabel progressLabel) {
        this.parent = parent;
        this.soundFiles = soundFiles;
        this.paramsDialog = paramsDialog;
        this.resultsTableModel = resultsTableModel;
        this.progressLabel = progressLabel;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        progressLabel.setText("Calculation of fundamental frequency...");
        
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
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + FS + soundFile.getName() + "-pitch.csv")));
            parameters.add(new ScriptParameter(OUTPUT_FILE_STATS_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + FS +  soundFile.getName() + "-pitch-stats.csv")));

            try {
                PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/F0.praat").getFile()), parameters);
                praat.runScript();

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                File csvStatsFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_STATS_PARAM).getValue()));
                
                publish(new ScriptResult(soundFile, ResultStatus.OK, ResultCategory.F0, csvResultFile, csvStatsFile));
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                publish(new ScriptResult(soundFile, ResultStatus.FAILED, ResultCategory.F0, ex));
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
        progressLabel.setText(null);
        JOptionPane.showMessageDialog(parent, "Pitch calculation is finished.", "Done", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}

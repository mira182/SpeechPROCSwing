/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.swingworkers.formants;

import feec.cz.brno.speechproc.calc.runscripts.PraatScript;
import feec.cz.brno.speechproc.calc.runscripts.ScriptRunException;
import feec.cz.brno.speechproc.calc.runscripts.result.ResultCategory;
import feec.cz.brno.speechproc.calc.runscripts.result.ResultStatus;
import feec.cz.brno.speechproc.calc.runscripts.result.ScriptResult;
import feec.cz.brno.speechproc.calc.runscripts.scriptparams.ScriptParameter;
import feec.cz.brno.speechproc.calc.runscripts.scriptparams.ScriptParameters;
import feec.cz.brno.speechproc.gui.parameters.formants.FormantParamsDialog;
import feec.cz.brno.speechproc.gui.parameters.results.ResultsTableModel;
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
 * Swingworker for computing formants in background.
 * @author mira
 */
public class FormantsImpl extends SwingWorker<Boolean, ScriptResult> implements IFormants {
    
    private final static Logger logger = LogManager.getLogger(FormantsImpl.class);
    
    private final JLabel progressLabel;
    private final JFrame parent;
    private final ResultsTableModel resultsTableModel;
    private final FormantParamsDialog paramsDialog;
    private final List<File> soundFiles;

    public FormantsImpl(JFrame parent, FormantParamsDialog paramsDialog, final List<File> soundFiles, ResultsTableModel resultsTableModel, JLabel progressLabel) {
        this.parent = parent;
        this.soundFiles = soundFiles;
        this.paramsDialog = paramsDialog;
        this.resultsTableModel = resultsTableModel;
        this.progressLabel = progressLabel;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        progressLabel.setText("Calculation of formants...");
        logger.info("Starting formants calculations.");
        
        if (!OUTPUT_FOLDER_FORMANTS.exists()) {
            OUTPUT_FOLDER_FORMANTS.mkdirs();
        }

        int processedFiles = 0;
        for (File soundFile : soundFiles) {
            ScriptParameters parameters = new ScriptParameters();
            parameters.add(new ScriptParameter("timeStep", paramsDialog.getTimeStep()));
            parameters.add(new ScriptParameter("maxFormantsNumber", IFormants.MAXIMUX_FORMANTS));
            parameters.add(new ScriptParameter("maxFormants", paramsDialog.getMaxFormant()));
            parameters.add(new ScriptParameter("windowLength", paramsDialog.getWindowLength()));
            parameters.add(new ScriptParameter("preemphasis", paramsDialog.getPreemphasis()));
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, OUTPUT_FOLDER_FORMANTS.getAbsoluteFile() + FS + soundFile.getName() + "-formantsListing.csv"));

            try {
                if (!new File((String) parameters.getParameter(OUTPUT_FILE_PARAM).getValue()).exists()) {
                    PraatScript praat = new PraatScript(SCRIPT_FILE_FORMANTS, parameters);
                    praat.runScript();
                }

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                
                publish(new ScriptResult(soundFile, ResultStatus.OK, ResultCategory.FORMANTS, csvResultFile, null));
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                publish(new ScriptResult(soundFile, ResultStatus.FAILED, ResultCategory.FORMANTS, ex));
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
        JOptionPane.showMessageDialog(parent, "Calculating of formants has finished.", "Finished.", JOptionPane.INFORMATION_MESSAGE);
    }
    
}

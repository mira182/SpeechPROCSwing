/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.params.ResultCategory;
import feec.cz.brno.speechproc.calc.api.params.ResultStatus;
import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.params.ScriptResult;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.gui.formants.FormantParamsDialog;
import feec.cz.brno.speechproc.gui.formants.FormantsResultPanel;
import feec.cz.brno.speechproc.gui.results.ResultsTableModel;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
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
public class FormantsImpl extends SwingWorker<Boolean, ScriptResult> implements IFormants {
    
    private final static Logger logger = LogManager.getLogger(FormantsImpl.class);
    
    private JFrame parent;
    private final ResultsTableModel resultsTableModel;
    private final FormantParamsDialog paramsDialog;
    private final List<File> soundFiles;

    public FormantsImpl(JFrame parent, FormantParamsDialog paramsDialog, final List<File> soundFiles, ResultsTableModel resultsTableModel) {
        this.parent = parent;
        this.soundFiles = soundFiles;
        this.paramsDialog = paramsDialog;
        this.resultsTableModel = resultsTableModel;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
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
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_FORMANTS.getAbsoluteFile() + soundFile.getName() + "-formantsListing.csv")));

            try {
                PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/formants.praat").getFile()), parameters);
                praat.runScript();

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));

                FormantsResultPanel formantResultPanel = new FormantsResultPanel(soundFile, csvResultFile, paramsDialog.isMeanCalc(), paramsDialog.isMedianCalc());
                publish(new ScriptResult(soundFile, ResultStatus.OK));
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                publish(new ScriptResult(soundFile, ResultStatus.FAILED));
                return false;
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
        JOptionPane.showMessageDialog(parent, "Calculating of formants has finished.", "Finished.", JOptionPane.INFORMATION_MESSAGE);
    }
    
}

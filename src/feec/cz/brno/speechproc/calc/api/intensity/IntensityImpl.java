/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.intensity;

import feec.cz.brno.speechproc.calc.api.params.ResultCategory;
import feec.cz.brno.speechproc.calc.api.params.ResultStatus;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.params.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.params.ScriptResult;
import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
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

import static feec.cz.brno.speechproc.calc.api.f0.IF0.OUTPUT_FOLDER_F0;
import static feec.cz.brno.speechproc.main.SpeechProc.FS;

/**
 *
 * @author mira
 */
public class IntensityImpl extends SwingWorker<Boolean, ScriptResult> implements IIntensity {

    private final static Logger logger = LogManager.getLogger(IntensityImpl.class);

    private final JLabel progressLabel;
    private final ResultsTableModel resultsTableModel;
    private final JFrame parent;
    private final List<File> soundFiles;

    public IntensityImpl(final JFrame parent, final List<File> soundFiles, ResultsTableModel resultsTableModel, JLabel progressLabel) {
        this.parent = parent;
        this.soundFiles = soundFiles;
        this.resultsTableModel = resultsTableModel;
        this.progressLabel = progressLabel;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        progressLabel.setText("Calculation of intensity...");
        
        if (!OUTPUT_FOLDER_INTENSITY.exists()) {
            OUTPUT_FOLDER_INTENSITY.mkdirs();
        }

        int processedFiles = 0;
        for (File soundFile : soundFiles) {
            ScriptParameters parameters = new ScriptParameters();
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_INTENSITY.getAbsolutePath() + FS + soundFile.getName() + "-intensity.csv")));
            parameters.add(new ScriptParameter(OUTPUT_FILE_STATS_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + FS + soundFile.getName() + "-intensity-stats.csv")));

            try {
                PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/intensity.praat").getFile()), parameters);
                praat.runScript();

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                File csvStatsFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_STATS_PARAM).getValue()));
                
                publish(new ScriptResult(soundFile, ResultStatus.OK, ResultCategory.INTENSITY, csvResultFile, csvStatsFile, null));
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                publish(new ScriptResult(soundFile, ResultStatus.FAILED, ResultCategory.INTENSITY, ex));
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
        JOptionPane.showMessageDialog(parent, "Calculating of intensity has finished.", "Done", JOptionPane.INFORMATION_MESSAGE);
    }
}

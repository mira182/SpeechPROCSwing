/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.formants;

import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.gui.formants.FormantParamsDialog;
import feec.cz.brno.speechproc.gui.formants.FormantsResultPanel;
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
public class FormantsImpl extends SwingWorker<Boolean, Void> implements IFormants {
    
    private final static Logger logger = LogManager.getLogger(FormantsImpl.class);
    
    private final FormantParamsDialog paramsDialog;
    private final List<File> soundFiles;

    public FormantsImpl(FormantParamsDialog paramsDialog, final List<File> soundFiles) {
        this.soundFiles = soundFiles;
        this.paramsDialog = paramsDialog;
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
//                centerTabbedPanel.add("Formants of " + soundFile.getName(), formantResultPanel);
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
//                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            setProgress(100 * ++processedFiles / soundFiles.size());
        }
        return true;
    }
}

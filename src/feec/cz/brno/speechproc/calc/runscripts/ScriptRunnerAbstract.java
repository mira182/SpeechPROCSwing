/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts;

import feec.cz.brno.speechproc.gui.settings.Settings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mira
 */
public abstract class ScriptRunnerAbstract implements ScriptRunner {
    
    private final static Logger logger = LogManager.getLogger(PraatScript.class);
    
    protected final static String PRAAT_COMMAND = Settings.getInstance().getPraatPath() + " --run ";
    protected final static String MATLAB_COMMAND = Settings.getInstance().getMatlabPath() + " -nodisplay -nosplash -nodesktop -r \"try, run('%s'), catch, exit, end, exit\" ";
    protected final static String OCTAVE_COMMAND = Settings.getInstance().getOctavePath() + " ";

    public abstract String buildCommand();
    
    /**
     * Executes praat script using command line.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws feec.cz.brno.speechproc.calc.runscripts.ScriptRunException
     */
    @Override
    public void runScript() throws IOException, InterruptedException, ScriptRunException {
        StringBuilder output = new StringBuilder();
        String command = buildCommand();
        
        logger.debug("Executing command: {}", command);

        Process proc;
        proc = Runtime.getRuntime().exec(command);
        proc.waitFor();

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        String line;
        while ((line = stdError.readLine()) != null) {
            output.append(line).append(System.getProperty("line.separator"));
        }

        if (!output.toString().isEmpty())
            throw new ScriptRunException("Command line output: " + output.toString());
    }
    
}

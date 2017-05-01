/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.runscripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static feec.cz.brno.speechproc.main.SpeechProc.FS;

/**
 *
 * @author mira
 */
public abstract class ScriptRunnerAbstract implements ScriptRunner {
    
    private final static Logger logger = LogManager.getLogger(PraatScript.class);
    
    protected final static String PRAAT_COMMAND = " --run ";
    protected final static String MATLAB_COMMAND = " -nodisplay -nosplash -nodesktop -r \"try, run('%s'), catch, exit, end, exit\" ";
    protected final static String OCTAVE_COMMAND = " ";

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
        BufferedReader stdOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line;
        while ((line = stdError.readLine()) != null) {
            output.append(line).append(FS);
        }
        
        while ((line = stdOutput.readLine()) != null) {
            output.append(line).append(FS);
        }

        if (!output.toString().isEmpty())
            throw new ScriptRunException("Command line output: " + output.toString());
    }
    
}

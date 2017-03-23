/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.api.runscript;

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
    
    protected final static String PRAAT_COMMAND = "praat --run ";
    protected final static String MATLAB_COMMAND = "matlab ";
    protected final static String OCTAVE_COMMAND = "octave ";

    public abstract String buildCommand();
    
    /**
     * Executes praat script using command line.
     * @return standard output of the command
     */
    @Override
    public String runScript() {
        StringBuilder output = new StringBuilder();
        String command = buildCommand();

        Process proc;
        try {
            logger.debug("Executing command: {}", command);
            proc = Runtime.getRuntime().exec(command);
            proc.waitFor();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            String line;
            while ((line = stdInput.readLine()) != null) {
                System.out.println("pica: " + line);
                output.append(line).append(System.getProperty("line.separator"));
            }
            while ((line = stdError.readLine()) != null) {
                System.out.println("kurva: " + line);
                output.append(line).append(System.getProperty("line.separator"));
            }
            
            logger.info("Command line output: {}", output.toString());
        } catch (IOException | InterruptedException e) {
            logger.error("Error during executing command", e);
        }

        return output.toString();
    }
    
}

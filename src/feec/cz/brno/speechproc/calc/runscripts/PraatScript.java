package feec.cz.brno.speechproc.calc.runscripts;

import feec.cz.brno.speechproc.calc.runscripts.scriptparams.ScriptParameters;
import java.io.File;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author mira
 */
public class PraatScript extends ScriptRunnerAbstract {
    private final static Logger logger = LogManager.getLogger(PraatScript.class);
    
    private final File praatScriptName;
    private final ScriptParameters parameters;

    public PraatScript(File praatScript, ScriptParameters parameters) {
        this.praatScriptName = praatScript;
        this.parameters = parameters;
    }
    
    @Override
    public String buildCommand() {
        StringBuilder command = new StringBuilder();
        
        command.append(ScriptRunnerAbstract.PRAAT_COMMAND);
        command.append(praatScriptName.getAbsolutePath()).append(" ");
        parameters.forEach(param -> command.append(String.valueOf(param)).append(" "));
        
        return command.toString();
    }
}
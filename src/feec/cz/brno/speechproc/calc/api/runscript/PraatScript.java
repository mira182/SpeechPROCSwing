package feec.cz.brno.speechproc.calc.api.runscript;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author mira
 */
public class PraatScript extends ScriptRunnerAbstract {
    private final static Logger logger = LogManager.getLogger(PraatScript.class);
    
    private final File praatScriptName;
    private final Map<String, Object> parameters;

    public PraatScript(File praatScript, Map<String, Object> parameters) {
        this.praatScriptName = praatScript;
        this.parameters = parameters;
    }
    
    @Override
    public String buildCommand() {
        StringBuilder command = new StringBuilder();
        
        command.append(ScriptRunnerAbstract.PRAAT_COMMAND);
        command.append(praatScriptName.getAbsolutePath()).append(" ");
        parameters.forEach((key, param) -> command.append(param).append(" "));
        
        return command.toString();
    }
}

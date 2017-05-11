package feec.cz.brno.speechproc.calc.runscripts;

import feec.cz.brno.speechproc.calc.runscripts.scriptparams.ScriptParameters;
import feec.cz.brno.speechproc.gui.settings.Settings;
import feec.cz.brno.speechproc.main.SpeechProc;
import java.io.File;


/**
 * 
 * @author mira
 */
public class PraatScript extends ScriptRunnerAbstract {
    
    private final File praatScriptName;
    private final ScriptParameters scriptParameters;

    public PraatScript(File praatScript, ScriptParameters parameters) {
        this.praatScriptName = praatScript;
        this.scriptParameters = parameters;
    }
    
    @Override
    public String buildCommand() {
        StringBuilder command = new StringBuilder();
        
        command.append(Settings.getInstance().getPraatPath());
        command.append(ScriptRunnerAbstract.PRAAT_COMMAND);
        if (SpeechProc.isWindows()) command.append("\"");
        command.append(praatScriptName.getAbsolutePath());
        if (SpeechProc.isWindows()) command.append("\"");
        command.append(" ");
        scriptParameters.forEach(param -> {
            if (SpeechProc.isWindows()) 
                command.append("\""); 
            command.append(String.valueOf(param)); 
            if (SpeechProc.isWindows()) 
                command.append("\""); 
            command.append(" ");
        });
        
        return command.toString();
    }
}

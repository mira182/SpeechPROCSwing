/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import feec.cz.brno.speechproc.praat.PraatScript;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 *
 * @author mira
 */
public class PraatScriptTest {

    private List<String> parameters;

    @Before
    public void setUp() {
        parameters = new ArrayList<>();
    }
    
    @After
    public void cleanUp() {
//        File file = new File("/home/mira/test.csv");
//        file.delete();
    }

    @Test
    public void testPraatCommand() {
        String currentDirectory = Paths.get("").toAbsolutePath().toString();
        System.out.println("Current directory: " + currentDirectory);
        
        Path pathAbsolute = Paths.get("/home/mira/Plosive1.wav");
        Path pathBase = Paths.get(currentDirectory);
        Path pathRelative = pathBase.relativize(pathAbsolute);
        
        System.out.println("Relative path to sound file from current director: " + pathRelative.toString());
        parameters.add(pathRelative.toString());
        parameters.add("/home/mira/test.csv");

        PraatScript pf = new PraatScript(new File("/home/mira/formants.praat"),
                parameters);
        pf.runPraatScript();
        File file = new File("/home/mira/test.csv");
        assertTrue("CSV file was not generated", file.exists());
    }
    
    @Test
    public void testRelativePaths() {
        Path currentDirectory = Paths.get("").toAbsolutePath();
        System.out.println("Current directory: " + currentDirectory);
        Path soundFile = Paths.get("/home/mira/Plosive1.wav");
        
        System.out.println(currentDirectory.relativize(soundFile));
        
//        File file = new File("/home/mira/Plosive1.wav");
        
    }
}

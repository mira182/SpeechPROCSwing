/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.utility;

import feec.cz.brno.speechproc.main.SpeechProc;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author mira
 */
public class ExtractResources {
    
    public File saveResource(String name) throws IOException {
        return saveResource(name, true);
    }

    public File saveResource(String name, boolean replace) throws IOException {
        return saveResource(new File("."), name, replace);
    }

    public File saveResource(File outputDirectory, String name) throws IOException {
        return saveResource(outputDirectory, name, true);
    }

    public File saveResource(File outputDirectory, String name, boolean replace) throws IOException {
        File out = new File(outputDirectory, name);
        if (!replace && out.exists()) {
            return out;
        }

        InputStream resource = ExtractResources.class.getClassLoader().getResourceAsStream(name);
        if (resource == null) {
            throw new FileNotFoundException(name + " (resource not found)");
        }
        
        try (InputStream in = resource; OutputStream writer = new BufferedOutputStream(new FileOutputStream(out))) {
            byte[] buffer = new byte[1024 * 4];
            int length;
            while ((length = in.read(buffer)) >= 0) {
                writer.write(buffer, 0, length);
            }
        }
        return out;
    }
    
    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     */
    static public String exportResource(String resourceName) throws Exception {//todo move to Utils
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = SpeechProc.class.getResourceAsStream(resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree"
            if (stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(SpeechProc.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return jarFolder + resourceName;
    }
    
}

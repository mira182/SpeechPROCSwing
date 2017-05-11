/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author mira
 */
public class ZipFolders {
    
    
    public static void addDirToArchive(ZipOutputStream zos, File srcFile) {

        File[] files = srcFile.listFiles();

        System.out.println("Adding directory: " + srcFile.getName());

        for (int i = 0; i < files.length; i++) {

            // if the file is directory, use recursion
            if (files[i].isDirectory()) {
                addDirToArchive(zos, files[i]);
                continue;
            }

            try {

                System.out.println("tAdding file: " + files[i].getName());

                // create byte buffer
                byte[] buffer = new byte[1024];

                FileInputStream fis = new FileInputStream(files[i]);

                zos.putNextEntry(new ZipEntry(files[i].getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            } catch (IOException ioe) {
                System.out.println("IOException :" + ioe);
            }

        }

    }
}

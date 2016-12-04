package feec.cz.brno.speechproc.gui.soundlist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mira
 */
public class SoundFilesTableModel extends DefaultTableModel {
    
    private final static Logger logger = LogManager.getLogger(SoundFilesTableModel.class);

    private final List<File> files;

    public SoundFilesTableModel() {
        files = new ArrayList<>();
    }
    
    public void addRow(File file) {
        if (!files.contains(file)) {
            super.addRow(new Object[] {file.getName(), file.getAbsolutePath(), getFileSize(file) + " KB"});
            files.add(file);
        } else {
            logger.debug("File {} was already added.", file.getAbsoluteFile());
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public File getFileFromRow(int rowIndex) {
        return files.get(rowIndex);
    }
    
    private double getFileSize(File file) {
        return (double) (file.length()/1024);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.results;

import feec.cz.brno.speechproc.calc.api.params.ResultStatus;
import feec.cz.brno.speechproc.calc.api.params.ScriptResult;
import feec.cz.brno.speechproc.gui.soundlist.SoundFilesTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hynstm
 */
public class ResultsTableModel extends DefaultTableModel {
    
    private final static Logger logger = LogManager.getLogger(ResultsTableModel.class);
    
    private List<ScriptResult> results;

    public ResultsTableModel() {
        this.results = new ArrayList<>();
    }
    
    public void addRow(ScriptResult result) {
        if (!results.contains(result)) {
            super.addRow(new Object[] {this.getRowCount(), result.getSoundFile().getName(), result.getStatus()});
            results.add(result);
        } else {
            logger.debug("File {} was already added.", result.getSoundFile().getAbsoluteFile());
        }
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public ScriptResult getResult(int row) {
        return results.get(row);
    }
}

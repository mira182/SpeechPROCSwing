package feec.cz.brno.speechproc.gui.soundlist;

import java.awt.Component;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mira
 */
public class SoundLIstCellRenderer extends JLabel implements TableCellRenderer {


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        //Set the icon and text.  If icon was null, say so.
//        ImageIcon icon = images[selectedIndex];

        if (value != null) {
            File file = (File) value;
            setText(file.getName() + "\t" + file.getAbsolutePath());
        }
        
        return this;
    }
    
}

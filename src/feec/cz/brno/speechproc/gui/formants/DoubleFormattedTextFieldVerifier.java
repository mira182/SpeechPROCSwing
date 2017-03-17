package feec.cz.brno.speechproc.gui.formants;

import java.text.ParseException;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * @author hynstm
 */
public class DoubleFormattedTextFieldVerifier extends InputVerifier {

    public boolean verify(JComponent input) {
        if (input instanceof JFormattedTextField) {
            JFormattedTextField ftf = (JFormattedTextField) input;
            AbstractFormatter formatter = ftf.getFormatter();
            if (formatter != null) {
                String text = ftf.getText();
                try {
                    formatter.stringToValue(text);
                    return true;
                } catch (ParseException pe) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean shouldYieldFocus(JComponent input) {
        return verify(input);
    }
}

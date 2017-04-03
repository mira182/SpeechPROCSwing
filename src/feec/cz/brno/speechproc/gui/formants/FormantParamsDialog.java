/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.formants;

import feec.cz.brno.speechproc.main.SpeechProc;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hynstm
 */
public class FormantParamsDialog extends javax.swing.JDialog {
    
    private final static Logger logger = LogManager.getLogger(SpeechProc.class);
    
    private Double timeStep;
    private Double maxFormant;
    private Double windowLength;
    private Double preemphasis;
    
    private boolean meanCalc;
    private boolean medianCalc;
    private boolean ok;
    
    public static final double MAXIMUM_FORMANTS = 3.0;
    
    private final DoubleValidatorListener doubleValidator = new DoubleValidatorListener();

    /**
     * Creates new form FormantParamsDialog
     * @param parent
     */
    public FormantParamsDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(okButton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        maxFormantTextField = new javax.swing.JTextField();
        windowLengthTextField = new javax.swing.JTextField();
        preemphasisTextField = new javax.swing.JTextField();
        timeStepTextField = new javax.swing.JTextField();
        errorMessageLabel = new javax.swing.JLabel();
        meanCheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        medianCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formants parameters");

        jLabel1.setLabelFor(timeStepTextField);
        jLabel1.setText("Time step (s):");

        jLabel3.setLabelFor(maxFormantTextField);
        jLabel3.setText("Maximum formant (Hz):");

        jLabel4.setLabelFor(windowLengthTextField);
        jLabel4.setText("Window length (s):");

        jLabel5.setLabelFor(preemphasisTextField);
        jLabel5.setText("Pre-emphasis (Hz):");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        maxFormantTextField.getDocument().addDocumentListener(doubleValidator);
        maxFormantTextField.setText("5000");
        maxFormantTextField.setName("maximum formant"); // NOI18N

        windowLengthTextField.getDocument().addDocumentListener(doubleValidator);
        windowLengthTextField.setText("0.025");
        windowLengthTextField.setName("window length"); // NOI18N

        preemphasisTextField.getDocument().addDocumentListener(doubleValidator);
        preemphasisTextField.setText("50");
        preemphasisTextField.setName("pre-emphasis"); // NOI18N

        timeStepTextField.getDocument().addDocumentListener(doubleValidator);
        timeStepTextField.setText("0.005");
        timeStepTextField.setName("time step"); // NOI18N

        errorMessageLabel.setVisible(false);
        errorMessageLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorMessageLabel.setText("Erorr");

        meanCheckBox.setText("mean");

        jLabel6.setText("Additional calculations:");

        medianCheckBox.setText("median");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeStepTextField)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(okButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(preemphasisTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(windowLengthTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxFormantTextField))
                            .addComponent(errorMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(meanCheckBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(medianCheckBox))
                                    .addComponent(jLabel6))
                                .addGap(210, 210, 210)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1))
                    .addComponent(timeStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3))
                    .addComponent(maxFormantTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4))
                    .addComponent(windowLengthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5))
                    .addComponent(preemphasisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meanCheckBox)
                    .addComponent(medianCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        timeStep = Double.parseDouble(timeStepTextField.getText());
        maxFormant = Double.parseDouble(maxFormantTextField.getText());
        windowLength = Double.parseDouble(windowLengthTextField.getText());
        preemphasis = Double.parseDouble(preemphasisTextField.getText());
        
        meanCalc = meanCheckBox.isSelected();
        medianCalc = medianCheckBox.isSelected();
        
        ok = true;

        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    public Double getTimeStep() {
        return timeStep;
    }

    public Double getWindowLength() {
        return windowLength;
    }

    public Double getPreemphasis() {
        return preemphasis;
    }

    public Double getMaxFormant() {
        return maxFormant;
    }

    public boolean isMeanCalc() {
        return meanCalc;
    }

    public boolean isMedianCalc() {
        return medianCalc;
    }

    public boolean isOk() {
        return ok;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel errorMessageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField maxFormantTextField;
    private javax.swing.JCheckBox meanCheckBox;
    private javax.swing.JCheckBox medianCheckBox;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField preemphasisTextField;
    private javax.swing.JTextField timeStepTextField;
    private javax.swing.JTextField windowLengthTextField;
    // End of variables declaration//GEN-END:variables

    class DoubleValidatorListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            validateNumber(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateNumber(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateNumber(e);
        }
        
        private void validateNumber(DocumentEvent e) {
            JTextField textField;
            if (e.getDocument() == timeStepTextField.getDocument()) {
                textField = timeStepTextField;
            } else if (e.getDocument() == maxFormantTextField.getDocument()) {
                textField = maxFormantTextField;
            } else if (e.getDocument() == preemphasisTextField.getDocument()) {
                textField = preemphasisTextField;
            } else {
                textField = windowLengthTextField;
            }

            try {
                Double.parseDouble(textField.getText());
                errorMessageLabel.setVisible(false);
                okButton.setEnabled(true);
                pack();
            } catch (NumberFormatException ex) {
                errorMessageLabel.setText("Value format of " + textField.getName() + " is incorrect!");
                errorMessageLabel.setVisible(true);
                okButton.setEnabled(false);
                pack();
            }
        }
    }
}

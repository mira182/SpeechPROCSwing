/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.f0;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author mira
 */
public class F0ParamsDialog extends javax.swing.JDialog {
    
    private final DoubleValidatorListener doubleValidator = new DoubleValidatorListener();
    
    private Double timeStep;
    private Double pitchMin;
    private Double pitchMax;
    
    private boolean meanCalc;
    private boolean medianCalc;
    private boolean stdevCalc;
    private boolean minCalc;
    private boolean maxCalc;
    
    private boolean jitterCalc;
    private boolean shimmerCalc;
    
    private boolean ok;
    
    /**
     * Creates new form F0ParamsDialog
     * @param parent
     */
    public F0ParamsDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(okButton);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        timeStepTextField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        errorMessageLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pitchMinTextField = new javax.swing.JTextField();
        pitchMaxTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("F0 parameters");

        jLabel1.setText("Time step (s):");

        timeStepTextField.getDocument().addDocumentListener(doubleValidator);
        timeStepTextField.setText("0.05");
        timeStepTextField.setName("time step"); // NOI18N

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

        errorMessageLabel.setVisible(false);
        errorMessageLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorMessageLabel.setText("Error");

        jLabel2.setText("Pitch min (Hz):");

        jLabel3.setText("Pitch max (Hz):");

        pitchMinTextField.getDocument().addDocumentListener(doubleValidator);
        pitchMinTextField.setText("75");
        pitchMinTextField.setName("pitch min"); // NOI18N

        pitchMaxTextField.getDocument().addDocumentListener(doubleValidator);
        pitchMaxTextField.setText("600");
        pitchMaxTextField.setName("pitch max"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(timeStepTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(pitchMaxTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pitchMinTextField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(timeStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pitchMinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pitchMaxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        timeStep = Double.parseDouble(timeStepTextField.getText());
        pitchMin = Double.parseDouble(pitchMinTextField.getText());
        pitchMax = Double.parseDouble(pitchMaxTextField.getText());
        
        ok = true;
        
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

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
            try {
                Double.parseDouble(timeStepTextField.getText());
                errorMessageLabel.setVisible(false);
                okButton.setEnabled(true);
                pack();
            } catch (NumberFormatException ex) {
                errorMessageLabel.setText("Value format of " + timeStepTextField.getName() + " is incorrect!");
                errorMessageLabel.setVisible(true);
                okButton.setEnabled(false);
                pack();
            }
        }
    }

    public Double getTimeStep() {
        return timeStep;
    }

    public Double getPitchMin() {
        return pitchMin;
    }

    public Double getPitchMax() {
        return pitchMax;
    }

    public boolean isOk() {
        return ok;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel errorMessageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField pitchMaxTextField;
    private javax.swing.JTextField pitchMinTextField;
    private javax.swing.JTextField timeStepTextField;
    // End of variables declaration//GEN-END:variables
}

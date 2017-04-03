/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.results;


import feec.cz.brno.speechproc.calc.api.SpeechParameter;
import feec.cz.brno.speechproc.calc.api.params.ResultStatus;
import feec.cz.brno.speechproc.calc.api.params.ScriptResult;
import feec.cz.brno.speechproc.gui.Icons;
import feec.cz.brno.speechproc.gui.f0.F0ResultPanel;
import feec.cz.brno.speechproc.gui.formants.FormantsResultPanel;
import feec.cz.brno.speechproc.gui.intensity.IntensityCharts;
import feec.cz.brno.speechproc.gui.intensity.IntensityResultPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import feec.cz.brno.speechproc.gui.api.CompareChart;
import feec.cz.brno.speechproc.gui.f0.F0PitchCharts;
import org.jfree.chart.ChartPanel;


/**
 *
 * @author hynstm
 */
public class ResultPanel extends javax.swing.JPanel {
    
    private final static Logger logger = LogManager.getLogger(ResultPanel.class);
    
    private ResultsTableModel resultTableModel = new ResultsTableModel();

    /**
     * Creates new form ResultPanel
     */
    public ResultPanel() {
        resultTableModel.setColumnIdentifiers(new String[] {"Number", "Sound file name", "Status"});
        initComponents();
        resultTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value instanceof ResultStatus) {
                    if (((ResultStatus) value).equals(ResultStatus.FAILED)) {
                        if (!isSelected)
                            c.setBackground(new Color(236, 95, 93)); // you can set the foreground and/or background here
                    }
                }
                return c;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable(resultTableModel);
        showDetailsButton = new javax.swing.JButton();
        compareButton = new javax.swing.JButton();

        resultTable.registerKeyboardAction(
            null,
            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
        resultTable.setModel(resultTableModel);
        resultTable.getSelectionModel().addListSelectionListener(new ResultTableListSelectionListener());
        resultTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultTableMouseClicked(evt);
            }
        });
        resultTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                resultTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(resultTable);

        showDetailsButton.setIcon(Icons.DETAILS_ICON);
        showDetailsButton.setText("Result details");
        showDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDetailsButtonActionPerformed(evt);
            }
        });

        compareButton.setIcon(Icons.COMPARE_CHARTS_ICON);
        compareButton.setText("Compare results");
        compareButton.setEnabled(false);
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(compareButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDetailsButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showDetailsButton)
                    .addComponent(compareButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDetailsButtonActionPerformed
        showResultDetails();
    }//GEN-LAST:event_showDetailsButtonActionPerformed

    private void resultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseClicked
        JTable table = (JTable) evt.getSource();
        Point p = evt.getPoint();
        int row = table.rowAtPoint(p);
        if (evt.getClickCount() == 2) {
            showResultDetails(row);
        }
    }//GEN-LAST:event_resultTableMouseClicked

    private void resultTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resultTableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            showResultDetails();
        }
    }//GEN-LAST:event_resultTableKeyReleased

    private void compareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareButtonActionPerformed
        if (resultTable.getSelectedRowCount() == 2) {
            ScriptResult selectedResult1 = resultTableModel.getResult(resultTable.getSelectedRows()[0]);
            ScriptResult selectedResult2 = resultTableModel.getResult(resultTable.getSelectedRows()[1]);
            ChartPanel chartPanel = null;
            switch (selectedResult1.getCategory()) {
                case FORMANTS:
                    logger.debug("Showing formants compared chart of " + selectedResult1.getSoundFile().getName() + " and " + selectedResult2.getCsvResult().getName());
                    break;
                case F0:
                    CompareChart chart = new F0PitchCharts();
                    chartPanel = chart.createComparedChart(selectedResult1.getCsvResult(), selectedResult2.getCsvResult());
                    logger.debug("Showing pitch compared chart of " + selectedResult1.getSoundFile().getName() + " and " + selectedResult2.getCsvResult().getName());
                    break;
                case INTENSITY:
                    chart = new IntensityCharts();
                    chartPanel = chart.createComparedChart(selectedResult1.getCsvResult(), selectedResult2.getCsvResult());
                    logger.debug("Showing intensity compared chart of " + selectedResult1.getSoundFile().getName() + " and " + selectedResult2.getCsvResult().getName());
                    break;
            }
            GraphWindow resultWindow = new GraphWindow("Compared graph", chartPanel);
            resultWindow.setVisible(true);
        }
    }//GEN-LAST:event_compareButtonActionPerformed

    public void addRow(ScriptResult result) {
        resultTableModel.addRow(result);
        resultTableModel.fireTableDataChanged();
    }
    
    public ResultsTableModel getResultTableModel() {
        return resultTableModel;
    }
    
    protected void showResultDetails() {
        for (int row : resultTable.getSelectedRows()) {
            showResultDetails(row);
        }
    }
    
    protected void showResultDetails(int row) {
        ScriptResult selectedResult = resultTableModel.getResult(row);
        if (selectedResult.getStatus().equals(ResultStatus.OK)) {
            JFrame resultWindow = new JFrame();
            JPanel resultPanel = null;
            switch (selectedResult.getCategory()) {
                case FORMANTS:
                    resultWindow.setTitle("Formants of " + selectedResult.getSoundFile().getName());
                    resultPanel = new FormantsResultPanel(selectedResult.getSoundFile(), selectedResult.getCsvResult());
                    logger.debug("Showing formants details of " + selectedResult.getSoundFile().getName());
                    break;
                case F0:
                    resultWindow.setTitle("F0 pitch of " + selectedResult.getSoundFile().getName());
                    resultPanel = new F0ResultPanel(selectedResult.getSoundFile(), selectedResult.getCsvResult(), selectedResult.getCsvStatsResult());
                    logger.debug("Showing pitch details of " + selectedResult.getSoundFile().getName());
                    break;
                case INTENSITY:
                    resultWindow.setTitle("Intensity of " + selectedResult.getSoundFile().getName());
                    resultPanel = new IntensityResultPanel(selectedResult.getSoundFile(), selectedResult.getCsvResult(), selectedResult.getCsvStatsResult());
                    logger.debug("Showing intensity details of " + selectedResult.getSoundFile().getName());
                    break;
            }
            resultWindow.setContentPane(resultPanel);
            resultWindow.pack();
            resultWindow.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Cannot show details of \"FAILED\" calculation.", "Show details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton compareButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resultTable;
    private javax.swing.JButton showDetailsButton;
    // End of variables declaration//GEN-END:variables

    class ResultTableListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (resultTable.getSelectedRowCount() == 2) {
                if (resultTableModel.getResult(resultTable.getSelectedRows()[0]).getStatus().equals(ResultStatus.OK) 
                        && resultTableModel.getResult(resultTable.getSelectedRows()[1]).getStatus().equals(ResultStatus.OK)) {
                    compareButton.setEnabled(true);
                }
            } else {
                compareButton.setEnabled(false);
            }
        }
        
    }
}

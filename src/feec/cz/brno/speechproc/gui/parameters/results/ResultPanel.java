/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.parameters.results;


import com.google.common.base.Throwables;
import feec.cz.brno.speechproc.calc.runscripts.result.ResultCategory;
import feec.cz.brno.speechproc.calc.runscripts.result.ResultStatus;
import feec.cz.brno.speechproc.calc.runscripts.result.ScriptResult;
import feec.cz.brno.speechproc.gui.api.charts.IChart;
import feec.cz.brno.speechproc.gui.api.charts.IFormantCharts;
import feec.cz.brno.speechproc.gui.icons.Icons;
import feec.cz.brno.speechproc.gui.parameters.f0.F0PitchCharts;
import feec.cz.brno.speechproc.gui.parameters.f0.F0ResultPanel;
import feec.cz.brno.speechproc.gui.parameters.formants.FormantCharts;
import feec.cz.brno.speechproc.gui.parameters.formants.FormantsResultPanel;
import feec.cz.brno.speechproc.gui.parameters.intensity.IntensityCharts;
import feec.cz.brno.speechproc.gui.parameters.intensity.IntensityResultPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        vowelSpaceButton = new javax.swing.JButton();

        resultTable.registerKeyboardAction(
            null,
            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
        resultTable.setModel(resultTableModel);
        resultTable.getSelectionModel().addListSelectionListener(new ResultTableListSelectionListener());
        resultTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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

        vowelSpaceButton.setIcon(Icons.VOWELS_ICON);
        vowelSpaceButton.setText("Vowel space chart");
        vowelSpaceButton.setEnabled(false);
        vowelSpaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vowelSpaceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vowelSpaceButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(compareButton)
                    .addComponent(vowelSpaceButton))
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
            IChart chart = null;
            switch (selectedResult1.getCategory()) {
                case FORMANTS:
                    logger.debug("Showing formants compared chart of " + selectedResult1.getSoundFile().getName() + " and " + selectedResult2.getCsvResult().getName());
                    chart = new FormantCharts();
                    chartPanel = chart.createComparedChart(selectedResult1.getCsvResult(), selectedResult2.getCsvResult());
                    break;
                case F0:
                    logger.debug("Showing pitch compared chart of " + selectedResult1.getSoundFile().getName() + " and " + selectedResult2.getCsvResult().getName());
                    chart = new F0PitchCharts();
                    chartPanel = chart.createComparedChart(selectedResult1.getCsvResult(), selectedResult2.getCsvResult());
                    break;
                case INTENSITY:
                    logger.debug("Showing intensity compared chart of " + selectedResult1.getSoundFile().getName() + " and " + selectedResult2.getCsvResult().getName());
                    chart = new IntensityCharts();
                    chartPanel = chart.createComparedChart(selectedResult1.getCsvResult(), selectedResult2.getCsvResult());
                    break;
            }
            GraphWindow resultWindow = new GraphWindow("Compared graph", chartPanel, chart.getControlPanel());
            resultWindow.setVisible(true);
        }
    }//GEN-LAST:event_compareButtonActionPerformed

    private void vowelSpaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vowelSpaceButtonActionPerformed
        // TODO add vowel space area chart
        if (resultTable.getSelectedRowCount() == 5) {
            ScriptResult selectedResult1 = resultTableModel.getResult(resultTable.getSelectedRows()[0]);
            ScriptResult selectedResult2 = resultTableModel.getResult(resultTable.getSelectedRows()[1]);
            ScriptResult selectedResult3 = resultTableModel.getResult(resultTable.getSelectedRows()[2]);
            ScriptResult selectedResult4 = resultTableModel.getResult(resultTable.getSelectedRows()[3]);
            ScriptResult selectedResult5 = resultTableModel.getResult(resultTable.getSelectedRows()[4]);
            if (selectedResult1.getCategory().equals(ResultCategory.FORMANTS)) {
                IFormantCharts formantCharts = new FormantCharts();
                ChartPanel chartPanel = formantCharts.createVowelSpaceAreaChart(selectedResult1.getCsvResult(), selectedResult2.getCsvResult(), 
                        selectedResult3.getCsvResult(), selectedResult4.getCsvResult(), selectedResult5.getCsvResult());
                GraphWindow resultWindow = new GraphWindow("Vowel space area chart", chartPanel, formantCharts.getControlPanel());
                resultWindow.setVisible(true);
            }
        }
    }//GEN-LAST:event_vowelSpaceButtonActionPerformed

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
            JTextArea ta = new JTextArea(Throwables.getStackTraceAsString(selectedResult.getFailureReason()), 10, 30);
            ta.setEditable(false);
            JScrollPane scrolPane = new JScrollPane(ta);
            scrolPane.setPreferredSize(new Dimension(700, 240));
            JOptionPane.showMessageDialog(this, scrolPane, "Failure reason.", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton compareButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resultTable;
    private javax.swing.JButton showDetailsButton;
    private javax.swing.JButton vowelSpaceButton;
    // End of variables declaration//GEN-END:variables

    class ResultTableListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (resultTable.getSelectedRowCount() == 2) {
                if (resultTableModel.getResult(resultTable.getSelectedRows()[0]).getStatus().equals(ResultStatus.OK) 
                        && resultTableModel.getResult(resultTable.getSelectedRows()[1]).getStatus().equals(ResultStatus.OK)) {
                    compareButton.setEnabled(true);
                }
            } else if (resultTable.getSelectedRowCount() == 5) {
                if (resultTableModel.getResult(resultTable.getSelectedRows()[0]).getCategory().equals(ResultCategory.FORMANTS))
                    vowelSpaceButton.setEnabled(true);
            } else {
                compareButton.setEnabled(false);
                vowelSpaceButton.setEnabled(false);
            }
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.parameters.intensity;

import au.com.bytecode.opencsv.CSVReader;
import feec.cz.brno.speechproc.gui.Icons;
import feec.cz.brno.speechproc.gui.parameters.results.GraphWindow;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import feec.cz.brno.speechproc.gui.api.charts.IChart;

/**
 *
 * @author hynstm
 */
public class IntensityResultPanel extends javax.swing.JPanel {
    
    private final static Logger logger = LogManager.getLogger(IntensityResultPanel.class);

    private DefaultTableModel intensityTableModel = new DefaultTableModel();

    private final File sourceSoundFile;
    private final File csvResultFile;
    private final File csvStatsFile;
    
    private GraphWindow intensityChart;
    
    /**
     * Creates new form IntensityResultPanel
     * @param sourceSoundFile
     * @param csvResultFile
     * @param csvStatsFile
     */
    public IntensityResultPanel(File sourceSoundFile, File csvResultFile, File csvStatsFile) {
        this.sourceSoundFile = sourceSoundFile;
        this.csvResultFile = csvResultFile;
        this.csvStatsFile = csvStatsFile;
        initComponents();
        loadIntensity();
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        minDescriptionLabel = new javax.swing.JLabel();
        minValueLabel = new javax.swing.JLabel();
        maxDescriptionLabel = new javax.swing.JLabel();
        maxValueLabel = new javax.swing.JLabel();
        meanDescriptionLabel = new javax.swing.JLabel();
        meanValueLabel = new javax.swing.JLabel();
        medianDescriptionLabel = new javax.swing.JLabel();
        medianValueLabel = new javax.swing.JLabel();
        stdevDescriptionLabel = new javax.swing.JLabel();
        stdevValueLabel = new javax.swing.JLabel();
        timeOfMinLabel = new javax.swing.JLabel();
        timeOfMaxLabel = new javax.swing.JLabel();
        timeOfMinValueLabel = new javax.swing.JLabel();
        timeOfMaxValueLabel = new javax.swing.JLabel();
        showGraphButton = new javax.swing.JButton(Icons.GRAPH_ICON);
        jScrollPane2 = new javax.swing.JScrollPane();
        intensityTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Intensity stats"));

        minDescriptionLabel.setText("Min:");

        minValueLabel.setText("jLabel1");

        maxDescriptionLabel.setText("Max:");

        maxValueLabel.setText("jLabel1");

        meanDescriptionLabel.setText("Mean:");

        meanValueLabel.setText("jLabel1");

        medianDescriptionLabel.setText("Median:");

        medianValueLabel.setText("jLabel1");

        stdevDescriptionLabel.setText("Stdev:");

        stdevValueLabel.setText("jLabel1");

        timeOfMinLabel.setText("Time of min:");

        timeOfMaxLabel.setText("Time of max:");

        timeOfMinValueLabel.setText("jLabel2");

        timeOfMaxValueLabel.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(medianDescriptionLabel)
                            .addComponent(stdevDescriptionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(medianValueLabel)
                            .addComponent(stdevValueLabel)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxDescriptionLabel)
                            .addComponent(minDescriptionLabel)
                            .addComponent(meanDescriptionLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(meanValueLabel)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(maxValueLabel)
                                    .addComponent(minValueLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(timeOfMinLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeOfMinValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(timeOfMaxLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeOfMaxValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minDescriptionLabel)
                    .addComponent(minValueLabel)
                    .addComponent(timeOfMinLabel)
                    .addComponent(timeOfMinValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxDescriptionLabel)
                    .addComponent(maxValueLabel)
                    .addComponent(timeOfMaxLabel)
                    .addComponent(timeOfMaxValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meanDescriptionLabel)
                    .addComponent(meanValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medianDescriptionLabel)
                    .addComponent(medianValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stdevDescriptionLabel)
                    .addComponent(stdevValueLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        showGraphButton.setText("Show chart");
        showGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGraphButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showGraphButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showGraphButton)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, java.awt.BorderLayout.PAGE_END);

        intensityTable.setModel(intensityTableModel);
        jScrollPane2.setViewportView(intensityTable);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void showGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGraphButtonActionPerformed
        logger.debug("Showing intensity graph from " + csvResultFile.getName());
        if (intensityChart == null) {    
            IChart graph = new IntensityCharts();
            intensityChart = new GraphWindow(csvResultFile.getName(), graph.createChart(csvResultFile));
            intensityChart.setVisible(true);
        } else {
            intensityChart.setVisible(true);
        }
    }//GEN-LAST:event_showGraphButtonActionPerformed

    private void loadIntensity() {
        String[] csvLine = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(csvResultFile), ' ');
            reader.readNext();
            intensityTableModel.setColumnIdentifiers(new String[]{"Time (s)", "Intensity"});
            while ((csvLine = reader.readNext()) != null) {
                intensityTableModel.addRow(csvLine);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load intensity table from CSV file " + csvResultFile.getName(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error("Failed to load intensity table from CSV file!", ex);
        }
        intensityTable.setModel(intensityTableModel);
        intensityTableModel.fireTableDataChanged();

        try {
            CSVReader reader = new CSVReader(new FileReader(csvStatsFile), ' ');
            reader.readNext();
            csvLine = reader.readNext();
            
            minValueLabel.setText(csvLine[0]);
            timeOfMinValueLabel.setText(csvLine[1]);
            maxValueLabel.setText(csvLine[2]);
            timeOfMaxValueLabel.setText(csvLine[3]);
            meanValueLabel.setText(csvLine[4]);
            medianValueLabel.setText(csvLine[5]);
            stdevValueLabel.setText(csvLine[6]);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load intensity table from CSV file " + csvStatsFile.getName(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error("Failed to load formants intensity from CSV file!", ex);
        }
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable intensityTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel maxDescriptionLabel;
    private javax.swing.JLabel maxValueLabel;
    private javax.swing.JLabel meanDescriptionLabel;
    private javax.swing.JLabel meanValueLabel;
    private javax.swing.JLabel medianDescriptionLabel;
    private javax.swing.JLabel medianValueLabel;
    private javax.swing.JLabel minDescriptionLabel;
    private javax.swing.JLabel minValueLabel;
    private javax.swing.JButton showGraphButton;
    private javax.swing.JLabel stdevDescriptionLabel;
    private javax.swing.JLabel stdevValueLabel;
    private javax.swing.JLabel timeOfMaxLabel;
    private javax.swing.JLabel timeOfMaxValueLabel;
    private javax.swing.JLabel timeOfMinLabel;
    private javax.swing.JLabel timeOfMinValueLabel;
    // End of variables declaration//GEN-END:variables
}

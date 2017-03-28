package feec.cz.brno.speechproc.main;

import feec.cz.brno.speechproc.calc.api.f0.IF0;
import feec.cz.brno.speechproc.calc.api.f0.IF0Impl;
import feec.cz.brno.speechproc.calc.api.formants.FormantsImpl;
import feec.cz.brno.speechproc.calc.api.formants.IFormants;
import feec.cz.brno.speechproc.calc.api.runscript.PraatScript;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameter;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptParameters;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunException;
import feec.cz.brno.speechproc.calc.api.runscript.ScriptRunner;
import feec.cz.brno.speechproc.gui.JTabbedPaneCloseButton;
import feec.cz.brno.speechproc.gui.f0.F0ParamsDialog;
import feec.cz.brno.speechproc.gui.f0.F0ResultPanel;
import feec.cz.brno.speechproc.gui.formants.FormantCharts;
import feec.cz.brno.speechproc.gui.formants.FormantParamsDialog;
import feec.cz.brno.speechproc.gui.formants.FormantsResultPanel;
import feec.cz.brno.speechproc.gui.intensity.IntensityResultPanel;
import feec.cz.brno.speechproc.gui.soundlist.SoundFilesTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mira
 */
public class SpeechProc extends javax.swing.JFrame {

    private final static Logger logger = LogManager.getLogger(SpeechProc.class);
    
    public static final String FS = System.getProperty("file.separator");
    public static final String USER_DIR = System.getProperty("user.dir");

    private SoundFilesTableModel soundFilesTableModel = new SoundFilesTableModel();
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(soundFilesTableModel);

    private IFormants formants = new FormantsImpl();
    private IF0 f0pitch = new IF0Impl();
    
    private static final int IMG_WIDTH = 32;
    private static final int IMG_HEIGHT= 32;
    
    private static final String OUTPUT_FILE_PARAM = "outputFile";
    private static final String OUTPUT_FILE_STATS_PARAM = "outputStatsFileName";
    
    public static final File OUTPUT_FOLDER_FORMANTS = new File(USER_DIR + FS + "tmpFiles" + FS + "formants" + FS);
    public static final File OUTPUT_FOLDER_F0 = new File(USER_DIR + FS + "tmpFiles" + FS + "f0" + FS);
    public static final File OUTPUT_FOLDER_JITTER_SHIMMER = new File(USER_DIR + FS + "tmpFiles" + FS + "jitt_shimm" + FS);
    public static final File OUTPUT_FOLDER_INTENSITY = new File(USER_DIR + FS + "tmpFiles" + FS + "intensity" + FS);

    /**
     * Creates new form SpeechProc
     */
    public SpeechProc() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            logger.error("Cannot set Nimbus Look and feel.", e);
        }
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        praatScriptBtn = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();
        centerSplitPanel = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        addSoundFileBtn = new javax.swing.JButton();
        removeSoundFileBtn = new javax.swing.JButton();
        soundFilesListScrollPanel = new javax.swing.JScrollPane();
        soundFilesTable = new javax.swing.JTable();
        searchFileTextField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        centerTabbedPanel = new JTabbedPaneCloseButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openSoundFilesMenuItem = new javax.swing.JMenuItem();
        praatScriptMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        formantsMenuItem = new javax.swing.JMenuItem();
        f0MenuItem = new javax.swing.JMenuItem();
        intensityMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Speech PROC");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setSize(new java.awt.Dimension(1078, 980));

        toolBar.setRollover(true);

        praatScriptBtn.setText("Run praat script");
        praatScriptBtn.setToolTipText("Run praat script...");
        praatScriptBtn.setFocusable(false);
        praatScriptBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        praatScriptBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        praatScriptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                praatScriptBtnActionPerformed(evt);
            }
        });
        toolBar.add(praatScriptBtn);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        bottomPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));
        bottomPanel.add(progressBar);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        centerSplitPanel.setDividerLocation(300);
        centerSplitPanel.setResizeWeight(0.3);
        centerSplitPanel.setAutoscrolls(true);
        centerSplitPanel.setOneTouchExpandable(true);

        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input files", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        addSoundFileBtn.setIcon(new ImageIcon(((new ImageIcon(getClass().getClassLoader().getResource("images/icons/plus.png"))).getImage()).getScaledInstance(IMG_WIDTH, IMG_HEIGHT, java.awt.Image.SCALE_SMOOTH)));
        addSoundFileBtn.setToolTipText("Add file");
        addSoundFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSoundFileBtnActionPerformed(evt);
            }
        });

        removeSoundFileBtn.setIcon(new ImageIcon(((new ImageIcon(getClass().getClassLoader().getResource("images/icons/minus.png"))).getImage()).getScaledInstance(IMG_WIDTH, IMG_HEIGHT, java.awt.Image.SCALE_SMOOTH)));
        removeSoundFileBtn.setToolTipText("Remove file");
        removeSoundFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSoundFileBtnActionPerformed(evt);
            }
        });

        soundFilesTableModel.setColumnIdentifiers(new String[] { "Name", "Path", "Size"});
        soundFilesTable.setModel(soundFilesTableModel);
        soundFilesTable.setRowSorter(rowSorter);
        soundFilesTable.setGridColor(new java.awt.Color(255, 255, 255));
        soundFilesTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        soundFilesListScrollPanel.setViewportView(soundFilesTable);

        searchFileTextField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchFileTextField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchFileTextField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        searchFileTextField.setMaximumSize(new java.awt.Dimension(2147483647, 32));

        searchLabel.setText("Search:");

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(soundFilesListScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addComponent(addSoundFileBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeSoundFileBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                        .addComponent(searchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchFileTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchFileTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundFilesListScrollPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSoundFileBtn)
                    .addComponent(removeSoundFileBtn)))
        );

        centerSplitPanel.setLeftComponent(leftPanel);
        centerSplitPanel.setRightComponent(centerTabbedPanel);

        getContentPane().add(centerSplitPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openSoundFilesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openSoundFilesMenuItem.setText("Open sound files...");
        openSoundFilesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSoundFilesMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openSoundFilesMenuItem);

        praatScriptMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        praatScriptMenuItem.setMnemonic('s');
        praatScriptMenuItem.setText("Run praat script...");
        praatScriptMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                praatScriptMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(praatScriptMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Parameters");

        formantsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        formantsMenuItem.setMnemonic('t');
        formantsMenuItem.setText("Formants listing");
        formantsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formantsMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(formantsMenuItem);

        f0MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        f0MenuItem.setMnemonic('p');
        f0MenuItem.setText("F0 (pitch)");
        f0MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f0MenuItemActionPerformed(evt);
            }
        });
        editMenu.add(f0MenuItem);

        intensityMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        intensityMenuItem.setMnemonic('d');
        intensityMenuItem.setText("Intensity");
        intensityMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intensityMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(intensityMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        FileUtils.deleteQuietly(OUTPUT_FOLDER_F0);
        FileUtils.deleteQuietly(OUTPUT_FOLDER_FORMANTS);
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void formantsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formantsMenuItemActionPerformed
        List<File> soundFiles = getSelectedSoundFiles();

        if (soundFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No sound file selected!", "No file.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        FormantParamsDialog paramsDialog = new FormantParamsDialog(this);
        paramsDialog.setVisible(true);

        if (!OUTPUT_FOLDER_FORMANTS.exists()) {
            OUTPUT_FOLDER_FORMANTS.mkdirs();
        }
        
        for (File soundFile : soundFiles) {
            ScriptParameters parameters = new ScriptParameters();
            parameters.add(new ScriptParameter("timeStep", paramsDialog.getTimeStep()));
            parameters.add(new ScriptParameter("maxFormantsNumber", IFormants.MAXIMUX_FORMANTS));
            parameters.add(new ScriptParameter("maxFormants", paramsDialog.getMaxFormant()));
            parameters.add(new ScriptParameter("windowLength", paramsDialog.getWindowLength()));
            parameters.add(new ScriptParameter("preemphasis", paramsDialog.getPreemphasis()));
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_FORMANTS.getAbsoluteFile() + soundFile.getName() + "-formantsListing.csv")));

            try {
                formants.formantListings(parameters);

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                
                FormantsResultPanel formantResultPanel = new FormantsResultPanel(soundFile, csvResultFile, paramsDialog.isMeanCalc(), paramsDialog.isMedianCalc());
                centerTabbedPanel.add("Formants of " + soundFile.getName(), formantResultPanel);
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_formantsMenuItemActionPerformed

    private void removeSoundFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSoundFileBtnActionPerformed
        for (int row : soundFilesTable.getSelectedRows()) {
            soundFilesTableModel.removeRow(row);
        }
    }//GEN-LAST:event_removeSoundFileBtnActionPerformed

    private void openSoundFilesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSoundFilesMenuItemActionPerformed
        addSoundFiles();
    }//GEN-LAST:event_openSoundFilesMenuItemActionPerformed

    private void addSoundFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSoundFileBtnActionPerformed
        addSoundFiles();
    }//GEN-LAST:event_addSoundFileBtnActionPerformed

    private void praatScriptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_praatScriptBtnActionPerformed
        runPraatScript();
    }//GEN-LAST:event_praatScriptBtnActionPerformed

    private void praatScriptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_praatScriptMenuItemActionPerformed
        runPraatScript();
    }//GEN-LAST:event_praatScriptMenuItemActionPerformed

    private void f0MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f0MenuItemActionPerformed
        List<File> soundFiles = getSelectedSoundFiles();

        if (soundFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No sound file selected!", "No file.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        F0ParamsDialog paramsDialog = new F0ParamsDialog(this);
        paramsDialog.setVisible(true);
        
        if (!OUTPUT_FOLDER_F0.exists()) {
            OUTPUT_FOLDER_F0.mkdirs();
        }

        for (File soundFile : soundFiles) {
            ScriptParameters parameters = new ScriptParameters();
            parameters.add(new ScriptParameter("timeStep", paramsDialog.getTimeStep()));
            parameters.add(new ScriptParameter("pitch_min", paramsDialog.getPitchMin()));
            parameters.add(new ScriptParameter("pitch_max", paramsDialog.getPitchMax()));
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + soundFile.getName() + "-pitch.csv")));
            parameters.add(new ScriptParameter(OUTPUT_FILE_STATS_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + soundFile.getName() + "-pitch-stats.csv")));

            try {
                PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/F0.praat").getFile()), parameters);
                praat.runScript();
                
                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                File csvStatsFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_STATS_PARAM).getValue()));
                
                F0ResultPanel f0panel = new F0ResultPanel(soundFile, csvResultFile, csvStatsFile, paramsDialog.isMeanCalc(), paramsDialog.isMedianCalc(), 
                        paramsDialog.isStdevCalc(), paramsDialog.isJitterCalc(), paramsDialog.isShimmerCalc(), paramsDialog.isMinCalc(), paramsDialog.isMaxCalc());
                centerTabbedPanel.add("F0 pitch of " + soundFile.getName(), f0panel);
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_f0MenuItemActionPerformed

    private void intensityMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intensityMenuItemActionPerformed
        List<File> soundFiles = getSelectedSoundFiles();

        if (soundFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No sound file selected!", "No file.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!OUTPUT_FOLDER_INTENSITY.exists()) {
            OUTPUT_FOLDER_INTENSITY.mkdirs();
        }
        
        for (File soundFile: soundFiles) {
            ScriptParameters parameters = new ScriptParameters();
            parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));
            parameters.add(new ScriptParameter(OUTPUT_FILE_PARAM, new File(OUTPUT_FOLDER_INTENSITY.getAbsolutePath() + soundFile.getName() + "-intensity.csv")));
            parameters.add(new ScriptParameter(OUTPUT_FILE_STATS_PARAM, new File(OUTPUT_FOLDER_F0.getAbsolutePath() + soundFile.getName() + "-intensity-stats.csv")));

            try {
                PraatScript praat = new PraatScript(new File(getClass().getClassLoader().getResource("praat/intensity.praat").getFile()), parameters);
                praat.runScript();

                File csvResultFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_PARAM).getValue()));
                File csvStatsFile = new File(String.valueOf(parameters.getParameter(OUTPUT_FILE_STATS_PARAM).getValue()));

                IntensityResultPanel intensityPanel = new IntensityResultPanel(soundFile, csvResultFile, csvStatsFile);
                centerTabbedPanel.add("Intensity of " + soundFile.getName(), intensityPanel);
            } catch (IOException | InterruptedException | ScriptRunException ex) {
                logger.error("Praat script run has failed: ", ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_intensityMenuItemActionPerformed

    private void addSoundFiles() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setDialogTitle("Open sound file");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Sound files (*.wav, *.mp3)", "wav", "mp3"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            List<File> list = new ArrayList<>(Arrays.asList(fileChooser.getSelectedFiles()));
            list.forEach(file -> {
                logger.debug("Opening sound file: {}", file.getAbsoluteFile());
                soundFilesTableModel.addRow(file);
            });
        } else {
            logger.trace("Open sound files cancelled by user.");
        }
    }

    private void runPraatScript() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open praat script");
        fileChooser.setMultiSelectionEnabled(rootPaneCheckingEnabled);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Praat script (*.praat)", "praat"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ScriptParameters parameters = new ScriptParameters();
            File praatScript = fileChooser.getSelectedFile();
            List<File> soundFiles = getSelectedSoundFiles();

            if (soundFiles.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No sound file selected!", "No file.", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (File soundFile : soundFiles) {

                parameters.add(new ScriptParameter("soundFilePath", soundFile.getAbsolutePath()));

//            parameters.add(soundFile.getCanonicalPath().replaceFirst(soundFile.getName(), "test.csv"));
                parameters.add(new ScriptParameter("outputFile", "./formantsListings.csv"));

                ScriptRunner praat = new PraatScript(praatScript, parameters);
                try {
                    praat.runScript();
                } catch (IOException | InterruptedException | ScriptRunException ex) {
                    logger.error("Praat script run has failed: ", ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(this, "Praat script has finished successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);

                FormantCharts graph = new FormantCharts();
                centerTabbedPanel.add("Formants listing", graph.createFormantChart(new File("formantsListings.csv"), true, false));
            }
        }
    }

    private List<File> getSelectedSoundFiles() {
        List<File> selectedSoundFiles = new ArrayList<>();
        File soundFile;

        for (int i = 0; i < soundFilesTable.getSelectedRows().length; i++) {
            soundFile = (File) soundFilesTableModel.getFileFromRow(soundFilesTable.getSelectedRows()[i]);
            selectedSoundFiles.add(soundFile);
        }

        return selectedSoundFiles;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpeechProc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SpeechProc().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addSoundFileBtn;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JSplitPane centerSplitPanel;
    private javax.swing.JTabbedPane centerTabbedPanel;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem f0MenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem formantsMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem intensityMenuItem;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openSoundFilesMenuItem;
    private javax.swing.JButton praatScriptBtn;
    private javax.swing.JMenuItem praatScriptMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton removeSoundFileBtn;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JTextField searchFileTextField;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JScrollPane soundFilesListScrollPanel;
    private javax.swing.JTable soundFilesTable;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

}

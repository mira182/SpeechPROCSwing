/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.icons;

import feec.cz.brno.speechproc.main.SpeechProc;
import java.awt.Image;
import javax.swing.ImageIcon;

import static java.awt.Image.SCALE_SMOOTH;

/**
 *
 * @author mira
 */
public class Icons {
    
    // ICONS
    public static final int IMG_WIDTH = 32;
    public static final int IMG_HEIGHT = 32;

    private static final Image graphImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/graph.png")).getImage();
    private static final Image plusImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/plus.png")).getImage();
    private static final Image minusImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/minus.png")).getImage();
    private static final Image detailsImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/details.png")).getImage();
    private static final Image compareChartsImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/compare-charts.png")).getImage();
    private static final Image vowelsImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/vowels.png")).getImage();
    private static final Image praatImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/praat.png")).getImage();
    private static final Image matlabImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/matlab.png")).getImage();
    private static final Image octaveImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/octave.png")).getImage();
    private static final Image addSoundImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/addSound.png")).getImage();
    private static final Image saveImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/save.png")).getImage();
    private static final Image settingsImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/settings.png")).getImage();
    private static final Image helpImage = new ImageIcon(SpeechProc.class.getClassLoader().getResource("images/icons/help.png")).getImage();

    public static final ImageIcon GRAPH_ICON = new ImageIcon(graphImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon PLUS_ICON = new ImageIcon(plusImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon MINUS_ICON = new ImageIcon(minusImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon DETAILS_ICON = new ImageIcon(detailsImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon COMPARE_CHARTS_ICON = new ImageIcon(compareChartsImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon VOWELS_ICON = new ImageIcon(vowelsImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon PRAAT_ICON = new ImageIcon(praatImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon MATLAB_ICON = new ImageIcon(matlabImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon OCTAVE_ICON = new ImageIcon(octaveImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon ADD_SOUND_ICON = new ImageIcon(addSoundImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon SAVE_ICON = new ImageIcon(saveImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon SETTINGS_ICON = new ImageIcon(settingsImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon HELP_ICON = new ImageIcon(helpImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    
}

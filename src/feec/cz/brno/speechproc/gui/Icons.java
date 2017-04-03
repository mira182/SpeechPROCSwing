/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui;

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

    public static final ImageIcon GRAPH_ICON = new ImageIcon(graphImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon PLUS_ICON = new ImageIcon(plusImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    public static final ImageIcon MINUS_ICON = new ImageIcon(minusImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, SCALE_SMOOTH));
    
}

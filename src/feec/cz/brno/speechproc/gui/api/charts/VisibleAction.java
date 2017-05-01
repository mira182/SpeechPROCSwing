/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.api.charts;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.jfree.chart.renderer.xy.XYItemRenderer;

/**
 *
 * @author mira
 */
public class VisibleAction extends AbstractAction {

    private XYItemRenderer renderer;
    private int i;

    public VisibleAction(XYItemRenderer renderer, String seriesName, int i) {
        super(seriesName);
        this.renderer = renderer;
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i));
    }
}

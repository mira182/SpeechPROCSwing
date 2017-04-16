/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.api.charts;

import java.io.Serializable;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

/**
 *
 * @author mira
 */
public class LegendXYItemLabelGenerator extends StandardXYItemLabelGenerator
        implements XYItemLabelGenerator, Cloneable, PublicCloneable,
        Serializable {

    private LegendItemCollection legendItems;

    public LegendXYItemLabelGenerator(LegendItemCollection legendItems) {
        super();
        this.legendItems = legendItems;
    }

    @Override
    public String generateLabel(XYDataset dataset, int series, int item) {
        LegendItem legendItem = legendItems.get(series);
        return legendItem.getLabel();
    }
}

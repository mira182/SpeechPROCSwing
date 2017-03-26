/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.utility;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author mira
 */
public class CalcUtilities {
    
    public static double getDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public static double mean(List<Double> values) {
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }
    
    public static double median(List<Double> values) {
        Collections.sort(values);
        int middle = values.size() / 2;
        if (values.size() % 2 == 1)
            return values.get(middle);
        else
            return (values.get(middle - 1) + values.get(middle)) / 2.0;
    }
}

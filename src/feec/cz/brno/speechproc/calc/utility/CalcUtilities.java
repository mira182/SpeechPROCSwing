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
    
    public static Double getDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return null;
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
    
    public static double variance(List<Double> values) {
        double mean = mean(values);
        double temp = 0;
        for (double a : values) {
            temp += (a - mean) * (a - mean);
        }
        return temp / values.size();
    }
    
    public static double stdev(List<Double> values) {
        return Math.sqrt(variance(values));
    }
    
    public static double max(List<Double> values) {
        double maxValue = Double.MIN_VALUE;
        for (Double d : values) {
            if (d > maxValue) {
                maxValue = d;
            }
        }
        return maxValue;
    }
}

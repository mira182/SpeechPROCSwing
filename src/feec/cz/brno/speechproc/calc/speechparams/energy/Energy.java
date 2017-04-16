/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc.speechparams.energy;

import feec.cz.brno.speechproc.calc.utility.AudioSampleReader;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author mira
 */
public class Energy {
    
    private double[] getSamples(File soundFile) {
        AudioSampleReader sampleReader = null;
        double[] samples = null;
        try {
            sampleReader = new AudioSampleReader(soundFile);
            long nbSamples = sampleReader.getSampleCount();
            samples = new double[(int) nbSamples];
            sampleReader.getInterleavedSamples(0, nbSamples, samples);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return samples;
    }
    
    public Double getEnergy(File soundFile) {
        double sum = 0;
        double[] samples = getSamples(soundFile);
        for (int i = 0; i < samples.length; i++) {
            sum += Math.pow(samples[i], 2);
        }
        return sum;
    }
}

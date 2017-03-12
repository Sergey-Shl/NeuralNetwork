package com.serge.neuralnetwork;

import android.graphics.Bitmap;
import android.util.Log;

import javax.xml.transform.Source;

/**
 * Created by serge on 10.03.2017.
 */

public class Neuron {
    private final Integer _SIZE = 16;
    private static Integer idCounter = 0;

    private Double[][] weight;
    private Integer trainCounter;
    private Integer neuronId;

    public Neuron() {
        weight = new Double[_SIZE][_SIZE];
        neuronId = idCounter;
        idCounter++;
    }

    double Compare(Double[][] source) {
        Double result = 0.0;
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                result += 1 - Math.abs(weight[i][j] - source[i][j]);
            }
        }
        return result;
    }

    double Compare(Bitmap bitmap) {
        Double result = 0.0;
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                result += 1 - Math.abs(weight[i][j] - bitmap.getPixel(j, i));
            }
        }
        return result;
    }

    public Boolean Train(Double[][] source) {
        //Double d = 1.0 / trainCounter;
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                //weight[i][j] += 2 * (source[i][j] - 0.5f) / countTrainig;
                weight[i][j] = source[i][j];
            }
        }
        return true;
    }

    public Boolean Train(Bitmap bitmap) {
        Double d = 1.0 / trainCounter;
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                //weight[i][j] += 2 * (source[i][j] - 0.5f) / countTrainig;
                weight[i][j] = (double)bitmap.getPixel(j, i);
            }
        }
        return true;
    }


    public Integer getNeuronId() {
        return neuronId;
    }

    public void setNeuronId(Integer neuronId) {
        this.neuronId = neuronId;
    }

    public static void restartCounter() {
        idCounter = 0;
    }
}

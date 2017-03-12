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
    private String neuronName;

    private void Init()
    {
        weight = new Double[_SIZE][_SIZE];
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                weight[i][j] = 0.0;
            }
        }
        neuronId = idCounter;
        idCounter++;
        trainCounter = 0;
        neuronName = "noname";
    }

    public Neuron() {
        Init();
    }

    public Neuron(String name) {
        Init();
        neuronName = name;
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


    public Boolean Train(Double[][] source) {
        //Double d = 1.0 / trainCounter;
        trainCounter++;
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                weight[i][j] += 2 * (source[i][j] - 0.5) / trainCounter;
                if (weight[i][j] > 1.0)
                    weight[i][j] = 1.0;
                if (weight[i][j] < 0.0)
                    weight[i][j] = 0.0;
                //weight[i][j] = source[i][j];
            }
        }
        return true;
    }

    public static void restartCounter() {
        idCounter = 0;
    }

    public Integer getNeuronId() {
        return neuronId;
    }

    public String getNeuronName() {
        return neuronName;
    }

    public void setNeuronId(Integer neuronId) {
        this.neuronId = neuronId;
    }
}

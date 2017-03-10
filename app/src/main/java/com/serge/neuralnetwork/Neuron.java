package com.serge.neuralnetwork;

import android.util.Log;

import javax.xml.transform.Source;

/**
 * Created by serge on 10.03.2017.
 */

public class Neuron {
    private final Integer _SIZE = 15;

    private Integer result;
    private Integer[][] weight;
    private Integer trainigCounter;
    private Integer neuronId;

    public Neuron(int id) {
        weight = new Integer[_SIZE][_SIZE];
        neuronId = id;
    }

    public Neuron(int id, int num) {
        weight = new Integer[_SIZE][_SIZE];
        neuronId = id;
        Train(num);
    }

    int Compare(Integer[][] source) {
        result = 0;
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                result += 1 - Math.abs(weight[i][j] - source[i][j]);
            }
        }
        return result;
    }

    public Boolean Train(Integer[][] source) {
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                weight[i][j] = source[i][j];
            }
        }
        return true;
    }

    public Boolean Train(Integer typeNum) {
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                weight[i][j] = 0;
                if (i < typeNum)
                    weight[i][j] = 1;
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
}

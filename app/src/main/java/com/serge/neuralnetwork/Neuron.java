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

    public Neuron() {
        weight = new Integer[_SIZE][_SIZE];
    }

    int Compare(Integer[][] source) {
        for (int i = 0; i < _SIZE; i++) {
            for (int j = 0; j < _SIZE; j++) {
                result += 1 - Math.abs(weight[i][j] - source[i][j]);
            }
        }
        return 0;
    }
}

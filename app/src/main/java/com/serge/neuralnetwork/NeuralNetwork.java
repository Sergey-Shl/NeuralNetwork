package com.serge.neuralnetwork;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by serge on 10.03.2017.
 */

public class NeuralNetwork {
    private ArrayList<Neuron> neurons;
    private Integer idCounter = 0;

    public NeuralNetwork() {
        neurons = new ArrayList<>();
        neurons.add(new Neuron(idCounter, 0));
        idCounter++;
        neurons.add(new Neuron(idCounter, 5));
        idCounter++;
        neurons.add(new Neuron(idCounter, 10));
        idCounter++;
        neurons.add(new Neuron(idCounter, 15));
        idCounter++;
    }
    
    public void FindBestSolution(Integer[][] source) {
        Integer bestNeuronId = 0;
        Integer bestSolution = 0;
        for (Neuron neuron: neurons) {
            Integer currentSolution = neuron.Compare(source);
            if (bestSolution < currentSolution) {
                bestSolution = currentSolution;
                bestNeuronId = neuron.getNeuronId();
            }
        }
        Log.d("Neuron", bestNeuronId.toString());
    }
}

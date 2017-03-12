package com.serge.neuralnetwork;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by serge on 10.03.2017.
 */

public class NeuralNetwork {
    private ArrayList<Neuron> neurons;

    public NeuralNetwork() {
        Neuron.restartCounter();
        neurons = new ArrayList<>();
    }
    
    public Integer FindBestSolution(Double[][] source) {
        Integer bestNeuronId = 0;
        Double bestSolution = 0.0;
        for (Neuron neuron: neurons) {
            Double currentSolution = neuron.Compare(source);
            if (bestSolution < currentSolution) {
                bestSolution = currentSolution;
                bestNeuronId = neuron.getNeuronId();
            }
        }
        Log.d("Neuron", bestNeuronId.toString());
        return bestNeuronId;
    }

    public void addNeuron()
    {
        neurons.add(new Neuron());
    }

    public void TrainNeuron(int id, Double[][] arr)
    {
        if(id > neurons.size())
            return;
        if(id == neurons.size())
            addNeuron();
        neurons.get(id).Train(arr);
    }
}

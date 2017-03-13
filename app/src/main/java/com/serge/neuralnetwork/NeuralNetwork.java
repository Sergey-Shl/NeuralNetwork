package com.serge.neuralnetwork;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by serge on 10.03.2017.
 */

public class NeuralNetwork {
    private ArrayList<Neuron> neurons;

    public NeuralNetwork() {
        Neuron.restartCounter();
        neurons = new ArrayList<>();
    }
    
    public Integer FindBestSolution(Double[][] input) {
        if (neurons.size() == 0)
            return -1;
        Integer bestNeuronId = 0;
        Double bestSolution = 0.0;
        for (Neuron neuron: neurons) {
            Double currentSolution = neuron.Compare(input);
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

    public void addNeuron(String name)
    {
        neurons.add(new Neuron(name));
    }

    public void addNeuron(Integer neuronId, String neuronName, Double[][] weight, Integer trainCounter)
    {
        neurons.add(new Neuron(neuronId, neuronName, weight, trainCounter));
    }

    public void TrainNeuron(int id, Double[][] arr)
    {
        if(id > neurons.size())
            return;
        if(id == neurons.size())
            addNeuron();
        neurons.get(id).Train(arr);
    }

    public void TrainNeuron(int id, Double[][] arr, String name)
    {
        if(id > neurons.size())
            return;
        if(id == neurons.size())
            addNeuron(name);
        neurons.get(id).Train(arr);
    }

    public String getNeuronName(int id)
    {
        return neurons.get(id).getNeuronName();
    }

    final String FILENAME = "NeuarlNet";
    public void Save(Context context) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(FILENAME,Context.MODE_PRIVATE)));
            for (Neuron n: neurons) {
                bufferedWriter.write(n.toString());
            }
            bufferedWriter.close();
            Log.d("File", "NeuralNet was saved");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load(Context context) {
        String str;
        Log.d("Load", "Loading...");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(FILENAME)));
            int counter = 0;
            int _SIZE = Neuron.getNeuronSize();
            Integer id = 0;
            String name = "";
            Double[][] array = new Double[_SIZE][_SIZE];
            Integer trainingCounter = 0;
            while ((str = bufferedReader.readLine()) != null) {
                switch (counter) {
                    case 1:
                        Log.d("1", str);
                        id = Integer.parseInt(str);
                        break;
                    case 2:
                        Log.d("2", str);
                        name = str;
                        break;
                    case 3:
                        for (int i = 0; i < _SIZE; i++) {
                            Log.d("3." + i, str);
                            array[i] = stringToList(str);
                            str = bufferedReader.readLine();
                        }
                        break;
                    case 4:
                        Log.d("4", str);
                        trainingCounter = Integer.parseInt(str);
                        addNeuron(id, name, array, trainingCounter);
                        break;
                }
                if (counter != 4)
                    counter++;
                else
                    counter = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Double[] stringToList(final String input) {
        String[] elements = input.substring(1, input.length() - 1).split(", ");
        Double[] result = new Double[elements.length];
        int index = 0;
        for (String item : elements) {
            result[index++] = Double.valueOf(item);
        }
        return result;
    }
}

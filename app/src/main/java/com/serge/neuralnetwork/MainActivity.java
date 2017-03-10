package com.serge.neuralnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer[][] source = new Integer[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                source[i][j] = 0;
                if (i < 8)
                    source[i][j] = 1;
            }
        }

        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.FindBestSolution(source);
    }
}

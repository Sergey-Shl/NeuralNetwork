package com.serge.neuralnetwork;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NeuralNetwork neuralNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        neuralNetwork = new NeuralNetwork();

        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);
        Button button4 = (Button) findViewById(R.id.btn4);
        Button button5 = (Button) findViewById(R.id.btn5);
        Button button6 = (Button) findViewById(R.id.btn_true);
        Button button7 = (Button) findViewById(R.id.btn_false);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
    }

    private Integer neuronId = -1;
    private Double[][] input = null;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editText = (EditText) findViewById(R.id.neuron_id);
            TextView textView = (TextView) findViewById(R.id.neuron_name);
            DrawingView drawingView = (DrawingView) findViewById(R.id.drawing_view);
            ImageView imageView = (ImageView) findViewById(R.id.image_view);
            Bitmap bm = null;
            switch (v.getId())
            {
                case R.id.btn1:
                    drawingView.Clear();
                    break;
                case R.id.btn2:
                    bm = drawingView.getDrawingCache();
                    bm = EditImage.TrimBitmap(bm);
                    bm = EditImage.getResizedBitmap(bm, Neuron.getNeuronSize(), Neuron.getNeuronSize());
                    imageView.setImageBitmap(bm);
                    input = EditImage.convertBitmapToArr(bm);

                    neuralNetwork.TrainNeuron(Integer.parseInt(String.valueOf(editText.getText())), input, String.valueOf(textView.getText()));
                    drawingView.Clear();
                    break;
                case R.id.btn3:
                    bm = drawingView.getDrawingCache();
                    bm = EditImage.TrimBitmap(bm);
                    bm = EditImage.getResizedBitmap(bm, Neuron.getNeuronSize(), Neuron.getNeuronSize());
                    imageView.setImageBitmap(bm);

                    input = EditImage.convertBitmapToArr(bm);
                    neuronId = neuralNetwork.FindBestSolution(input);
                    drawingView.Clear();
                    if (neuronId == -1)
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), neuralNetwork.getNeuronName(neuronId), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn4:
                    neuralNetwork.Save(getApplicationContext());
                    break;
                case R.id.btn5:
                    neuralNetwork.Load(getApplicationContext());
                    break;
                case R.id.btn_true:
                    if(neuronId == -1 || input == null)
                        break;
                    neuralNetwork.TrainNeuron(neuronId, input);
                    break;
                case R.id.btn_false:
                    neuronId = -1;
                    break;
                default:
                    break;
            }
        }
    };

}

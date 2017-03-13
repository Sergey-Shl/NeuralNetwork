package com.serge.neuralnetwork;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
            }

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

                    neuralNetwork.TrainNeuron(Integer.parseInt(String.valueOf(editText.getText())), EditImage.convertBitmapToArr(bm), String.valueOf(textView.getText()));
                    drawingView.Clear();
                    break;
                case R.id.btn3:
                    bm = drawingView.getDrawingCache();
                    bm = EditImage.TrimBitmap(bm);
                    bm = EditImage.getResizedBitmap(bm, Neuron.getNeuronSize(), Neuron.getNeuronSize());
                    imageView.setImageBitmap(bm);

                    Integer id = neuralNetwork.FindBestSolution(EditImage.convertBitmapToArr(bm));
                    drawingView.Clear();
                    Toast.makeText(getApplicationContext(), neuralNetwork.getNeuronName(id), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn4:
                    neuralNetwork.Save(getApplicationContext());
                    break;
                case R.id.btn5:
                    Log.d("Btn", "Pressed");
                    neuralNetwork.Load(getApplicationContext());
                    break;
                default:
                    break;
            }
        }
    };

}

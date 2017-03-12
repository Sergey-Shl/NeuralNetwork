package com.serge.neuralnetwork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);

        Double[][] source = new Double[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                source[i][j] = 0.0;
                if (i < 8)
                    source[i][j] = 1.0;
            }
        }

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
                    bm = TrimImage.TrimBitmap(bm);
                    bm = TrimImage.getResizedBitmap(bm, 16, 16);
                    imageView.setImageBitmap(bm);

                    neuralNetwork.TrainNeuron(Integer.parseInt(String.valueOf(editText.getText())), convertBitmapToArr(bm), String.valueOf(textView.getText()));
                    drawingView.Clear();
                    break;
                case R.id.btn3:
                    bm = drawingView.getDrawingCache();
                    bm = TrimImage.TrimBitmap(bm);
                    bm = TrimImage.getResizedBitmap(bm, 16, 16);
                    imageView.setImageBitmap(bm);

                    Integer id = neuralNetwork.FindBestSolution(convertBitmapToArr(bm));
                    drawingView.Clear();
                    Toast.makeText(getApplicationContext(), neuralNetwork.getNeuronName(id), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    public Double[][] convertBitmapToArr(Bitmap bitmap){
        Double[][] arr = new Double[bitmap.getWidth()][bitmap.getHeight()];
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                if(bitmap.getPixel(j, i) == Color.BLACK)
                    arr[j][i] = 1.0;
                else
                    arr[j][i] = 0.0;
            }

        }
        return arr;
    }

}

package com.serge.neuralnetwork;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

/**
 * Created by serge on 12.03.2017.
 */

public class EditImage {

    public static Bitmap TrimBitmap(Bitmap bmp) {
        final int step = 10;
        int imgHeight = bmp.getHeight();
        int imgWidth  = bmp.getWidth();

        //TRIM WIDTH - LEFT
        int startWidth = 0;
        for(int x = 0; x < imgWidth; x = x + step) {
            if (startWidth == 0) {
                for (int y = 0; y < imgHeight; y = y + step) {
                    if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                        startWidth = x;
                        break;
                    }
                }
            } else break;
        }

        //TRIM WIDTH - RIGHT
        int endWidth  = 0;
        for(int x = imgWidth - 1; x >= 0; x = x - step) {
            if (endWidth == 0) {
                for (int y = 0; y < imgHeight; y = y + step) {
                    if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                        endWidth = x;
                        break;
                    }
                }
            } else break;
        }

        //TRIM HEIGHT - TOP
        int startHeight = 0;
        for(int y = 0; y < imgHeight; y = y + step) {
            if (startHeight == 0) {
                for (int x = 0; x < imgWidth; x = x + step) {
                    if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                        startHeight = y;
                        break;
                    }
                }
            } else break;
        }

        //TRIM HEIGHT - BOTTOM
        int endHeight = 0;
        for(int y = imgHeight - 1; y >= 0; y = y - step) {
            if (endHeight == 0 ) {
                for (int x = 0; x < imgWidth; x = x + step) {
                    if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                        endHeight = y;
                        break;
                    }
                }
            } else break;
        }

        return Bitmap.createBitmap(bmp, startWidth, startHeight, endWidth - startWidth, endHeight - startHeight);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public static Double[][] convertBitmapToArr(Bitmap bitmap){
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

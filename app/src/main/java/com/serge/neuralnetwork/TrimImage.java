package com.serge.neuralnetwork;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

/**
 * Created by serge on 12.03.2017.
 */

public class TrimImage {

    public TrimImage() {

    }

    public static Bitmap Trim(Bitmap bmp) {
        int imgHeight = bmp.getHeight();
        int imgWidth = bmp.getWidth();

        int widthStart = imgWidth;
        int widthEnd = 0;
        for (int i = 0; i < imgHeight; i++) {
            for (int j = imgWidth - 1; j >=0 ; j--) {
                if(bmp.getPixel(j, i) != Color.TRANSPARENT && j < widthStart)
                {
                    widthStart = j;
                }
                if(bmp.getPixel(j, i) != Color.TRANSPARENT && j > widthEnd) {
                    widthEnd = j;
                    break;
                }
            }
        }

        int heightStart = imgHeight;
        int heightEnd = 0;
        for (int i = 0; i < imgWidth; i++) {
            for (int j = imgHeight - 1; j >= 0 ; j--) {
                if(bmp.getPixel(i, j) != Color.TRANSPARENT && j < heightStart) {
                    heightStart = j;
                }
                if(bmp.getPixel(i, j) != Color.TRANSPARENT && j > heightEnd) {
                    heightEnd = j;
                    break;
                }
            }
        }

        int finalWidth = widthEnd - widthStart;
        int finalHeight = heightEnd - heightStart;

        return Bitmap.createBitmap(bmp, widthStart, heightStart, finalWidth, finalHeight);
    }

    public static Bitmap TrimBitmap(Bitmap bmp) {
        int imgHeight = bmp.getHeight();
        int imgWidth  = bmp.getWidth();


        //TRIM WIDTH - LEFT
        int startWidth = 0;
        for(int x = 0; x < imgWidth; x++) {
            if (startWidth == 0) {
                for (int y = 0; y < imgHeight; y++) {
                    if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                        startWidth = x;
                        break;
                    }
                }
            } else break;
        }


        //TRIM WIDTH - RIGHT
        int endWidth  = 0;
        for(int x = imgWidth - 1; x >= 0; x--) {
            if (endWidth == 0) {
                for (int y = 0; y < imgHeight; y++) {
                    if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                        endWidth = x;
                        break;
                    }
                }
            } else break;
        }



        //TRIM HEIGHT - TOP
        int startHeight = 0;
        for(int y = 0; y < imgHeight; y++) {
            if (startHeight == 0) {
                for (int x = 0; x < imgWidth; x++) {
                    if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                        startHeight = y;
                        break;
                    }
                }
            } else break;
        }



        //TRIM HEIGHT - BOTTOM
        int endHeight = 0;
        for(int y = imgHeight - 1; y >= 0; y--) {
            if (endHeight == 0 ) {
                for (int x = 0; x < imgWidth; x++) {
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


}

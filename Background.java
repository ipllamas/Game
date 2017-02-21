package com.example.ivan.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Created by Ivan on 12/24/2016.
 */

public class Background {
    Bitmap bitmap;
    Bitmap reversed;

    int imagewidth,imageheight;
    boolean flipped;

    int clip;
    int yStart,yEnd;
    float speed;

    public Background(int width, int height, Bitmap bitmap, int sY, int eY, float speed){
        this.bitmap = bitmap;

        clip = 0;
        yStart = sY * (height / 100);
        yEnd = eY * (height / 100);
        this.speed = speed;

        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, width, (yEnd - yStart), true);

        imagewidth = this.bitmap.getWidth();
        imageheight = this.bitmap.getHeight();

        //matrix to flip image horziontally
        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        reversed = Bitmap.createBitmap(this.bitmap,0,0,imagewidth,imageheight,matrix,true);

    }

    public void update(long fps){

        // Move the clipping position and reverse if necessary
        clip -= speed/fps;
        if (clip >= imagewidth) {
            clip = 0;
            flipped = !flipped;
        } else if (clip <= 0) {
            clip = imagewidth;
            flipped = !flipped;

        }
    }
}

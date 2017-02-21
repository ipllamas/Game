package com.example.ivan.game;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

/**
 * Created by Ivan on 1/2/2017.

 **This class is meant to handle the logic behind animating the player sprite, and can be used to
 **animate other sprites as needed in the future.
 */

public class Animation {
    Bitmap sprites[];
    private int width, height, frameWidth, frameHeight;
    private int currentFrame;
    private int frames;
    private long frameTime;
    private long maxFrameTime;
    private long time;
    private float scaledWidth, scaledHeight;
    private Matrix m;
    Animation(Bitmap sheet, int frames, long maxFrameTime, int row, int col, float tarWidth, float tarHeight){
        this.frames = frames;
        frameTime = 0;
        this.maxFrameTime = maxFrameTime;
        this.width = sheet.getWidth();
        this.height = sheet.getHeight();
        this.frameWidth = this.width / col;
        this.frameHeight = this.height / row;
        this.currentFrame = 0;

        sprites = new Bitmap[frames];

        scaledWidth = tarWidth / frameWidth;
        scaledHeight = tarHeight / frameHeight;
        m = new Matrix();
        m.postScale(scaledWidth,scaledHeight);


        for(int i = 0;i<frames;i++){
            sprites[i] = Bitmap.createBitmap(sheet,(i%5)*frameWidth,(i/5)*frameHeight,frameWidth,frameHeight);
            sprites[i] = Bitmap.createBitmap(sprites[i], 0, 0, frameWidth, frameHeight, m, false);

            //for(int j = 0;j<col;j++){
                //sprites[i+j] = Bitmap.createBitmap(sheet, j*frameWidth,row*frameHeight,frameWidth,frameHeight);
                //sprites[i+j] = Bitmap.createBitmap(sprites[i+j], 0, 0, frameWidth, frameHeight, m, false);
            //}
        }
    }

    public void update(){
        time = System.currentTimeMillis();
        if(time>frameTime + maxFrameTime){
            frameTime = time;
            currentFrame++;
            if(currentFrame>=frames){
                currentFrame = 0;
            }
        }
        //Log.d("CURRENT FRAME"," "+currentFrame);
    }

    public Bitmap getSprite(){
        return sprites[currentFrame];
    }

    public int getFrame(){
        return currentFrame;
    }
}

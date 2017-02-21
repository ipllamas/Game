package com.example.ivan.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Ivan on 1/1/2017.
 */

public class Player {
    boolean playing = false;
    int x, y;

    Animation animation;




    public Player(Bitmap genji, int screenwidth,int screenheight,int x, int y, int width, int height, int frames){
        this.x = x * (screenwidth/100);
        this.y = y * (screenheight/100);
        playing = true;



        animation = new Animation(genji, frames, 100, 2, 5, width, height);
        //Log.d("PLAYER","ANIMATION CREATED");
    }

    public void update(){

        if(playing) {
            animation.update();
        }


    }

    public void draw(Canvas c,Paint paint){
        /*if(animation.getFrame() >=5) {
            c.drawBitmap(animation.getSprite(), x, y + 20, paint);
        }else{*/
            c.drawBitmap(animation.getSprite(),x,y,paint);
        //}

    }

}

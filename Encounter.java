package com.example.ivan.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Ivan on 1/5/2017.

 **This class contains the possible random encounters that the player can trigger
 **throughout the game. This class makes it easy to simply add new scenarios in the future.
 */

public class Encounter {
    private int x;
    private int y;
    private int selection = 0;
    private int speed;
    private Bitmap bitmap;
    boolean complete;

    public Encounter(int screenwidth, int screenheight, int height, Bitmap bitmap, int selection){
        x = screenwidth + screenwidth/4;
        y = height * (screenheight/100);
        speed = 50;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
        complete = false;
        this.selection = selection;
    }

    public void draw(Canvas c){
        c.drawBitmap(bitmap, x, y, null);
    }

    public void update(){
        x -= speed;
    }

    public void completeEnc(){
        complete = true;
    }

    public boolean checkComplete(){
        return complete;
    }

    public int getX(){
        return x;
    }

    public String getScenario(Canvas c, int x, int y,Paint p){
        if(selection==0){
            return null;
        }
        String scenario = "";
        switch (selection){
            case 1:
                scenario = "This is scenario 1";
                c.drawText("Would you like to train",x,y,p);
                c.drawText("with me? It only costs",x,y+55,p);
                c.drawText("10 gold!",x,y+110,p);
                break;
            case 2:
                scenario = "This is scenario 2";
                c.drawText("Would you like to train",x,y,p);
                c.drawText("with me? It only costs",x,y+55,p);
                c.drawText("10 gold!",x,y+110,p);
                break;
            case 3:
                scenario = "This is scenario 3";
                c.drawText("You look strong, young man.",x,y,p);
                c.drawText("Can you open this jar of",x,y+55,p);
                c.drawText("pickles for me?",x,y+110,p);
                break;
            case 4:
                scenario = "This is scenario 4";
                c.drawText("What are you looking at?",x,y,p);
                c.drawText("You don't look so tough.",x,y+55,p);
                c.drawText("Fight me!",x,y+110,p);
                break;
            case 5:
                scenario = "This is scenario 5";
                c.drawText("What are you looking at?",x,y,p);
                c.drawText("You don't look so tough.",x,y+55,p);
                c.drawText("Fight me!",x,y+110,p);
                break;
            case 6:
                scenario = "This is scenario 6";
                c.drawText("What are you looking at?",x,y,p);
                c.drawText("You don't look so tough.",x,y+55,p);
                c.drawText("Fight me!",x,y+110,p);
                break;
        }
        return scenario;
    }


}

package com.example.ivan.game;

import android.content.Context;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Ivan on 11/23/2016.


 **This is the main game loop. it is within this class where the game is initialized
 **with all the proper objects and values. The game then iterates through the update()
 **and onDraw() methods to keep the game running using all the logic to keep the game
 **running as it should.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    BackGroundThread bt;

    boolean playing = true;
    boolean prompt = false;
    boolean paused = false;
    boolean encounterActive = false;

    int screenwidth,screenheight;

    private long gameTimer, lastEncounter;
    private int encounterTimer;
    int encounterSelect;
    Random rand;


    Bitmap stranger = BitmapFactory.decodeResource(getResources(), R.drawable.stranger);
    Bitmap bgcity = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
    Bitmap genji = BitmapFactory.decodeResource(getResources(),R.drawable.ninja);
    Bitmap gameOver = BitmapFactory.decodeResource(getResources(),R.drawable.gameover);
    //Bitmap bgwall = BitmapFactory.decodeResource(getResources(), R.drawable.brickwall);


    Paint p;
    Paint p2;
    Rect button;
    Rect button1,button2,button3,promptButton;

    int gold,str,hp;
    long fps = 30;

    Background city;
    Background wall;
    Encounter encounter;
    Player player;

    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        screenwidth = metrics.widthPixels;
        screenheight = metrics.heightPixels;

        p = new Paint();
        p2 = new Paint();
        gold = 50;
        str = 10;
        hp = 100;

        button1 = new Rect(0,4*screenheight/5,screenwidth/5,screenheight);
        button2 = new Rect(2*screenwidth/5,4*screenheight/5,3*screenwidth/5,screenheight);
        button3 = new Rect(4*screenwidth/5,4*screenheight/5,screenwidth,screenheight);


        rand = new Random();

        city = new Background(screenwidth,screenheight,bgcity,0,75,200);
        //wall = new Background(screenwidth,screenheight,bgwall,55,75,600);
        player = new Player(genji,screenwidth,screenheight,20,60,200,300, 10);

        bt = new BackGroundThread(this);
        Log.d("GameView", "bt starting. width and height is" + getWidth() + " " + getHeight());
        bt.start();
    }



    public void update(){
        if(!paused) {
            city.update(fps);
            // wall.update(fps);
            player.update();
            if (encounterActive) {
                encounter.update();
            }
            if(!encounterActive) {
                checkEncounter();
            }
        }
        if(encounter.getX() <= screenwidth/2 && encounter.checkComplete()==false){
            encounter.completeEnc();
            paused = true;
            button = new Rect();
            button.set(screenwidth/3, 3 * screenheight/5, 2 * screenwidth/3, 4 * screenheight/5);
        }
        if(encounter.getX() < -100){
            encounterActive = false;
        }
        if(hp <= 0){
            playing = false;
            paused = true;
        }

    }

    @Override
    public void onDraw(Canvas c){

        c.drawColor(Color.GRAY);


        p.setColor(Color.argb(255, 249, 129, 0));
        p.setTextSize(40);
        drawBackground(c, city);
       // drawBackground(c, wall);
        if(encounterActive){
            encounter.draw(c);
        }
        player.draw(c,p);
        p.setTextSize(60);
        p.setColor(Color.BLACK);
        c.drawText("HP: "+hp+"   Gold: " + gold + "   Str: " + str, 10, 60, p);


        if(paused){
            p.setColor(Color.BLACK);
            c.drawRect(button.left-10, screenheight/5 -10, 4*screenwidth/5 +10, 2*screenheight/5 +10,p);
            p.setColor(Color.WHITE);
            c.drawRect(button.left, screenheight/5,4*screenwidth/5,2*screenheight/5,p);
            p.setTextSize(60);
            p.setColor(Color.BLACK);
            encounter.getScenario(c,button.left,screenheight/5 +50,p);

            p2.setColor(Color.argb(255,255,153,0));
            p.setTextSize(100);
            p.setColor(Color.argb(255,148,0,211));
            switch (encounterSelect) {
                case 1:
                    c.drawRect(button1,p2);
                    c.drawText("Yes", (button1.left + button1.right) / 2 - 50, (button1.top + button1.bottom) / 2 + 30, p);
                    //c.drawText("Maybe", button2.left + 30, (button2.top + button2.bottom) / 2 + 30, p);
                    c.drawRect(button3,p2);
                    c.drawText("No", (button3.left + button3.right) / 2 - 50, (button3.top + button3.bottom) / 2 + 30, p);
                    break;
                case 2:
                    c.drawRect(button1,p2);
                    c.drawText("Yes", (button1.left + button1.right) / 2 - 50, (button1.top + button1.bottom) / 2 + 30, p);
                    //c.drawText("Maybe", button2.left + 30, (button2.top + button2.bottom) / 2 + 30, p);
                    c.drawRect(button3,p2);
                    c.drawText("No", (button3.left + button3.right) / 2 - 50, (button3.top + button3.bottom) / 2 + 30, p);
                    break;
                case 3:
                    c.drawRect(button1,p2);
                    c.drawText("Yes", (button1.left + button1.right) / 2 - 50, (button1.top + button1.bottom) / 2 + 30, p);
                    //c.drawText("Maybe", button2.left + 30, (button2.top + button2.bottom) / 2 + 30, p);
                    c.drawRect(button3,p2);
                    c.drawText("No", (button3.left + button3.right) / 2 - 50, (button3.top + button3.bottom) / 2 + 30, p);
                    break;
                case 4:
                    c.drawRect(button1,p2);
                    c.drawText("Yes", (button1.left + button1.right) / 2 - 50, (button1.top + button1.bottom) / 2 + 30, p);
                    c.drawRect(button2,p2);
                    c.drawText("Flee", button2.left + 30, (button2.top + button2.bottom) / 2 + 30, p);
                    c.drawRect(button3,p2);
                    p.setTextSize(45);
                    c.drawText("Offer 10G", (button3.left + button3.right) / 2 - 50, (button3.top + button3.bottom) / 2 + 30, p);
                    break;
                case 5:
                    c.drawRect(button1,p2);
                    c.drawText("Yes", (button1.left + button1.right) / 2 - 50, (button1.top + button1.bottom) / 2 + 30, p);
                    c.drawRect(button2,p2);
                    c.drawText("Flee", button2.left + 30, (button2.top + button2.bottom) / 2 + 30, p);
                    c.drawRect(button3,p2);
                    p.setTextSize(45);
                    c.drawText("Offer 10G", (button3.left + button3.right) / 2 - 50, (button3.top + button3.bottom) / 2 + 30, p);
                    break;
                case 6:
                    c.drawRect(button1,p2);
                    c.drawText("Yes", (button1.left + button1.right) / 2 - 50, (button1.top + button1.bottom) / 2 + 30, p);
                    c.drawRect(button2,p2);
                    c.drawText("Flee", button2.left + 30, (button2.top + button2.bottom) / 2 + 30, p);
                    c.drawRect(button3,p2);
                    p.setTextSize(45);
                    c.drawText("Offer 10G", (button3.left + button3.right) / 2 - 50, (button3.top + button3.bottom) / 2 + 30, p);
                    break;
            }
            if(!playing){
                gameOver = Bitmap.createScaledBitmap(gameOver,screenwidth,screenheight,false);
                c.drawBitmap(gameOver,0,0,null);

            }

        }

    }

    public void setFps(long fps){
        this.fps = fps;
    }

    private void drawBackground(Canvas c, Background background) {

        // Make a copy of the relevant background
        Background bg = background;

        // define what portion of images to capture and
        // what coordinates of screen to draw them at

        // For the regular bitmap
        Rect fromRect1 = new Rect(0, 0, bg.imagewidth - bg.clip, bg.imageheight);
        Rect toRect1 = new Rect(bg.clip, bg.yStart, bg.imagewidth, bg.yEnd);

        // For the reversed background
        Rect fromRect2 = new Rect(bg.imagewidth - bg.clip, 0, bg.imagewidth, bg.imageheight);
        Rect toRect2 = new Rect(0, bg.yStart, bg.clip, bg.yEnd);

        //draw the two background bitmaps
        if (!background.flipped) {
            c.drawBitmap(bg.bitmap, fromRect1, toRect1, p);
            c.drawBitmap(bg.reversed, fromRect2, toRect2, p);
        } else {
            c.drawBitmap(bg.bitmap, fromRect2, toRect2, p);
            c.drawBitmap(bg.reversed, fromRect1, toRect1, p);
        }

    }

    private void checkEncounter(){
        rand = new Random();
        gameTimer = System.currentTimeMillis();
        encounterTimer = rand.nextInt((15-10) +1) + 10;
        encounterTimer *= 1000;
        if(gameTimer >= lastEncounter + encounterTimer){
            lastEncounter = gameTimer;
            selectEncounter();
            encounterActive = true;
        }
    }

    private void selectEncounter(){
        rand = new Random();
        encounterSelect = rand.nextInt((6-1)+1)+1;
        switch (encounterSelect){
            case 1:
                encounter = new Encounter(screenwidth, screenheight, 60, stranger, 1);
                Log.d("SELECTION","Selection is "+encounterSelect);
                break;
            case 2:
                encounter = new Encounter(screenwidth, screenheight, 60, stranger, 2);
                Log.d("SELECTION","Selection is "+encounterSelect);
                break;
            case 3:
                encounter = new Encounter(screenwidth, screenheight, 60, stranger, 3);
                Log.d("SELECTION","Selection is "+encounterSelect);
                break;
            case 4:
                encounter = new Encounter(screenwidth, screenheight, 60, stranger, 4);
                Log.d("SELECTION","Selection is "+encounterSelect);
                break;
            case 5:
                encounter = new Encounter(screenwidth, screenheight, 60, stranger, 5);
                Log.d("SELECTION","Selection is "+encounterSelect);
                break;
            case 6:
                encounter = new Encounter(screenwidth, screenheight, 60, stranger, 6);
                Log.d("SELECTION","Selection is "+encounterSelect);
                break;
        }
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        bt.interrupt();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        return;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(paused && playing){
                    switch(encounterSelect){
                        case 1:
                            if (x > button1.left && x <= button1.right && y > button1.top && y <= button1.bottom){
                                if(gold>=10) {
                                    gold -= 10;
                                    str += 10;
                                    paused = false;
                                }
                            }
                            else if (x > button3.left && x <= button3.right && y > button3.top && y <= button3.bottom){
                                paused = false;
                            }
                            break;
                        case 2:
                            if (x > button1.left && x <= button1.right && y > button1.top && y <= button1.bottom){
                                if(gold>=10) {
                                    gold -= 10;
                                    str += 10;
                                    paused = false;
                                }
                            }
                            else if (x > button3.left && x <= button3.right && y > button3.top && y <= button3.bottom){
                                paused = false;
                            }
                            break;
                        case 3:
                            if (x > button1.left && x <= button1.right && y > button1.top && y <= button1.bottom){
                                if(str>=100) {
                                    gold += 100;
                                }
                            }
                            else if (x > button3.left && x <= button3.right && y > button3.top && y <= button3.bottom){
                                paused = false;
                            }
                            break;
                        case 4:
                            if (x > button1.left && x <= button1.right && y > button1.top && y <= button1.bottom){
                                hp -= (50-str);
                                if(hp<0) hp=0;
                                gold += 25;
                                paused = false;
                            }
                            else if(x > button2.left && x <= button2.right && y > button2.top && y <= button2.bottom){
                                hp -= 10;
                                paused = false;
                            }
                            else if (x > button3.left && x <= button3.right && y > button3.top && y <= button3.bottom){
                                gold -= 10;
                                paused = false;
                            }
                            break;
                        case 5:
                            if (x > button1.left && x <= button1.right && y > button1.top && y <= button1.bottom){
                                hp -= (50-str);
                                if(hp<0) hp=0;
                                gold += 25;
                                paused = false;
                            }
                            else if(x > button2.left && x <= button2.right && y > button2.top && y <= button2.bottom){
                                hp -= 10;
                                paused = false;
                            }
                            else if (x > button3.left && x <= button3.right && y > button3.top && y <= button3.bottom){
                                gold -= 10;
                                paused = false;
                            }
                            break;
                        case 6:
                            if (x > button1.left && x <= button1.right && y > button1.top && y <= button1.bottom){
                                hp -= (50-str);
                                if(hp<0) hp=0;
                                gold += 25;
                                paused = false;
                            }
                            else if(x > button2.left && x <= button2.right && y > button2.top && y <= button2.bottom){
                                hp -= 10;
                                paused = false;
                            }
                            else if (x > button3.left && x <= button3.right && y > button3.top && y <= button3.bottom){
                                gold -= 10;
                                paused = false;
                            }
                            break;

                    }
                }
                if(!playing){
                    ((Activity)getContext()).finish();
                }

        }
        return true;
    }

    private void resume(){
        playing = true;
        bt = new BackGroundThread(this);
        bt.start();
    }




}

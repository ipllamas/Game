package com.example.ivan.game;

/**
 * Created by Ivan on 12/23/2016.


 This class makes use of threads to ru
 */

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;



public class BackGroundThread extends Thread{
    GameView gv;

    public BackGroundThread(GameView gv){
        this.gv=gv;
        Log.d("bt","bt created");
    }

    public void run(){
        SurfaceHolder holder = gv.getHolder();
        // Main game loop.
        while(!Thread.interrupted() ) {
            //Want to do game processing in a method call here
            Canvas c = holder.lockCanvas(null);
            try {
                synchronized (holder) {
                    long start = System.currentTimeMillis();
                    gv.update();;
                    gv.onDraw(c);
                    long end = System.currentTimeMillis();
                    gv.setFps(1000/(end-start));
                }

            } catch (Exception e) {
            } finally {
                if (c != null) {
                    holder.unlockCanvasAndPost(c);
                }
            }
            //Set frame rate
            try {
                Thread.sleep(0);
            }catch(InterruptedException e){
                //Thread was interrupted while sleeping.
                return;
            }
        }
    }


}

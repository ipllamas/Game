package com.example.ivan.game;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;


/**
 * Created by Ivan on 12/13/2016.

 **This Android Activity is launched upon proceeding from the Main menu, with logic in
 **the GameView class ending this activity to return to the main menu upon a game over
 **condition.
 */
public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY", "STARTING");
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

    }


}
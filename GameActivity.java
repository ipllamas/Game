package com.example.ivan.game;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;


/**
 * Created by Ivan on 12/13/2016.
 */
public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY", "STARTING");
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

    }


}
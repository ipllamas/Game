package com.example.ivan.game;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Created by Ivan on 12/13/2016.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY","STARTING");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        createButton();
    }

    private void createButton(){
        ImageButton button = (ImageButton) findViewById(R.id.Start);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Button Clicked!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(intent);

            }
        });

    }


}
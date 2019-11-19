package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.example.project.ui.GameRun;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGame (View view) {

        // Create an Intent to start the second activity
        Intent secondIntent = new Intent(this, SecondActivity.class);
        GameRun gameRun = new GameRun();
        secondIntent.putExtra("newGame", gameRun);

        // Start the new activity.
        startActivity(secondIntent);
    }

    public void loadGame (View view) {

        // Create an Intent to start the second activity
        Intent secondIntent = new Intent(this, SecondActivity.class);

        // Start the new activity.
        startActivity(secondIntent);
    }
}

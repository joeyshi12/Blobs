package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGame (View view) {

        // Create an Intent to start the second activity
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("game", "new");

        // Start the new activity.
        startActivity(intent);
    }

    public void loadGame (View view) {

        // Create an Intent to start the second activity
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("game", "load");

        // Start the new activity.
        startActivity(intent);
    }
}

package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;
import com.google.android.material.textfield.TextInputLayout;

public class StartActivity extends AppCompatActivity {
    String name;
    TextInputLayout nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        nameInput = findViewById(R.id.name_input);
    }

    // EFFECTS: begin main activity with a map from "chooseLoad" to false and "name" to name
    public void newGame (View view) {

        // Create an Intent to start the second activity
        Intent intent = new Intent(this, MainActivity.class);
        name = nameInput.getEditText().getText().toString();

        intent.putExtra("chooseLoad", false);
        intent.putExtra("name", name);

        // Start the new activity.
        startActivity(intent);
    }

    // EFFECTS: begin main activity with a map from "chooseLoad" to true
    public void loadGame (View view) {

        // Create an Intent to start the second activity
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("chooseLoad", true);

        // Start the new activity.
        startActivity(intent);
    }
}

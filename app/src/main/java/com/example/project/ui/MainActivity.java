package com.example.project.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.model.SaveAndLoad;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable animationDrawable;
    GameRun gameRun = new GameRun();
    SaveAndLoad saveAndLoad = new SaveAndLoad(gameRun);
    String saveFilePath = "data/saveFile.txt";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        final String value = intent.getStringExtra("game");
        assert value != null;
        if (value.equals("load")) {
            try {
                saveAndLoad.load(saveFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TextView nameDisplay = (TextView) findViewById(R.id.blobName);
        nameDisplay.setText(gameRun.getTamagotchi().getName());

        ImageView imageView = (ImageView)findViewById(R.id.image);
        imageView.setBackgroundResource(R.drawable.animation);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animationDrawable.start();
    }

    public void clickInventory(View view) {
        gameRun.getInventory();
        Toast myToast = Toast.makeText(this,gameRun.getInventory().inventoryReport(),
                Toast.LENGTH_SHORT);
        myToast.show();
    }


    public void clickFeed(View view) {
        Toast myToast = Toast.makeText(this, "stub2",
                Toast.LENGTH_SHORT);
        myToast.show();
        ImageView imageView = (ImageView)findViewById(R.id.image);
        imageView.setBackgroundResource(R.drawable.animation2);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    // EFFECTS: changes hat of tamagotchi to the next available hat in inventory
    public void clickHat(View view) {
        Toast myToast = Toast.makeText(this, "stub3",
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void clickStatus(View view) {
        Toast myToast = Toast.makeText(this, "stub4",
                Toast.LENGTH_SHORT);
        myToast.show();
    }
}

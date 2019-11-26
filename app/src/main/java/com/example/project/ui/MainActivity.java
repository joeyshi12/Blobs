package com.example.project.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.exceptions.ItemNameException;
import com.example.project.exceptions.NegativeAmountException;
import com.example.project.exceptions.NoFoodException;
import com.example.project.model.Inventory;
import com.example.project.model.Tamagotchi;
import com.example.project.model.items.Food;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable blobDrawable;
    Boolean isHatVisable = false;
    GameRun gameRun = new GameRun();
    Intent intent;
    ImageView blobBackground;
    ImageView feedImage;
    String name;
    TextView nameDisplay;
    MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        blobBackground = findViewById(R.id.blobAnimationBackground);
        feedImage = findViewById(R.id.feedImage);
        nameDisplay = findViewById(R.id.nameDisplay);
        playMusic();
        setUpGameRun();
        nameDisplay.setText(name);
        setUpBlobAnimation();
    }

    // Plays blob_music.mp3
    public void playMusic() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.blob_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public void onBackPressed() { }

    // EFFECTS: initialize gameRun
    public void setUpGameRun() {
        boolean value = intent.getBooleanExtra("chooseLoad", false);
        if (value) {
            name = "Rolf";
            Inventory inventory = gameRun.getInventory();
            try {
                inventory.addItemAmount("steak", 99);
                inventory.addItemAmount("apple", 99);
                inventory.addItemAmount("watermelon", 99);
                inventory.addItemAmount("fedora", 1);
            } catch (ItemNameException e) {
                e.printStackTrace();
            }
        } else {
            name = intent.getStringExtra("name");
        }
        Tamagotchi.getInstance().setName(name);
    }

    // EFFECTS: plays blobDrawable animation list
    public void setUpBlobAnimation() {
        blobBackground.setBackgroundResource(R.drawable.animation_veggie);
        blobDrawable = (AnimationDrawable) blobBackground.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        blobDrawable.start();
    }


    // EFFECTS: shows the contents of player inventory
    public void clickInventory(View view) {
        Toast myToast = Toast.makeText(this, gameRun.getInventory().inventoryReport(),
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    // EFFECTS: increments the happy meter of tamagotchi and plays feeding animation
    public void clickFeed(View view) {
        Inventory inventory = gameRun.getInventory();
        try {
            if (inventory.contains("steak")) {
                Tamagotchi.getInstance().feed((Food) inventory.stringToItem("steak"));
                inventory.subtractItemAmount("steak", 1);
                feedImage.setVisibility(View.VISIBLE);
            } else if (inventory.contains("apple")) {
                Tamagotchi.getInstance().feed((Food) inventory.stringToItem("apple"));
                inventory.subtractItemAmount("apple", 1);
                feedImage.setVisibility(View.VISIBLE);
            } else if (inventory.contains("watermelon")) {
                Tamagotchi.getInstance().feed((Food) inventory.stringToItem(
                        "watermelon"));
                inventory.subtractItemAmount("watermelon", 1);
                feedImage.setVisibility(View.VISIBLE);
            }
            timerDelayRemoveView(200, feedImage);
        } catch (ItemNameException | NoFoodException | NegativeAmountException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: removes image v after some time passes
    public void timerDelayRemoveView(float time, final ImageView v) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                v.setVisibility(View.GONE);
            }
        }, (long) time);
    }

    // EFFECTS: changes hat of tamagotchi to the next available hat in inventory
    public void clickHat(View view) {
        ImageView imageView = findViewById(R.id.fedoraImage);
        isHatVisable = !isHatVisable;
        if (isHatVisable) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    // EFFECTS: shows the name and happy meter of tamagotchi
    public void clickStatus(View view) {
        int happyMeter = Tamagotchi.getInstance().getHappyMeter();
        Toast myToast = Toast.makeText(this,
                name + "'s happy meter is " + happyMeter, Toast.LENGTH_SHORT);
        myToast.show();
    }

    // EFFECTS: saves the current gameRun state
    public void clickSave(View view) { }
}

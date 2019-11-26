package com.example.project.model;


import android.os.Build;


import androidx.annotation.RequiresApi;

import com.example.project.exceptions.ItemNameException;
import com.example.project.model.items.Food;
import com.example.project.model.items.Hat;
import com.example.project.model.items.Item;
import com.example.project.ui.GameRun;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveAndLoad implements Saveable, Loadable {
    private Tamagotchi tamagotchi;
    private Inventory inventory;

    public SaveAndLoad(GameRun gameRun) {
        tamagotchi = gameRun.getTamagotchi();
        inventory = gameRun.getInventory();
    }

    // EFFECTS: overwrites the path text file to the field values in current gameRun
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void save(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        writer.println("tamagotchi " + tamagotchi.getName() + " " + tamagotchi.getHat().getName() + " "
                + tamagotchi.getHappyMeter());
        try {
            for (String itemName : inventory.getDefaultItemNames()) {
                Item item = inventory.stringToItem(itemName);
                writer.println(item.getType() + " " + itemName + " " + inventory.numberOf(itemName));
            }
        } catch (ItemNameException e) {
            e.printStackTrace();
        }
        writer.close();
    }

    // EFFECTS: overwrites every field value in gameRun to the value in the path text file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void load(String path) throws IOException {
        List<String> lines = null;
        lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            overwrite(partsOfLine);
        }
    }

    // EFFECTS: splits string at spaces and returns a list of the segmented strings
    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: sets a field value of gameRun to loadData
    private void overwrite(ArrayList<String> loadData) {
        switch (loadData.get(0)) {
            case "tamagotchi":
                overwriteTamagotchi(loadData);
                break;
            case "food":
                overwriteFood(loadData);
                break;
            default:
                overwriteHat(loadData);
                break;
        }
    }

    // EFFECTS: sets the fields of tamagotchi to the values in loadData
    private void overwriteTamagotchi(ArrayList<String> loadData) {
        try {
            String tamaName = loadData.get(1);
            Hat tamaHat = (Hat) inventory.stringToItem(loadData.get(2));
            int tamaHappyMeter = Integer.parseInt(loadData.get(3));
            tamagotchi.setName(tamaName);
            tamagotchi.setHat(tamaHat);
            tamagotchi.setHappyMeter(tamaHappyMeter);
        } catch (ItemNameException e) {
            e.printStackTrace();
            System.out.println("tamagotchi hat name in saveFile is invalid");
        }
    }

    // EFFECTS: sets the amount of food in inventory to the values in loadData
    private void overwriteFood(ArrayList<String> loadData) {
        try {
            Food food = (Food) inventory.stringToItem(loadData.get(1));
            food.setAmount(Integer.parseInt(loadData.get(2)));
        } catch (ItemNameException e) {
            e.printStackTrace();
            System.out.println("food name in saveFile is invalid");
        }
    }

    // EFFECTS: sets the amount of hat in inventory to the values in loadData
    private void overwriteHat(ArrayList<String> loadData) {
        try {
            Hat hat = (Hat) inventory.stringToItem(loadData.get(1));
            hat.setAmount(Integer.parseInt(loadData.get(2)));
        } catch (ItemNameException e) {
            e.printStackTrace();
            System.out.println("hat name in saveFile is invalid");
        }
    }
}

package com.example.project.ui;


import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.exceptions.ItemNameException;
import com.example.project.exceptions.NegativeAmountException;
import com.example.project.exceptions.NoFoodException;
import com.example.project.exceptions.NoHatException;
import com.example.project.model.Inventory;
import com.example.project.model.SaveAndLoad;
import com.example.project.model.Tamagotchi;
import com.example.project.model.items.Food;
import com.example.project.model.items.Hat;
import com.example.project.network.Weather;

import java.io.IOException;
import java.util.Scanner;

public class GameRun {
    private Tamagotchi tamagotchi;
    private Inventory inventory = new Inventory();
    private Scanner input = new Scanner(System.in);
    private SaveAndLoad saveAndLoad = new SaveAndLoad(this);
    private Weather weather = new Weather();

    public GameRun() {
        inventory.setDefault();
        tamagotchi = Tamagotchi.getInstance();
    }

    // EFFECTS: calls createTamagotchi if input is "create", load if input is "load", or repeat loop if otherwise
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void intro() throws IOException {
        while (true) {
            System.out.println("Enter: new or load");
            String choice = input.nextLine();
            if (choice.equals("new")) {
                createTamagotchi();
                weather.addObserver(tamagotchi);
                weather.notifyObserver();
                break;
            } else if (choice.equals("load")) {
                saveAndLoad.load("data/saveFile.txt");
                break;
            } else {
                System.out.println("Not a valid input");
            }
        }
    }

    // EFFECTS: sets tamagotchi name to inputted name
    private void createTamagotchi() {
        System.out.println("Name your Tamagotchi");
        String tamaName = input.nextLine();
        tamagotchi.setName(tamaName);
        System.out.println("Your Tama has been named " + tamaName + "!");
    }

    // EFFECTS: prints fields of tamagotchi and inventory
    private void statusMessage() {
        System.out.println(tamagotchi.getName() + " is wearing a " + tamagotchi.getHat().getName());
        System.out.println("Happy Meter: " + tamagotchi.getHappyMeter());
        System.out.println("Today's weather is " + tamagotchi.getColour());
        System.out.println("\n");
    }

    // EFFECTS: tamagotchi changes to hat with the inputted name
    private void changeTamaHat() {
        System.out.println("Choose a hat");
        String choice = input.nextLine();
        try {
            Hat hatChoice = (Hat) inventory.stringToItem(choice);
            tamagotchi.changeHat(hatChoice);
        } catch (ItemNameException e) {
            System.out.println("Not a valid choice");
        } catch (NoHatException e) {
            System.out.println("Hat is not available");
        } finally {
            System.out.println("\n");
        }
    }

    // EFFECTS: feeds food with the inputted name to tamagotchi and subtracts 1 amount from food
    private void feedTama() {
        System.out.println("Choose a food");
        String choice = input.nextLine();
        try {
            Food foodChoice = (Food) inventory.stringToItem(choice);
            tamagotchi.feed(foodChoice);
            inventory.subtractItemAmount(choice, 1);
        } catch (ItemNameException e) {
            System.out.println("Not a valid choice");
        } catch (NoFoodException | NegativeAmountException e) {
            System.out.println("Food is not available");
        } finally {
            System.out.println("\n");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void main() throws IOException {
        intro();
        while (true) {
            System.out.println("Enter: status, feed, change hat, save, exit");
            String choice = input.nextLine();
            if (choice.equals("status")) {
                statusMessage();
            } else if (choice.equals("feed")) {
                feedTama();
            } else if (choice.equals("change hat")) {
                changeTamaHat();
            } else if (choice.equals("save")) {
                saveAndLoad.save("data/saveFile.txt");
            } else if (choice.equals("exit")) {
                return;
            } else {
                System.out.println("Choose one of the valid options");
            }
        }
    }

    // GETTERS
    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }

    public Inventory getInventory() {
        return inventory;
    }
}


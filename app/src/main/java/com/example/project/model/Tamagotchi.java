package com.example.project.model;


import com.example.project.exceptions.NoFoodException;
import com.example.project.exceptions.NoHatException;
import com.example.project.model.items.Food;
import com.example.project.model.items.Hat;

public class Tamagotchi implements Observer {
    private String name = "blank";
    private Hat hat = new Hat("cap", 1);
    private int happyMeter = 10;

    /** private constructor to prevent others from instantiating this class */
    private Tamagotchi() { }

    /** Create an instance of the class at the time of class loading */
    private static final Tamagotchi instance = new Tamagotchi();

    /** Provide a global point of access to the instance */
    public static Tamagotchi getInstance() {
        return instance;
    }

    // MODIFIES: this
    // EFFECTS: returns true if food amount >= 1 and increases happyMeter by food points; else, return false
    public void feed(Food food) throws NoFoodException {
        if (food.getAmount() >= 1) {
            happyMeter += food.getPoints();
        } else {
            throw new NoFoodException();
        }
    }

    // MODIFIES: this
    // EFFECTS: returns true if hat amount >= 1 and replaces hat; else, return false
    public void changeHat(Hat hat) throws NoHatException {
        if (hat.getAmount() >= 1) {
            this.hat = hat;
        } else {
            throw new NoHatException();
        }
    }



    @Override
    public void update(String weather) {
        if (weather.equals("clear")) {
            System.out.println("today's a clear day");
        } else if (weather.equals("clouds")) {
            System.out.println("today's cloudy");
        } else {
            System.out.println("today is rainy");
        }
    }



    // GETTERS
    public String getName() {
        return name;
    }

    public Hat getHat() {
        return hat;
    }

    public int getHappyMeter() {
        return happyMeter;
    }



    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setHat(Hat hat) {
        this.hat = hat;
    }

    public void setHappyMeter(int happyMeter) {
        this.happyMeter = happyMeter;
    }
}

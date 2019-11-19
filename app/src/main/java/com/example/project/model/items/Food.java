package com.example.project.model.items;

import com.example.project.model.items.Item;

public class Food extends Item {
    private int points;

    public Food(String name, int amount, int points) {
        super(name, amount);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String getType() {
        return "food";
    }
}

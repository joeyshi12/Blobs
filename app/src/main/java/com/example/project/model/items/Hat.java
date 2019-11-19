package com.example.project.model.items;

public class Hat extends Item {

    public Hat(String name, int amount) {
        super(name, amount);
    }

    @Override
    public String getType() {
        return "hat";
    }
}

package com.example.project.model.items;


import com.example.project.exceptions.NegativeAmountException;

public abstract class Item {
    protected String name;
    private int amount;

    Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    // MODIFIES: this
    // EFFECTS: adds amount from item
    public void add(int addAmount) {
        amount = amount + addAmount;
    }

    // MODIFIES: this
    // EFFECTS: subtracts amount from item
    public void subtract(int subtractAmount) throws NegativeAmountException {
        if (amount >= subtractAmount) {
            amount = amount - subtractAmount;
        } else {
            throw new NegativeAmountException();
        }
    }

    // GETTERS
    public abstract String getType();

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    // SETTERS
    public void setAmount(int amount) {
        this.amount = amount;
    }
}

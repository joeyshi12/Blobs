package com.example.project.model;

import com.example.project.exceptions.ItemNameException;
import com.example.project.exceptions.NegativeAmountException;
import com.example.project.model.items.Food;
import com.example.project.model.items.Hat;
import com.example.project.model.items.Item;


import java.util.*;

public class Inventory {
    private Map<String, Item> inventory;
    private List<String> defaultItemNames = new ArrayList<>();

    public Inventory() {
        inventory = new HashMap<>();
    }

    public void setDefault() {
        defaultItemNames.add("steak");
        defaultItemNames.add("apple");
        defaultItemNames.add("watermelon");
        defaultItemNames.add("fedora");
        defaultItemNames.add("cap");
        inventory.put("steak", new Food("steak", 1, 4));
        inventory.put("apple", new Food("apple", 1, 2));
        inventory.put("watermelon", new Food("watermelon", 1, 1));
        inventory.put("fedora", new Hat("fedora", 0));
        inventory.put("cap", new Hat("cap", 1));
    }

    // MODIFIES: this
    // EFFECTS: adds one to the item amount with name string
    public void addItemAmount(String itemName, int amount) throws ItemNameException {
        Item item = stringToItem(itemName);
        item.add(amount);
    }

    // MODIFIES: this
    // EFFECTS: subtracts 1 from item amount
    public void subtractItemAmount(String itemName, int amount) throws NegativeAmountException, ItemNameException {
        Item item = stringToItem(itemName);
        item.subtract(amount);
    }

    // EFFECTS: prints all items in inventory
    public void printInventory() {
        for (Map.Entry<String, Item> entry : inventory.entrySet()) {
            Item item = entry.getValue();
            System.out.println(item.getName() + ": " + item.getAmount());
        }
    }


    // Helper Methods

    // EFFECTS: returns inventory item with given item name
    public Item stringToItem(String itemName) throws ItemNameException {
        for (Map.Entry<String, Item> entry : inventory.entrySet()) {
            Item item = entry.getValue();
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        throw new ItemNameException();
    }

    // EFFECTS: returns amount of inventory items with given item name
    public int numberOf(String itemName) throws ItemNameException {
        Item item = stringToItem(itemName);
        return item.getAmount();
    }

    // EFFECTS: returns true if amount of inventory items with given item name is greater than 0; else, false
    public Boolean contains(String itemName) throws ItemNameException {
        return numberOf(itemName) > 0;
    }

    public Boolean isEmpty() {
        return inventory.isEmpty();
    }

    List<String> getDefaultItemNames() {
        return defaultItemNames;
    }

    public void addItemType(String itemName, Item item) {
        inventory.put(itemName, item);
    }
}

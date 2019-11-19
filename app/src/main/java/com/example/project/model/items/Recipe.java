package com.example.project.model.items;

import com.example.project.exceptions.ItemNameException;
import com.example.project.exceptions.NoItemException;
import com.example.project.model.Inventory;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {
    String recipeName;
    List<Ingredient> ingredients;

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
        ingredients = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Recipe recipe = (Recipe) o;
        return Objects.equals(recipeName, recipe.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeName);
    }

    // EFFECTS: adds ingredient to ingredients and adds this recipe to ingredient
    public void addIngredient(Ingredient ingredient) {
        if (!this.ingredients.contains(ingredient)) {
            ingredients.add(ingredient);
            ingredient.addRecipe(this);
        }
    }

    // EFFECTS: returns true if inventory has proper ingredients for cooking the dish; otherwise, return false
    //          throws NoItemException if inventory is empty
    public Boolean canCook(Inventory inventory) throws NoItemException, ItemNameException {
        if (inventory.isEmpty()) {
            throw new NoItemException();
        } else {
            for (Ingredient ingredient : ingredients) {
                if (!inventory.contains(ingredient.getName())) {
                    return false;
                }
            }
            return true;
        }
    }

    // EFFECTS: returns true if recipe contains ingredient; otherwise, returns false
    public Boolean containsIngredient(String ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns name of recipe
    public String getName() {
        return recipeName;
    }
}

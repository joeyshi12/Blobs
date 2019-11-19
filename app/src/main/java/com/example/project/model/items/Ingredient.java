package com.example.project.model.items;

import com.example.project.model.items.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Ingredient extends Item {
    private List<Recipe> recipes;

    public Ingredient(String name, int amount) {
        super(name, amount);
        recipes = new ArrayList<>();
    }

    // EFFECTS: adds recipe to recipes and adds this ingredient to recipe
    public void addRecipe(Recipe recipe) {
        if (!this.recipes.contains(recipe)) {
            recipes.add(recipe);
            recipe.addIngredient(this);
        }
    }

    // EFFECTS: returns true if ingredient is found in recipe; otherwise, returns false
    public Boolean inRecipe(String recipeName) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(recipeName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "ingredient";
    }
}

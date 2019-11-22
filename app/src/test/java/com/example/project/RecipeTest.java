package com.example.project;


import com.example.project.exceptions.ItemNameException;
import com.example.project.exceptions.NoItemException;
import com.example.project.model.Inventory;
import com.example.project.model.items.Ingredient;
import com.example.project.model.items.Recipe;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class RecipeTest {
    Recipe beefRiceBall;
    Inventory inventory;

    @Before
    public void setUp() {
        beefRiceBall = new Recipe("beef rice ball");
        inventory = new Inventory();
    }

    @Test
    public void testAddIngredient() {
        Ingredient steak = new Ingredient("steak", 1);
        Ingredient rice = new Ingredient("rice", 1);
        Ingredient seaweed = new Ingredient("seaweed", 1);
        beefRiceBall.addIngredient(steak);
        beefRiceBall.addIngredient(rice);
        beefRiceBall.addIngredient(seaweed);

        assertTrue(beefRiceBall.containsIngredient("steak"));
        assertTrue(beefRiceBall.containsIngredient("rice"));
        assertTrue(beefRiceBall.containsIngredient("seaweed"));

        assertTrue(steak.inRecipe("beef rice ball"));
        assertTrue(rice.inRecipe("beef rice ball"));
        assertTrue(seaweed.inRecipe("beef rice ball"));
    }

    @Test
    public void testCanCook() {
        Ingredient steak = new Ingredient("steak", 1);
        Ingredient rice = new Ingredient("rice", 1);
        Ingredient seaweed = new Ingredient("seaweed", 1);
        beefRiceBall.addIngredient(steak);
        beefRiceBall.addIngredient(rice);
        beefRiceBall.addIngredient(seaweed);
        inventory.addItemType("steak", steak);
        inventory.addItemType("rice", rice);
        inventory.addItemType("seaweed", seaweed);
        try {
            assertTrue(beefRiceBall.canCook(inventory));
        } catch (NoItemException e) {
            fail("NoItemException");
        } catch (ItemNameException e) {
            fail("ItemNameException");
        }
    }

    @Test
    public void testCookNoItemException() {
        Ingredient steak = new Ingredient("steak", 1);
        Ingredient rice = new Ingredient("rice", 1);
        Ingredient seaweed = new Ingredient("seaweed", 1);
        beefRiceBall.addIngredient(steak);
        beefRiceBall.addIngredient(rice);
        beefRiceBall.addIngredient(seaweed);
        try {
            beefRiceBall.canCook(inventory);
            fail();
        } catch (NoItemException e) {
        } catch (ItemNameException e) {
            fail("ItemNameException");
        }
    }
}

package com.example.project;


import com.example.project.exceptions.NoFoodException;
import com.example.project.exceptions.NoHatException;
import com.example.project.model.Tamagotchi;
import com.example.project.model.items.Food;
import com.example.project.model.items.Hat;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TamagotchiTest {
    private Tamagotchi testTama;

    @Before
    public void setUp() {
        testTama = Tamagotchi.getInstance();
        testTama.setHappyMeter(10);
        testTama.setHat(new Hat("cappy", 1));
    }

    @Test
    public void testFeedAvailableFood() {
        Food testFood = new Food("testFood", 0, 5);
        testFood.setAmount(10);
        assertEquals(10, testTama.getHappyMeter());
        try {
            testTama.feed(testFood);
            assertEquals(15, testTama.getHappyMeter());
        } catch (NoFoodException e) {
            fail("No Food Exception");
        }
    }

    @Test
    public void testFeedUnavailableFood() {
        Food testFood = new Food("testFood", 0, 5);
        assertEquals(10, testTama.getHappyMeter());
        try {
            testTama.feed(testFood);
            fail("No Exception");
        } catch (NoFoodException e) {
            assertEquals(10, testTama.getHappyMeter());
        }
    }

    @Test
    public void testChangeToAvailableHat() {
        Hat testHat = new Hat("testHat", 0);
        testHat.setAmount(1);
        assertEquals("cappy", testTama.getHat().getName());
        assertEquals(1, testTama.getHat().getAmount());
        try {
            testTama.changeHat(testHat);
            assertEquals("testHat", testTama.getHat().getName());
            assertEquals(1, testTama.getHat().getAmount());
        } catch (NoHatException e) {
            fail("No Hat Exception");
        }
    }

    @Test
    public void testChangeToUnavailableHat() {
        Hat testHat = new Hat("testHat", 0);
        assertEquals("cappy", testTama.getHat().getName());
        assertEquals(1, testTama.getHat().getAmount());
        try {
            testTama.changeHat(testHat);
            fail("No Exception");
        } catch (NoHatException e) {
            assertEquals("cappy", testTama.getHat().getName());
            assertEquals(1, testTama.getHat().getAmount());
        }
    }

    @Test
    public void testSetName() {
        assertEquals("blank", testTama.getName());
        testTama.setName("nameReset");
        assertEquals("nameReset", testTama.getName());
    }

    @Test
    public void testSetHat() {
        assertEquals("cappy", testTama.getHat().getName());
        assertEquals(1, testTama.getHat().getAmount());
        testTama.setHat(new Hat("hatReset", 2));
        assertEquals("hatReset", testTama.getHat().getName());
        assertEquals(2, testTama.getHat().getAmount());
    }

    @Test
    public void testSetHappyMeter() {
        int happy = testTama.getHappyMeter();
        assertEquals(10, testTama.getHappyMeter());
        testTama.setHappyMeter(15);
        assertEquals(15, testTama.getHappyMeter());
    }
}

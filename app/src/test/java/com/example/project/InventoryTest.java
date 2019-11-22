package com.example.project;


import com.example.project.exceptions.ItemNameException;
import com.example.project.exceptions.NegativeAmountException;
import com.example.project.model.Inventory;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

public class InventoryTest {
    private Inventory testInventory;

    @Before
    public void setUp() {
        testInventory = new Inventory();
        testInventory.setDefault();
    }

    @Test
    public void testAddOneValidItemToEmpty() {
        try {
            assertEquals(0, testInventory.numberOf("fedora"));
            assertFalse(testInventory.contains("fedora"));
            testInventory.addItemAmount("fedora", 1);
            assertEquals(1, testInventory.numberOf("fedora"));
            assertTrue(testInventory.contains("fedora"));
        } catch (ItemNameException e) {
            fail("Invalid Item Name Exception");
        }
    }

    @Test
    public void testAddOneValidItemToNotEmpty() {
        try {
            assertEquals(1, testInventory.numberOf("cap"));
            assertTrue(testInventory.contains("cap"));
            testInventory.addItemAmount("cap", 1);
            assertEquals(2, testInventory.numberOf("cap"));
            assertTrue(testInventory.contains("cap"));
        } catch (ItemNameException e) {
            fail("Invalid Item Name Exception");
        }
    }

    @Test
    public void testAddOneInvalidItem() {
        try {
            testInventory.addItemAmount("invalidItem", 1);
            fail("No Exception");
        } catch (ItemNameException e) {
        }
    }

    @Test
    public void testSubtractOneValidItemFromOne() {
        try {
            assertEquals(1, testInventory.numberOf("apple"));
            assertTrue(testInventory.contains("apple"));
            testInventory.subtractItemAmount("apple", 1);
            assertEquals(0, testInventory.numberOf("apple"));
            assertFalse(testInventory.contains("apple"));
        } catch (NegativeAmountException e) {
            fail("Negative Amount Exception");
        } catch (ItemNameException e) {
            fail("Invalid Item Name Exception");
        }
    }


    @Test
    public void testSubtractOneValidItemFromEmpty() {
        try {
            assertEquals(0, testInventory.numberOf("fedora"));
            assertFalse(testInventory.contains("fedora"));
            testInventory.subtractItemAmount("fedora", 1);
            fail("No Exception");
        } catch (ItemNameException e) {
            fail("Invalid Item Name Exception");
        } catch (NegativeAmountException e) {
            try {
                assertEquals(0, testInventory.numberOf("fedora"));
                assertFalse(testInventory.contains("fedora"));
            } catch (ItemNameException ex) {
                fail("Invalid Item Name Exception");
            }
        }
    }

    @Test
    public void testSubtractOneInvalidItem() {
        try {
            testInventory.subtractItemAmount("invalidItem", 1);
            fail("No Exception");
        } catch (ItemNameException | NegativeAmountException e) {
        }
    }
}

package com.example.project;

import com.example.project.exceptions.ItemNameException;
import com.example.project.model.Inventory;
import com.example.project.model.SaveAndLoad;
import com.example.project.model.Tamagotchi;
import com.example.project.model.items.Hat;
import com.example.project.ui.GameRun;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;


public class SaveAndLoadTest {
    private GameRun testGame;
    private Tamagotchi testTama;
    private Inventory testInventory;
    private SaveAndLoad testSaveAndLoad;
    private String testFilePath =
            "C:\\Users\\j\\Desktop\\Applications\\Blobs\\data\\testFile.txt";
    private String testFileCopyPath =
            "C:\\Users\\j\\Desktop\\Applications\\Blobs\\data\\testFileCopy.txt";

    @Before
    public void setUp() {
        testGame = new GameRun();
        testTama = testGame.getTamagotchi();
        testTama.setName("blank");
        testTama.setHat(new Hat("cap", 1));
        testInventory = testGame.getInventory();
        testSaveAndLoad = new SaveAndLoad(testGame);
    }

    @Test
    public void testSave() throws IOException {
        List<String> testData = Files.readAllLines(Paths.get(testFilePath));
        List<String> testDataCopy = Files.readAllLines(Paths.get(testFileCopyPath));
        for (int i = 0; i < testData.size(); i++) {
            assertEquals(testData.get(i), testDataCopy.get(i));
        }
        testSaveAndLoad.save(testFilePath);
        testData = Files.readAllLines(Paths.get(testFilePath));

        assertEquals("tamagotchi blank cap 10", testData.get(0));
        assertEquals("food steak 1", testData.get(1));
        assertEquals("food apple 1", testData.get(2));
        assertEquals("food watermelon 1", testData.get(3));
        assertEquals("hat fedora 0", testData.get(4));
        assertEquals("hat cap 1", testData.get(5));

        testSaveAndLoad.load(testFileCopyPath);
        testSaveAndLoad.save(testFilePath);
    }

    @Test
    public void testLoad() throws IOException {
        assertEquals("blank", testTama.getName());
        assertEquals("cap", testTama.getHat().getName());
        assertEquals(10, testTama.getHappyMeter());
        try {
            assertEquals(1, testInventory.numberOf("steak"));
            assertEquals(1, testInventory.numberOf("apple"));
            assertEquals(1, testInventory.numberOf("watermelon"));
            assertEquals(0, testInventory.numberOf("fedora"));
            assertEquals(1, testInventory.numberOf("cap"));
            testSaveAndLoad.load(testFilePath);
            assertEquals("test", testTama.getName());
            assertEquals("fedora", testTama.getHat().getName());
            assertEquals(10, testTama.getHappyMeter());
            assertEquals(10, testInventory.numberOf("steak"));
            assertEquals(10, testInventory.numberOf("apple"));
            assertEquals(10, testInventory.numberOf("watermelon"));
            assertEquals(10, testInventory.numberOf("cap"));
            assertEquals(10, testInventory.numberOf("fedora"));
        } catch (ItemNameException e) {
            fail("Invalid Item Name Exception");
        }
    }
}

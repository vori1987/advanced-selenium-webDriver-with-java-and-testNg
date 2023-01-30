package com.herokuapp.theinternet.base;

import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest {

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
@DataProvider(name = "files")
    protected static Object[][] files() {
        return new Object[][]{
                {1, "test.jpg"},
                {2, "test.png"},
                {3, "test.bmp"},
                {4, "test.txt"},
        };
    }
}
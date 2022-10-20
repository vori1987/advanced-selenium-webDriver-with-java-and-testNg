package com.herokuapp.theinternet.loginpagetest;

import com.herokuapp.theinternet.base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLogInTests extends TestUtilities {

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 2, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedMessage) {
       log.info("Starting negativeLoginTest with" + username + " and " + password);
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
       log.info("Page is opened.");
        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(username);
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(password);
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();
        WebElement unsuccessfulLogInMessage = driver.findElement(By.cssSelector("div#flash"));
        String actualMessage = unsuccessfulLogInMessage.getText();
        Assert.assertTrue(unsuccessfulLogInMessage.isDisplayed(), "Unsuccessful popUp is not visible");
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Actual message does not contain expected message.\nActualMessage: "
                        + actualMessage + "\nExpectedMessage: " + expectedMessage);
    }
}


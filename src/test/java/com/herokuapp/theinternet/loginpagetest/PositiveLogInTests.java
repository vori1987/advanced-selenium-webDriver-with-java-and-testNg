package com.herokuapp.theinternet.loginpagetest;

import com.herokuapp.theinternet.base.TestUtilities;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PositiveLogInTests extends TestUtilities {

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 1, groups = {"positiveTests", "smokeTests"})
    public void positiveLoginTest() throws InterruptedException {
       log.info("Starting login test");
        //        open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
       log.info("Page is opened.");
        //                enter user name
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        //                enter pas
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        //explicit wait (wait when element will be clickable) - many times using.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        sleep(3000);

        //                        click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();
        //logOut button
        WebElement logOutButton = driver.findElement(By.xpath("//a[@class = 'button secondary radius']"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Log Out button is not visible");
        //                verifications:
        //        new url
        Thread.sleep(3000);
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualString = driver.getCurrentUrl();
        Assert.assertEquals(actualString, expectedUrl, "Actual page url is not the same as expected");
        //                logout button is visible
        //                successful login message
        WebElement succesLogInMessage = driver.findElement(By.cssSelector("div#flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = succesLogInMessage.getText();
        //        Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Actual message does not contain expected message.\nActualMessage: "
                        + actualMessage + "\nExpectedMessage: " + expectedMessage);
    }
}


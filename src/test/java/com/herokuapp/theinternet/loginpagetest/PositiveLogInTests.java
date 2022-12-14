package com.herokuapp.theinternet.loginpagetest;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
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
    public void loginTest(){
        log.info("Starting login test");
        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");

        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());
        Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "LogOut button is not visible");

        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualString = driver.getCurrentUrl();
        Assert.assertEquals(actualString, expectedUrl, "Actual page url is not the same as expected");

        WebElement succesLogInMessage = driver.findElement(By.cssSelector("div#flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = secureAreaPage.getSuccessMessageText();

        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "ActualSuccessMessage does not contain expectedSuccessMessage.\nActualSuccessMessage: "
                        + actualMessage + "\nExpectedSuccessMessage: " + expectedMessage);
    }
}


package com.herokuapp.theinternet.loginpagetest;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PositiveLogInTests extends TestUtilities {

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 1, groups = {"positiveTests", "smokeTests"})
    public void loginTest() {
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        takeScreenshot("WelcomePage opened");
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        takeScreenshot("LoginPage opened");
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");
        takeScreenshot("SecureAreaPage opened");
        //verification
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


package com.herokuapp.theinternet.loginpagetest;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLogInTests extends TestUtilities {

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 1, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        log.info("Starting negativeLoginTest with" + username + " and " + password);
        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        //click on form authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        //execute negative login
        loginPage.negativeLogin(username, password);
        //wait for error msg
        loginPage.waitForErrorMessage();
        String message = loginPage.getErrorMessageText();
        //verification
        Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
    }
}


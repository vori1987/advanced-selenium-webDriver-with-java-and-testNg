package com.herokuapp.theinternet.loginpagetest;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLogInTests extends TestUtilities {

    @Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLogInTest(Map<String, String> testData) {
        //Data
        String no = testData.get("no");
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        log.info("Starting negativeLoginTest #" + no + " for " + description);
        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        //click on form authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        takeScreenshot("LoginPage opened");
        //execute negative login
        loginPage.negativeLogin(username, password);
        //wait for error msg
        loginPage.waitForErrorMessage();
        String message = loginPage.getErrorMessageText();
        //verification
        Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
    }
}


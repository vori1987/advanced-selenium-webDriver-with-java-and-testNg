package com.herokuapp.theinternet.alerts;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JsAlertsPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AlertsTests extends TestUtilities {

    @Test
    public void jsAlertTest() {
        log.info("Starting jsAlertTest");
        SoftAssert softAssert = new SoftAssert();
        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        //click on alertsPage link
        JsAlertsPage alertsPage = welcomePage.clickJsAlertsLink();
        //click openJSAlert button
        alertsPage.openjsAlert();
        //get alert text
        String alertMessage = alertsPage.getAlertText();
        //click OK button
        alertsPage.acceptAlert();
        //get result text
        String result = alertsPage.getResultText();
        //verifications
        softAssert.assertTrue(alertMessage.equals("I am a JS Alert"),
                "Alert message is not expected. \nShould be 'I am a JS Alert', but was" + alertMessage);
        softAssert.assertTrue(result.equals("You successfully clicked an alert"),
                "Result is not expected. \nShould be 'You successfully clicked an alert', but was" + result);
        softAssert.assertAll();
    }

    @Test
    public void jsDismissTest() {
        log.info("Starting jsDismissTest");
        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        //click on alertsPage link
        JsAlertsPage alertsPage = welcomePage.clickJsAlertsLink();
        //click openJSConfirm button
        alertsPage.openjsConfirm();
        //get alert text
        String alertMessage = alertsPage.getAlertText();
        //click Cancel button
        alertsPage.dismissAlert();
        //get result text
        String result = alertsPage.getResultText();
        //verifications
        Assert.assertTrue(alertMessage.equals("I am a JS Confirm"),
                "Alert message is not expected. \nShould be 'I am a JS Confirm', but was" + alertMessage);
        Assert.assertTrue(result.equals("You clicked: Cancel"),
                "Result is not expected. \nShould be 'You clicked: Cancel', but was" + result);
    }

    @Test
    public void jsPromptTest() {
        log.info("Starting jsPromptTest");
        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        //click on alertsPage link
        JsAlertsPage alertsPage = welcomePage.clickJsAlertsLink();
        //click openJSPrompt button
        alertsPage.openjsPrompt();
        //get alert text
        String alertMessage = alertsPage.getAlertText();
        //Type text into alert
        alertsPage.typeTextIntoAlert("Hello Alert, it's Vova here");
        //get result text
        String result = alertsPage.getResultText();
        //verifications
        Assert.assertEquals(alertMessage, "I am a JS prompt",
                "Alert message is not expected. \nShould be 'I am a JS prompt', but was" + alertMessage);
        Assert.assertEquals(result, "You entered: Hello Alert, it's Vova here",
                "Result is not expected. \nShould be 'You clicked: Cancel', but was '" + result + "'");
    }
}

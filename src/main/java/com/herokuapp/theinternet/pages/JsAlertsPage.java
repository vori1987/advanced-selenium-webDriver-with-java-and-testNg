package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JsAlertsPage extends BasePageObject {

    private final By jsAlert = By.cssSelector("[onclick='jsAlert\\(\\)']");
    private final By jsConfirm = By.cssSelector("[onclick='jsConfirm\\(\\)']");
    private final By jsPrompt = By.cssSelector("[onclick='jsPrompt\\(\\)']");
    private final By resultTextLocator = By.id("result");

    public JsAlertsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openjsAlert() {
        log.info("Clicking jsAlert button");
        click(jsAlert);
    }

    public void openjsConfirm() {
        log.info("Clicking jsConfirm button");
        click(jsConfirm);
    }

    public void openjsPrompt() {
        log.info("Clicking jsPrompt button");
        click(jsPrompt);
    }

    public void acceptAlert() {
        log.info("Switching to alert and pressing OK");
        Alert alert = switchToAlert();
        alert.accept();
    }

    public void dismissAlert() {
        log.info("Switching to alert and pressing Cancel");
        Alert alert = switchToAlert();
        alert.dismiss();
    }

    public void typeTextIntoAlert(String text) {
        log.info("Typing text into alert and pressing OK");
        Alert alert = switchToAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    public String getAlertText() {
        Alert alert = switchToAlert();
        String alertText = alert.getText();
        log.info("Alert says: " + alertText);
        return alertText;
    }

    public String getResultText() {
        String result = find(resultTextLocator).getText();
        log.info("Result says: " + result);
        return result;
    }
}




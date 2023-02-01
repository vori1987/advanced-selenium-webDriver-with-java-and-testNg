package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject {

    private final String pageUrl = "http://the-internet.herokuapp.com/";

    private final By checkboxesLinkLocator = By.linkText("Checkboxes");
    private final By dropdownLinkLocator = By.linkText("Dropdown");
    private final By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private final By jsAlertsLocator = By.linkText("JavaScript Alerts");
    private final By multipleWindowsLinkLocator = By.linkText("Multiple Windows");
    private final By editorLinkLocator = By.linkText("WYSIWYG Editor");

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page is opened!");
    }

    public LoginPage clickFormAuthenticationLink() {
        log.info("Clicking Form Authentication link on Welcome Page");
        click(formAuthenticationLinkLocator);
        return new LoginPage(driver, log);
    }

    public CheckboxesPage clickCheckboxesLink() {
        log.info("Clicking checkboxes link on Welcome Page");
        click(checkboxesLinkLocator);
        return new CheckboxesPage(driver, log);
    }

    public DropdownPage clickDropdownLink() {
        log.info("Clicking Dropdown link on Welcome Page");
        click(dropdownLinkLocator);
        return new DropdownPage(driver, log);
    }

    public JsAlertsPage clickJsAlertsLink() {
        log.info("Clicking JsAlerts link on Welcome Page");
        click(jsAlertsLocator);
        return new JsAlertsPage(driver, log);
    }

    public WindowsPage clickMultipleWindowsLink() {
        log.info("Clicking Multiple Windows link on Welcome Page");
        click(multipleWindowsLinkLocator);
        return new WindowsPage(driver, log);
    }

    public EditorPage clickWYSIWYGEditorLink() {
        log.info("Clicking WYSIWYG Editor link on Welcome Page");
        click(editorLinkLocator);
        return new EditorPage(driver, log);
    }

    public void scrollToButtom() {
        log.info("Scrolling to the bottom of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}

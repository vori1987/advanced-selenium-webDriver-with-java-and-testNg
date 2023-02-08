package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePageObject {

    private final By dropdown = By.cssSelector("select#dropdown");

    public DropdownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void selectOption(int i) {
        log.info("Selecting option " + i + " from dropdown");
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("" + i);
    }

    public String getSelectedOption() {
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        log.info(selectedOption + " is selected in dropdown");
        return selectedOption;
    }
}




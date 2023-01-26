package com.herokuapp.theinternet.pages;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HorizontalSliderPage extends BasePageObject {

    private final By rangeLocator = By.id("range");
    private final By sliderLocator = By.tagName("input");
    private String pageUrl = "https://the-internet.herokuapp.com/horizontal_slider";

    public HorizontalSliderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page is opened!");
    }

    public void setSliderTo(String value) {
        log.info("Moving slider to " + value);
        //calculate number of steps
        int steps = (int) (Double.parseDouble(value) / 0.5);
        //perform steps
        pressKey(sliderLocator, Keys.ENTER);
        for (int i=0; i <steps; i++){
            pressKey(sliderLocator, Keys.ARROW_RIGHT);
        }
    }

    public String getSliderValue(){
        String value = find(rangeLocator).getText();
        log.info("Slider value is: " + value);
        return value;
    }
}

package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DragAndDropPage extends BasePageObject {

    private final By columnA = By.id("column-a");
    private final By columnB = By.id("column-b");
    private String pageUrl = "https://the-internet.herokuapp.com/drag_and_drop";

    public DragAndDropPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page is opened!");
    }

    public void dragAtoB() {
        log.info("Drag and drop A box to B box");
        performDragAndDrop(columnA, columnB);
    }

    public String getColumnA() {
        String text = find(columnA).getText();
        log.info("Column A text is: " + text);
        return text;
    }

    public String getColumnB() {
        String text = find(columnB).getText();
        log.info("Column B text is: " + text);
        return text;
    }
}

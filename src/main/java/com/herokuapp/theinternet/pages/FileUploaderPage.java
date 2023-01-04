package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploaderPage extends BasePageObject {

    private final By choseFileFieldLocator = By.id("file-upload");
    private final By uploadButtonLocator = By.id("file-submit");
    private final By uploadedFilesLocator = By.id("uploaded-files");
    private final String pageUrl = "https://the-internet.herokuapp.com/upload";

    public FileUploaderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public void selectFile(String filename) {
        log.info("Selecting '" + filename + "' file from Files folder");
        String filePath = System.getProperty("user.dir") + "/src/main/resources/files/" + filename;
        log.info("Putting " + filePath);
        type(filePath, choseFileFieldLocator);
        log.info("File selected");
    }

    public void pushUploadButton() {
        log.info("Clicking on upload button");
        click(uploadButtonLocator);
    }

    public String getUploadedFilesNames() {
        String names = find(uploadedFilesLocator).getText();
        log.info("Uploaded files: " + names);
        return names;
    }
}

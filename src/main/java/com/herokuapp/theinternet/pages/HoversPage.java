package com.herokuapp.theinternet.pages;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HoversPage extends BasePageObject {

    private final By avatarLocator = By.xpath("//div[@class='figure']");
    private final By viewProfileLinkLocator = By.xpath(".//a[contains(text(), 'View profile')]");
    private String pageUrl = "https://the-internet.herokuapp.com/hovers";

    public HoversPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page is opened!");
    }

    public void openUserProfile(int i) {
        log.info("Opening user profile");
        List<WebElement> avatars = findAll(avatarLocator);
        WebElement specifiedUserAvatar = avatars.get(i - 1);
        hoverOverElement(specifiedUserAvatar);
        specifiedUserAvatar.findElement(viewProfileLinkLocator).click();
    }
}

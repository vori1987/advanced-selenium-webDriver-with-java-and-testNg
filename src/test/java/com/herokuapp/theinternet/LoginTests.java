package com.herokuapp.theinternet;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {

    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                System.out.println("Do not know to start " + browser + ", starting chrome browser");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();

        //implicit wait - use only 1 time. no multiple usim
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(priority = 1, groups = {"positiveTests", "smokeTests"})
    public void positiveLoginTest() throws InterruptedException {
        System.out.println("Starting login test");
        //        open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened.");
        //                enter user name
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        //                enter pas
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        //explicit wait (wait when element will be clickable) - many times using.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //                        click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();

        //logOut button
        WebElement logOutButton = driver.findElement(By.xpath("//a[@class = 'button secondary radius']"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Log Out button is not visible");
        //                verifications:
        //        new url
        Thread.sleep(3000);
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualString = driver.getCurrentUrl();
        Assert.assertEquals(actualString, expectedUrl, "Actual page url is not the same as expected");
        //                logout button is visible
        //                successful login message
        WebElement succesLogInMessage = driver.findElement(By.cssSelector("div#flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = succesLogInMessage.getText();
        //        Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Actual message does not contain expected message.\nActualMessage: "
                        + actualMessage + "\nExpectedMessage: " + expectedMessage);
    }

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 2, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedMessage) {
        System.out.println("Starting negativeLoginTest with" + username + " and " + password);
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened.");
        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(username);
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(password);
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();
        WebElement unsuccessfulLogInMessage = driver.findElement(By.cssSelector("div#flash"));
        String actualMessage = unsuccessfulLogInMessage.getText();
        Assert.assertTrue(unsuccessfulLogInMessage.isDisplayed(), "Unsuccessful popUp is not visible");
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Actual message does not contain expected message.\nActualMessage: "
                        + actualMessage + "\nExpectedMessage: " + expectedMessage);
    }

    @AfterMethod(alwaysRun = true)
    private void extracted() {
        driver.quit();
    }
}

package com.herokuapp.theinternet;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

public class ExceptionsTests {

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
    }

    @Test(priority = 1)
    public void notVisibleTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        System.out.println("Page is opened.");
        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button[.='Start']"));
        startButton.click();
        WebElement finishElement = driver.findElement(By.xpath("//div[@id='finish']/h4[.='Hello World!']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(finishElement));
        String expectedLabel = finishElement.getText();
        Assert.assertTrue(expectedLabel.contains("Hello World!"), "Finish text: " + expectedLabel);
    }

    @Test(priority = 2)
    public void timeOutTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        System.out.println("Page is opened.");
        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button[.='Start']"));
        startButton.click();
        WebElement finishElement = driver.findElement(By.xpath("//div[@id='finish']/h4[.='Hello World!']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.visibilityOf(finishElement));
        } catch (TimeoutException exception) {
            System.out.println("Exception catched " + exception.getMessage());
            sleep(3000);
        }
        String expectedLabel = finishElement.getText();
        Assert.assertTrue(expectedLabel.contains("Hello World!"), "Finish text: " + expectedLabel);
    }

    @Test(priority = 3)
    public void noSuchElementTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        System.out.println("Page is opened.");
        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button[.='Start']"));
        startButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
                "Couldn't verify expecting text");
    }

    @Test(priority = 3)
    public void stateElementTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement checkBox = driver.findElement(By.id("checkbox"));
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        removeButton.click();
        Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkBox)),
                "Checkbox is still visible but shouldn't be");
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
        addButton.click();
        Assert.assertTrue((wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox"))).isDisplayed()),
                "Checkbox is not visible but it should be");
    }

    @Test(priority = 4)
    public void disabledElementTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement textField = driver.findElement(By.xpath("(//input)[2]"));
        WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
        enableButton.click();
        String text = "some text";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(textField));
        textField.sendKeys(text);
        Assert.assertEquals(textField.getAttribute("value"), text);
    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    private void extracted() {
        driver.quit();
    }
}

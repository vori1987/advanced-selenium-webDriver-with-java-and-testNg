package com.herokuapp.theinternet.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() {
        System.out.println("Create driver: " + browser);
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver.set(new FirefoxDriver());
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
            default:
                System.out.println("Do not know to start " + browser + ", starting chrome browser");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
        }
        return driver.get();
    }
}
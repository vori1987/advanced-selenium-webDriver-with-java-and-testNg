package com.herokuapp.theinternet.base;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.log = log;
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() {
        log.info("Create driver: " + browser);
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver.set(new FirefoxDriver());
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
            case "chromeheadless":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver.set(new ChromeDriver());
                break;
            case "firefoxheadless":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "phantomjs":
                System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
                driver.set(new PhantomJSDriver());
                break;
            case "htmlunit":
                driver.set(new HtmlUnitDriver());
                break;
            default:
                log.info("Do not know to start " + browser + ", starting chrome browser");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
        }
        return driver.get();
    }

    public WebDriver createChromeWithProfile(String profile) {
        log.info("Starting chrome driver with profile " + profile);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=src/main/resources/profiles/" + profile);
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }

    public WebDriver createChromeDriverWithMobileEmulation(String deviceName) {
        log.info("Starting chrome driver with " + deviceName + " emulation");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }
}
package com.herokuapp.theinternet.jserrortests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JSErrorPage;
import java.util.List;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class JSErrorTests extends TestUtilities {

    @Test
    public void jsErrorTest() {
        log.info("Starting jsErrorTest");
        SoftAssert softAssert = new SoftAssert();
        //open jsErrorPage
        JSErrorPage jsErrorPage = new JSErrorPage(driver, log);
        jsErrorPage.openPage();
        //Get logs
        List<LogEntry> logs = getBrowserLogs();
        //verifications
        for (LogEntry logEntry : logs) {
            if (logEntry.getLevel().toString().equals("SEVERE")) {
                softAssert.fail("Severe error: " + logEntry.getMessage());
            }
        }
        softAssert.assertAll();
    }
}

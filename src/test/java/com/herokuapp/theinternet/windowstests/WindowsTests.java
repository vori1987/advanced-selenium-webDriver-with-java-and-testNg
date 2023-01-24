package com.herokuapp.theinternet.windowstests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WindowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowsTests extends TestUtilities {

    @Test
    public void newWindowTest() {
        log.info("Starting newWindowTest");
        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        //click on MultipleWindows link
        WindowsPage windowsPage = welcomePage.clickMultipleWindowsLink();
        //open new window, select "Click Here"
        windowsPage.openNewWindow();
        //Find and switch to new window page
        NewWindowPage newWindowPage = windowsPage.switchToNewWindowPage();
        String pageSource = newWindowPage.getCurrentPageSource();
        //verify page source contains expected text
        Assert.assertTrue(pageSource.contains("New Window"),
                "Mew page source does not as expected -" + pageSource);
    }
}

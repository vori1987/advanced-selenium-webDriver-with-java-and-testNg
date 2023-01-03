package com.herokuapp.theinternet.keypress;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.EditorPage;
import com.herokuapp.theinternet.pages.KeyPressesPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KeyPressesTest extends TestUtilities {

    @Test
    public void pressKeyTest() {
        log.info("Starting pressKeyTest");
        //open keyPressPage
        KeyPressesPage keyPressesPage = new KeyPressesPage(driver, log);
        keyPressesPage.openPage();
        //push keyboard key
        keyPressesPage.pressKeyWithActions(Keys.SPACE);
        //Get result Text
                String result = keyPressesPage.getResultText();
        //verify result text is expected
        Assert.assertTrue(result.equals("You entered: SPACE"),
                "result is not as expected. \nText is: " + result);
    }
}

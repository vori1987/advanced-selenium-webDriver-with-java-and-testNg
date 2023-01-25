package com.herokuapp.theinternet.hovers;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HoversPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HoversTests extends TestUtilities {

    @Test
    public void user2ProfileTest() {
        log.info("Starting user1ProfileTest");
        //open Hovers page
        HoversPage hoversPage = new HoversPage(driver, log);
        hoversPage.openPage();
        //Drag and Drop elements
        hoversPage.openUserProfile(2);
        sleep(1000);
        //verify correct user profile opened
        Assert.assertTrue(hoversPage.getCurrentUrl().contains("/users/2"),
                "Url of opened page is not expected that expected");
    }
}
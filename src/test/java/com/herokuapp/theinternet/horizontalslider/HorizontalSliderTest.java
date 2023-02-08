package com.herokuapp.theinternet.horizontalslider;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HorizontalSliderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HorizontalSliderTest extends TestUtilities {

    @Test
    public void sliderTest() {
        //open Horizontal Slider page
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(driver, log);
        horizontalSliderPage.openPage();
        //Set slider value
        String value = "3.5";
        sleep(2000);
        horizontalSliderPage.setSliderTo(value);
        sleep(2000);
        //verify slider value
        String currentSliderValue = horizontalSliderPage.getSliderValue();
        Assert.assertEquals(value, currentSliderValue, "Rage is not correct. It is: " + currentSliderValue);
    }
}
package com.herokuapp.theinternet.editortests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.EditorPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditorTests extends TestUtilities {

    @Test
    public void defaultEditorValueTest() {
        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        //scroll down
        welcomePage.scrollToButtom();
        //click on WYSIWYGEditor link
        EditorPage editorPage = welcomePage.clickWYSIWYGEditorLink();
        //Get default editor text
        sleep(100);
        String editorText = editorPage.getEditorText();
        //verify page source contains expected text
        Assert.assertEquals(editorText, "Your content goes here.",
                "Editor default text is not as expected. It is: " + editorText);
    }
}

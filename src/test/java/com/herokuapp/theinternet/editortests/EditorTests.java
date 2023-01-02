package com.herokuapp.theinternet.editortests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.EditorPage;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditorTests extends TestUtilities {

    @Test
    public void defaultEditorValueText() {
        log.info("Starting defaultEditorValueText");
        //open main page
        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        //click on WYSIWYGEditor link
        EditorPage editorPage = welcomePage.clickWYSIWYGEditorLink();
        //Get default editor text
                String editorText = editorPage.getEditorText();
        //verify page source contains expected text
        Assert.assertTrue(editorText.equals("Your content goes here."),
                "Editor default text is not as expected. It is: " + editorText);
    }
}

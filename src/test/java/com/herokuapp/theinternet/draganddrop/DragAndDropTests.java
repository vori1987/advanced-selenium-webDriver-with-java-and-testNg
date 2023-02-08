package com.herokuapp.theinternet.draganddrop;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTests extends TestUtilities {

    @Test
    public void dragAndDropTest() {
        //open DragAndDrop page
        DragAndDropPage dragAndDropPage = new DragAndDropPage(driver, log);
        dragAndDropPage.openPage();
        //Drag and Drop elements
        dragAndDropPage.dragAtoB();
        sleep(1000);
        //verify correct headers in correct boxes
        String columnAText = dragAndDropPage.getColumnA();
        Assert.assertEquals(columnAText, "B", "Column A header should be B, but it is: " + columnAText);
        String columnBText = dragAndDropPage.getColumnB();
        Assert.assertEquals(columnBText, "A", "Column A header should be B, but it is: " + columnBText);
    }
}
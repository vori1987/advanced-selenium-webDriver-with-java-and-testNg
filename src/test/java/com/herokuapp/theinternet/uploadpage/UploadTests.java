package com.herokuapp.theinternet.uploadpage;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.FileUploaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTests extends TestUtilities {

    @Test
    public void imageUploadTest() {
        log.info("Starting imageUploadTest");
        // open File Uploader Page
        FileUploaderPage fileUploaderPage = new FileUploaderPage(driver, log);
        fileUploaderPage.openPage();
        // Select file
        String fileName = "test.jpg";
        fileUploaderPage.selectFile(fileName);
        // Push upload button
        fileUploaderPage.pushUploadButton();
        // Get uploaded files
        String fileNames = fileUploaderPage.getUploadedFilesNames();
        // Verify selected file uploaded
        Assert.assertTrue(fileNames.contains(fileName),
                "Our file (" + fileName + ") is not one of the uploaded (" + fileNames + ")");
    }
}
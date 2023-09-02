package com.example.tests.amazon;

import com.example.TestComponents.BaseTest;
import com.example.Utils.PropertyClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AmazonTitleTest extends BaseTest {

    @BeforeMethod
    public void launchBrowser() {
        driver = initializeBrowser();
        driver.get(PropertyClass.getProperty("amazon"));
        System.out.println("Browser Launched maximised and amazon entered");
    }

    @Test
    public void verifyTitle() throws InterruptedException, IOException {
        driver = initializeBrowser();
        driver.get(PropertyClass.getProperty("amazon"));
        Assert.assertEquals("Amazon.com. Spend less. Smile more.", driver.getTitle());
        System.out.println("Title verified");
    }

}


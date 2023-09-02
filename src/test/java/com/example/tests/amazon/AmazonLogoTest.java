package com.example.tests.amazon;

import com.example.TestComponents.BaseTest;
import com.example.Utils.PropertyClass;
import com.example.pages.AmazonPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonLogoTest extends BaseTest {

    @BeforeMethod
    public void launchBrowser() {
        driver = initializeBrowser();
        driver.get(PropertyClass.getProperty("amazon"));
    }

    @Test
    public void verifyAmazonLogo() {
        AmazonPage amazonPage = new AmazonPage(driver);
        amazonPage.verifyAmazonLogo();

    }

}

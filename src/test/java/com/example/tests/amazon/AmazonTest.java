package com.example.tests.amazon;

import com.example.TestComponents.BaseTest;
import com.example.Utils.PropertyClass;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AmazonTest extends BaseTest {

    @BeforeMethod
    public void launchBrowser(){
        driver = initializeBrowser();
        driver.get(PropertyClass.getProperty("amazon"));
    }

    @Test
    public void launchAmazon() throws InterruptedException, IOException {
        System.out.println(driver.getTitle());
        driver.quit();
    }
}

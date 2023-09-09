package com.example.tests.flipkart;

import com.example.TestComponents.BaseTest;
import com.example.Utils.PropertyClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlipkartTest extends BaseTest {

    @Test
    public void launchFlipkart() throws InterruptedException, IOException {
        driver = initializeBrowser();
        driver.get(PropertyClass.getProperty("flipkart"));
        System.out.println(driver.getTitle());
        System.out.println("Reading testdata :"+PropertyClass.getTestcaseNames("Sheet1"));
        driver.quit();
    }
}

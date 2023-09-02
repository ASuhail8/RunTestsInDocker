package com.example.tests.facebook;

import com.example.TestComponents.BaseTest;
import com.example.Utils.PropertyClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class FacebookTest extends BaseTest {

    @Test
    public void launchFb() throws InterruptedException, IOException {
        driver = initializeBrowser();
        driver.get(PropertyClass.getProperty("facebook"));
        System.out.println(driver.getTitle());
        driver.quit();
    }
}

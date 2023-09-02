package com.example.tests.google;

import com.example.TestComponents.BaseTest;
import com.example.Utils.PropertyClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class GoogleTest extends BaseTest {

    public static WebDriver driver;
    @Test
    public void launchGoogle() throws InterruptedException, IOException {
        driver = initializeBrowser();
        driver.get(PropertyClass.getProperty("google"));
        System.out.println(driver.getTitle());
        driver.quit();
    }

}

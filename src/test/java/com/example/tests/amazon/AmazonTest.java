package com.example.tests.amazon;

import com.example.Utils.PropertyClass;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class AmazonTest {

    @Test
    public void launchAmazon() throws InterruptedException, IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(PropertyClass.getProperty("amazon"));
        System.out.println(driver.getTitle());
        driver.quit();
    }
}

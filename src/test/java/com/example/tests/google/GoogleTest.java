package com.example.tests.google;

import com.example.Utils.PropertyClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class GoogleTest {

    public static WebDriver driver;
    @Test
    public void launchGoogle() throws InterruptedException, IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get(PropertyClass.getProperty("google"));
        System.out.println(driver.getTitle());
        driver.quit();
    }

}

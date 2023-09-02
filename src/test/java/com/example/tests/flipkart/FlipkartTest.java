package com.example.tests.flipkart;

import com.example.Utils.PropertyClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlipkartTest {

    @Test
    public void launchFlipkart() throws InterruptedException, IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-application-cache");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(PropertyClass.getProperty("flipkart"));
        System.out.println(driver.getTitle());
        driver.quit();
    }
}

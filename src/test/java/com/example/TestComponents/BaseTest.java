package com.example.TestComponents;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.Utils.PropertyClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.Utils.PropertyClass.getProperty;

public class BaseTest {

    public WebDriver driver;

    public WebDriver initializeBrowser() {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-application-cache");
            driver = new ChromeDriver(chromeOptions);
        } else if (System.getProperty("browser").equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--disable-dev-shm-usage");
            edgeOptions.addArguments("--disable-extensions");
            edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("--disable-gpu");
            edgeOptions.addArguments("--no-sandbox");
            edgeOptions.addArguments("--incognito");
            edgeOptions.addArguments("--disable-application-cache");
            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    public static LocalDateTime getCurrentDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return LocalDateTime.now();
    }

    public String getScreenshot(String testName, WebDriver driver) throws IOException {

        TakesScreenshot t = (TakesScreenshot) driver;
        File src = t.getScreenshotAs(OutputType.FILE);
        File path = new File(System.getProperty("user.dir") + "/reports/" + testName + ".png");
        FileUtils.copyFile(src, path);
        return System.getProperty("user.dir") + "/reports/" + testName + ".png";

    }

}



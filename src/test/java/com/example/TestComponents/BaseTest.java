package com.example.TestComponents;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import java.io.File;
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

    @AfterSuite
    public void UploadToS3() {
        // Initialize Amazon S3 client
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

        // Specify the S3 bucket name and the local directory where your test result files are stored
        String bucketName = "my-app-test-results";
        String localDirectory = "test-output/emailable-report.html";

        // Upload files to S3
        s3Client.putObject(new PutObjectRequest(bucketName, getCurrentDateAndTime()+"-test-output/emailable-report.html", new File(localDirectory)));
    }

    public LocalDateTime getCurrentDateAndTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return LocalDateTime.now();
    }
}



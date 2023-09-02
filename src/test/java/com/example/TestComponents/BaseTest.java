package com.example.TestComponents;

import com.example.Utils.PropertyClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static com.example.Utils.PropertyClass.getProperty;

public class BaseTest {

    public WebDriver driver;

    public WebDriver initializeBrowser(){
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getProperty("browser");
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-application-cache");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(chromeOptions);
        } else if(System.getProperty("browser").equalsIgnoreCase("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--disable-extensions");
            edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("--disable-gpu");
            edgeOptions.addArguments("--no-sandbox");
            edgeOptions.addArguments("--incognito");
            edgeOptions.addArguments("--disable-application-cache");
            edgeOptions.addArguments("--disable-dev-shm-usage");
            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().window().maximize();
        return driver;
    }

}

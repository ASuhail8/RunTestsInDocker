package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AmazonPage {

    public WebDriver driver;

    private static By amazonLogo = By.id("nav-logo-sprites");

    public AmazonPage(WebDriver driver){
        this.driver = driver;
    }

    public void verifyAmazonLogo() {
        Assert.assertTrue(driver.findElement(amazonLogo).isDisplayed());
    }

}

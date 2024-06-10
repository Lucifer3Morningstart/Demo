package org.example;

import Helper.BaseTest;
import Helper.LoginPage;
import Helper.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Helper.XpathHelper.LOGIN_LOGO_XPATH;

public class LoginTest extends BaseTest {
    @Test
    public void login(){
        driver.get(properties.getProperty("login.url"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_LOGO_XPATH)));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }
    @Test
    public void testProductPages(){
        driver.get(properties.getProperty("login.url"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_LOGO_XPATH)));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductCount(),6);
    }

    @Test(dependsOnMethods = "testProductPages")
    public void testAddHighestProductToCart(){
        ProductPage productPage = new ProductPage(driver);
        productPage.addHighestPriceProductToCart();
        Assert.assertTrue(productPage.isProductInCart());
    }
}

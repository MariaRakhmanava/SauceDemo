package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ShoppingCartTests {

    @Test
    public void checkProductPriceAndNameInTheShoppingCartTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-onesie")).click();
        String productNameOnDisplay = driver.findElement(By.xpath("//*[text()='Sauce Labs Onesie']")).getText();
        driver.findElement(By.className("shopping_cart_link")).click();
        String productNameInTheShoppingCart = driver.findElement(By.xpath("//*[text()='Sauce Labs Onesie']")).getText();
        Assert.assertEquals(productNameInTheShoppingCart, productNameOnDisplay);
        driver.quit();
    }
}

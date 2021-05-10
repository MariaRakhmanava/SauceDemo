package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCartTests {

    @Test
    public void checkProductNameInTheShoppingCartTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.findElement(By.xpath("//*[text()='Sauce Labs Onesie']")).click();
        String productNameOnDisplay = driver.findElement(By.xpath("//*[text()='Sauce Labs Onesie']")).getText();
        driver.findElement(By.xpath("//*[@data-test='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        String productNameInTheShoppingCart = driver.findElement(By.xpath("//*[text()='Sauce Labs Onesie']")).getText();
        Assert.assertEquals(productNameInTheShoppingCart, productNameOnDisplay);
        driver.quit();
    }

    @Test
    public void checkProductPriceInTheShoppingCartTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.findElement(By.xpath("//*[text()='Sauce Labs Onesie']")).click();
        String productPriceOnDisplay = driver.findElement(By.xpath("//*[@class='inventory_details_price']")).getText();
        driver.findElement(By.xpath("//*[@data-test='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='inventory_item_price']")).getText(), productPriceOnDisplay);
        driver.quit();
    }

    @Test
    public void checkProductsPricesInTheShoppingCartTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).submit();
        driver.findElement(By.xpath("//*[text()='Sauce Labs Onesie']")).click();
        String product1PriceOnDisplay = driver.findElement(By.xpath("//*[@class='inventory_details_price']")).getText();
        List<String> productsPricesOnDisplay = new ArrayList<>(2);
        productsPricesOnDisplay.add(product1PriceOnDisplay);
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.xpath("//*[@data-test='back-to-products']")).click();
        driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']")).click();
        productsPricesOnDisplay.add(driver.findElement(By.className("inventory_details_price")).getText());
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        List<WebElement> productsPricesAsWebElements = driver.findElements(By.xpath("//*[@class='inventory_item_price']"));
        List<String> productsPricesInTheShoppingCart = new ArrayList<>(2);
        for (WebElement i : productsPricesAsWebElements) {
            productsPricesInTheShoppingCart.add(i.getText());
        }
        for (int i = 0; i < productsPricesInTheShoppingCart.size(); i++) {
            Assert.assertEquals(productsPricesInTheShoppingCart.get(i), productsPricesOnDisplay.get(i));
        }
        driver.quit();
    }

    @Test
    public void checkProductsNamesInTheShoppingCartTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).submit();
        List<String> productsNamesOnDisplay = new ArrayList<>();
        List<WebElement> productsNamesAsWebElementsOnDisplay = driver.findElements(By.className("inventory_item_name"));
        for (WebElement i : productsNamesAsWebElementsOnDisplay) {
            productsNamesOnDisplay.add(i.getText());
        }
        List<WebElement> productsToBeAddedToTheShoppingCart = driver.findElements(By.xpath("//*[text()='Add to cart']"));
        for (WebElement i : productsToBeAddedToTheShoppingCart) {
            i.click();
        }
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<String> productsNamesInTheShoppingCart = new ArrayList<>();
        List<WebElement> productsNamesAsWebElementsInTheShoppingCart = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement i : productsNamesAsWebElementsInTheShoppingCart) {
            productsNamesInTheShoppingCart.add(i.getText());
        }
        for (String i : productsNamesOnDisplay) {
            Assert.assertEquals(productsNamesOnDisplay, productsNamesInTheShoppingCart);
        }
        driver.quit();
    }

}


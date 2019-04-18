package Actions;

import Pages.TikiAddToCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TikiAddToCartAction {
    public static  void clickProduct(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiAddToCartPage.aProduct)).click();
    }
    public static  void clickAdd(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiAddToCartPage.btnAdd)).click();
    }
    public static  void clickCheckCart(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiAddToCartPage.btnCheckCart)).click();
    }
    public static  void clickCheckProduct(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiAddToCartPage.btnCheckCart)).click();
    }
    public static  void clickDeleteProduct(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiAddToCartPage.btnDelete)).click();
    }

}

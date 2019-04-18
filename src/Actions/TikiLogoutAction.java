package Actions;

import Pages.TikiLogoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TikiLogoutAction {
    public static  void clickLogout(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiLogoutPage.aLogout)).click();
    }
    public static  void clickCart(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiLogoutPage.btnCart)).click();
    }
}

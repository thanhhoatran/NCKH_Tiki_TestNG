package Actions;

import Pages.TikiSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class TikiSearchAction {
    public static  void enterSearch(WebDriver driver, String search)
    {
        //driver.findElement(By.xpath(TikiLoginPage.txtSearch)).sendKeys(search);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(TikiSearchPage.txtSearch)));
        actions.click();
        actions.sendKeys(search);
        actions.build().perform();
    }
    public static  void clickSearch(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiSearchPage.btnSearch)).click();
    }
}

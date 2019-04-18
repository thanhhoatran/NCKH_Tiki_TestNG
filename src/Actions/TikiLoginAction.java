package Actions;

import Pages.TikiLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TikiLoginAction {
    public static  void clickOnDangNhap1(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiLoginPage.DangNhap1)).click();
    }
    public static  void clickOnDangNhap2(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiLoginPage.DangNhap2)).click();
    }
    public static void enterEmailAndPassword(WebDriver driver, String email, String password)
    {
        driver.findElement(By.xpath(TikiLoginPage.txtEmail)).sendKeys(email);
        driver.findElement(By.xpath(TikiLoginPage.txtPass)).sendKeys(password);
    }
    public static  void clickOnLoginButton(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiLoginPage.btnLogin)).click();
    }
}

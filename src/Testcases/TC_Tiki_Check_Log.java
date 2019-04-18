package Testcases;

import Actions.TikiAddToCartAction;
import Actions.TikiLoginAction;
import Actions.TikiLogoutAction;
import Actions.TikiSearchAction;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class TC_Tiki_Check_Log {
    WebDriver driver;
    Properties user = new Properties();
    InputStream input = null;
    String dynamicName = "ThanhHoa Tran";

    private static Logger Log = Logger.getLogger(org.apache.commons.logging.Log.class.getName());
    @BeforeMethod
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", ".\\src\\Resources\\Drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); //vô hiệu hóa thông báo chrome
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.get("https://tiki.vn/");
        driver.manage().window().maximize();

        try
        {
            input = new FileInputStream("user.txt");
            user.load(input);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test(priority = 0, enabled = false)
    //Summary: Verify that the products of cart still displayed when the user log out then login successfully.
    public void TC_Tiki_Check() throws InterruptedException {
        DOMConfigurator.configure("log4j.xml");
        //Step1: Hover on "Đăng nhập" field and click on "Đăng nhập" link.
        TikiLoginAction.clickOnDangNhap1(driver);
        TikiLoginAction.clickOnDangNhap2(driver);
        Log.info("Hover on \"Đăng nhập\" field and click on \"Đăng nhập\" link.");
        //Step2: Enter all valid data in all field of Login form.
        TikiLoginAction.enterEmailAndPassword(driver,user.getProperty("userid"),user.getProperty("password"));
        Log.info("Enter all valid data in all field of Login form.");
        //Step3: Click on "Đăng nhập" button.
        TikiLoginAction.clickOnLoginButton(driver);
        Log.info("Click on \"Đăng nhập\" button.");

        //Step4:Enter a valid data into Search field.
        //(Eg: "laptop" )
        Thread.sleep(2000);
        TikiSearchAction.enterSearch(driver,"laptop");
        Log.info("Enter a valid data into Search field.");
        //Step5: Click on "Tìm kiếm" button.
        TikiSearchAction.clickSearch(driver);
        Log.info("Click on \"Tìm kiếm\" button.");
        //Step6: Click on a product.
        Thread.sleep(2000);
        TikiAddToCartAction.clickProduct(driver);
        ArrayList<String> tags = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tags.get(1));
        Log.info("Click on a product.");
        //Step7: Click on "CHỌN MUA" button.
        Thread.sleep(2000);
        TikiAddToCartAction.clickAdd(driver);
        Log.info("Click on \"CHỌN MUA\" button.");
        //Step8: Click on "Thoát tài khoản" button.
        Thread.sleep(3000);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@id='header-user']//b[text()='Chào ' and text()='"+ dynamicName +"']")).click();
        TikiLogoutAction.clickLogout(driver);
        Log.info("Click on \"Thoát tài khoản\" button.");
        //Step9: User login again. Such as: step 1,2,3.
        Thread.sleep(2000);
        TikiLoginAction.clickOnDangNhap1(driver);
        TikiLoginAction.clickOnDangNhap2(driver);
        TikiLoginAction.enterEmailAndPassword(driver,user.getProperty("userid"),user.getProperty("password"));
        TikiLoginAction.clickOnLoginButton(driver);
        Log.info("User login again. Such as: step 1,2,3.");
        //Step10: Click on "Giỏ hàng" button.
        Thread.sleep(2000);
        TikiLogoutAction.clickCart(driver);
        Log.info("Click on \"Giỏ hàng\" button.");

        //Expected Result: The products of cart still displayed.
        Thread.sleep(2000);
        String LogoutCheckXpath = "//a[@href=\"https://tiki.vn/laptop-masstel-l133-pro-silver-p5829707.html?src=cart-page&spid=5829709\"]";
        boolean isLogoutCheck = driver.findElement(By.xpath(LogoutCheckXpath)).isDisplayed();
        Assert.assertEquals(true, isLogoutCheck);

        //Delete
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        TikiAddToCartAction.clickDeleteProduct(driver);
    }
    @Test
    public void TC_Tiki_Check2() throws InterruptedException {
        //Step1: Hover on "Đăng nhập" field and click on "Đăng nhập" link.
        TikiLoginAction.clickOnDangNhap1(driver);
        TikiLoginAction.clickOnDangNhap2(driver);
        //Step2: Enter all valid data in all field of Login form.
        TikiLoginAction.enterEmailAndPassword(driver,user.getProperty("userid"),user.getProperty("password"));
        //Step3: Click on "Đăng nhập" button.
        TikiLoginAction.clickOnLoginButton(driver);
        //Step4:Enter a valid data into Search field.
        //(Eg: "laptop" )
        Thread.sleep(2000);
        TikiSearchAction.enterSearch(driver,"laptop");
        //Step5: Click on "Tìm kiếm" button.
        TikiSearchAction.clickSearch(driver);
        //Step6: Click on a product.
        Thread.sleep(2000);
        TikiAddToCartAction.clickProduct(driver);
        ArrayList<String> tags = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tags.get(1));
        //Step7: Click on "CHỌN MUA" button.
        Thread.sleep(2000);
        TikiAddToCartAction.clickAdd(driver);
        //Step8: Click on "Thoát tài khoản" button.
        Thread.sleep(3000);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@id='header-user']//b[text()='Chào ' and text()='"+ dynamicName +"']")).click();
        TikiLogoutAction.clickLogout(driver);

    }
    @AfterMethod
    public void afterMethod() {

        // Close the driver

        driver.quit();
    }
}

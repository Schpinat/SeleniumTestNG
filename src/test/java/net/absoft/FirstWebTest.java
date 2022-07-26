package net.absoft;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class FirstWebTest {
    private WebDriver driver;

    @BeforeSuite
    public void downloadDriver () {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver","D:\\JAVA\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions()
                .addArguments("--window-size=1960,1060");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
    }


    @Test
    public void testFailIncorrectPassword (){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("password_wrong");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[@data-test]")).getText(),"Epic sadface: Username and password do not match any user in this service","No error message after wrong-password input");
    }

    @Test
    public void testFailLockedAccount () {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[@data-test]")).getText(),"Epic sadface: Sorry, this user has been locked out.","No error message after locked_out_user input");

    }

    @Test
    public void testSuccessfulAuthorization () {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        Assert.assertFalse(driver.findElements(By.id("shopping_cart_container")).isEmpty(),"User is not logged in, shopping cart icon not found");

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

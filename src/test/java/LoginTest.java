import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTestClass{

    String homeUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String login = "1303";
    String password = "GURU99";

    @BeforeMethod
    public void navigateToHomeUrl(){
        driver.get(homeUrl);
    }

    @Test
    public void positiveLoginTest() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        WebElement logoutLink = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        logoutLink.click();
        assertEquals("You Have Succesfully Logged Out!!", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @Test
    public void resetButtonLoginTest() {
        WebElement loginField = driver.findElement(By.xpath("//input[@type='text']"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//input[@type='reset']")).click();
        assertEquals(loginField.getText(), "");
        assertEquals(passwordField.getText(), "");
    }

    @Test
    public void negativeInvalidLoginLoginTest() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("test");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        assertEquals("User or Password is not valid", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @Test
    public void negativeInvalidPasswordLoginTest() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("setpass");
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        assertEquals("User or Password is not valid", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @Test
    public void negativeInvalidLoginAndPasswordLoginTest() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("765^&%");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("HJGjfj38");
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        assertEquals("User or Password is not valid", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @Test
    public void negativeNullValueLoginAndPasswordLoginTest() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("");
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        assertEquals("User or Password is not valid", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @Test
    public void negativeNullLoginAndCorrectPasswordLoginTest() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        assertEquals("User or Password is not valid", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @Test
    public void negativeCorrectLoginAndNullPasswordLoginTest() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("");
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        assertEquals("User or Password is not valid", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @Test
    public void checkValidationNullValuesLoginAndPasswordLoginTest() {
        WebElement loginField = driver.findElement(By.xpath("//input[@type='text']"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys(password);
        loginField.clear();
        passwordField.clear();
        assertEquals(driver.findElement(By.xpath("//label[@id='message23']")).getText(), "User-ID must not be blank");
        assertEquals(driver.findElement(By.xpath("//label[@id='message18']")).getText(), "Password must not be blank");
    }
}

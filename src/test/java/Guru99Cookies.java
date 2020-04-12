import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class Guru99Cookies extends BaseTestClass {

    String homeUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String login = "1303";
    String password = "GURU99";

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.navigate().to(homeUrl);
    }

    @Test
    public void handleCookies() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        WebElement logOut = driver.findElement(By.xpath("//a[@href='Logout.php']"));
        assertEquals(logOut.getText().trim(), "Log out");
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class GmailTest extends BaseTestClass {

    String homeUrl = "https://www.google.com/intl/ru/gmail/about/";

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.navigate().to(homeUrl);
    }

    @Test
    public void gmailLoadFileTest() throws Exception {
        WebElement enter = driver.findElement(By.xpath("//div[@class='h-c-header__cta']//a[normalize-space(text())='Войти'][1]"));
        enter.click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement login = driver.findElement(By.xpath("//input[@type='email']"));
        login.click();
        login.sendKeys("testforhillelschool@gmail.com" + Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
        inputPassword.click();
        inputPassword.sendKeys("testschool" + Keys.ENTER);

        WebElement compose = driver.findElement(By.xpath("//div[@role='button' and text()='Compose']"));
        compose.click();

        WebElement inputToSend = driver.findElement(By.xpath("//textarea[@name='to']"));
        inputToSend.click();
        inputToSend.sendKeys("testforhillelschool@gmail.com" + Keys.ENTER);

        WebElement inputSubject = driver.findElement(By.xpath("//input[@name='subjectbox']"));
        inputSubject.click();
        inputSubject.sendKeys("Test mail" + Keys.ENTER);

        WebElement inputForText = driver.findElement(By.xpath("//div[@role='textbox']"));
        inputForText.click();
        inputForText.sendKeys("This is the test letter for Automation" + Keys.ENTER);

        WebElement attachButton = driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']"));
        attachButton.click();

        uploadFile("C:\\setuplogfile.log");
        assertEquals(driver.findElement(By.xpath("//div[text()='setuplogfile.log']")).getText(), "setuplogfile.log");

        WebElement sendButton = driver.findElement(By.xpath("//div[@role='button' and text()='Send']"));
        sendButton.click();

        WebElement lastLetter = wait.until(presenceOfElementLocated(By.xpath("//div[@class='yW'][1]")));
        //assertEquals(driver.findElement(By.xpath("//div[@class='y6']/span/span[1]")).getText(), inputSubject.getText());
        lastLetter.click();

        //assertEquals(inputToSend.getText(), driver.findElement(By.xpath("//div[@class='ha']/h2")).getText());
        //assertEquals(inputSubject.getText(), driver.findElement(By.xpath("//h2[text()='Test mail']")).getText());
        //assertEquals(inputForText.getText(), driver.findElement(By.xpath("//div[text()='This is the test letter for Automation']")).getText());
        //assertEquals("setuplogfile.log", driver.findElement(By.xpath("//span[text()='setuplogfile.log']")).getText());
    }

    public void uploadFile(String filePath) throws Exception {
        Robot robot = new Robot();
        robot.setAutoDelay(1000);
        StringSelection ss = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

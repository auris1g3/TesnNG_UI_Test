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
    String subject = "Test mail";
    String textMail = "This is the test letter for Automation";
    String loginName = "testforhillelschool@gmail.com";
    String passwordName = "testschool";
    String fileName = "setuplogfile.log";

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
        login.sendKeys(loginName + Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
        inputPassword.click();
        inputPassword.sendKeys(passwordName + Keys.ENTER);

        WebElement compose = driver.findElement(By.xpath("//div[@role='button' and text()='Compose']"));
        compose.click();

        WebElement inputToSend = driver.findElement(By.xpath("//textarea[@name='to']"));
        inputToSend.click();
        inputToSend.sendKeys(loginName + Keys.ENTER);

        WebElement inputSubject = driver.findElement(By.xpath("//input[@name='subjectbox']"));
        inputSubject.click();
        inputSubject.sendKeys(subject + Keys.ENTER);

        WebElement inputForText = driver.findElement(By.xpath("//div[@role='textbox']"));
        inputForText.click();
        inputForText.sendKeys(textMail + Keys.ENTER);

        WebElement attachButton = driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']"));
        attachButton.click();

        uploadFile("C:\\" + fileName);
        assertEquals(driver.findElement(By.xpath("//div[text()='setuplogfile.log']")).getText(), "setuplogfile.log");

        WebElement sendButton = driver.findElement(By.xpath("//div[@role='button' and text()='Send']"));
        sendButton.click();

        WebElement lastLetter = wait.until(presenceOfElementLocated(By.xpath("//div[@class='yW'][1]")));
        lastLetter.click();

        assertEquals(subject, driver.findElement(By.xpath("//div[@class='ha']/h2")).getText());
        assertEquals("<testforhillelschool@gmail.com>", driver.findElement(By.xpath("//span[@class='go']")).getText());
        assertEquals(textMail, driver.findElement(By.xpath("//div[@class='a3s aXjCH ']/div[@dir='ltr']")).getText());
        assertEquals(fileName, driver.findElement(By.xpath("//span[@class='aV3 zzV0ie']")).getText());
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

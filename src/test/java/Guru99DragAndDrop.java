import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Guru99DragAndDrop extends BaseTestClass {
    String homeUrl = "http://demo.guru99.com/test/drag_drop.html";

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.navigate().to(homeUrl);
    }

    @Test
    public void DragAndDropTest() {
        WebElement bank = driver.findElement(By.xpath("//a[text()=' BANK ']/ancestor::li[@id='credit2']"));
        WebElement accountDebit = driver.findElement(By.xpath("//li[@class='placeholder']/ancestor::ol[@id='bank']"));
        dragAndDrop(bank, accountDebit);

        WebElement sum = driver.findElement(By.xpath("//a[@class='button button-orange']/ancestor::li[@id='fourth']"));
        WebElement amountDebit = driver.findElement(By.xpath("//li[@class='placeholder']/ancestor::ol[@id='amt7']"));
        dragAndDrop(sum, amountDebit);

        WebElement sale = driver.findElement(By.xpath("//a[text()=' SALES ']/ancestor::li[@id='credit1']"));
        WebElement accountCredit = driver.findElement(By.xpath("//li[@class='placeholder']/ancestor::ol[@id='loan']"));
        dragAndDrop(sale, accountCredit);

        WebElement amountCredit = driver.findElement(By.xpath("//li[@class='placeholder']/ancestor::ol[@id='amt8']"));
        dragAndDrop(sum, amountCredit);

        assertEquals(driver.findElement(By.xpath("//a[text()='Perfect!']")).getText(), "Perfect!");
    }

    public void dragAndDrop(WebElement elemFrom, WebElement elemTo) {
        Actions act = new Actions(driver);
        act.dragAndDrop(elemFrom, elemTo).build().perform();
    }
}


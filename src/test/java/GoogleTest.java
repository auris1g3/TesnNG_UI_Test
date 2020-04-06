import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class GoogleTest extends BaseTestClass {

    String homeUrl = "https://www.google.com/";
    Boolean link = false;
    String searchLink = "stylus.ua";

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.get(homeUrl);
    }

    @Test
    public void foundLinkToStylusShop() {

        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("iphone kyiv buy" + Keys.ENTER);
        List<WebElement> linkOnPage1 = driver.findElements(By.partialLinkText(searchLink));
        searchLink(linkOnPage1);

        if (!link) {
            driver.findElement(By.xpath("a[aria-label='Page 2']")).click();
            List<WebElement> linkOnPage2 = driver.findElements(By.partialLinkText(searchLink));
            wait.until((presenceOfElementLocated(By.xpath("//div[@id='result-stats']"))));
            searchLink(linkOnPage2);

            driver.findElement(By.xpath("a[aria-label='Page 3']")).click();
            List<WebElement> linkOnPage3 = driver.findElements(By.partialLinkText(searchLink));
            wait.until((presenceOfElementLocated(By.xpath("//div[@id='result-stats']"))));
            searchLink(linkOnPage3);

            driver.findElement(By.xpath("a[aria-label='Page 4']")).click();
            List<WebElement> linkOnPage4 = driver.findElements(By.partialLinkText(searchLink));
            wait.until((presenceOfElementLocated(By.xpath("//div[@id='result-stats']"))));
            searchLink(linkOnPage4);

            driver.findElement(By.xpath("a[aria-label='Page 5']")).click();
            List<WebElement> linkOnPage5 = driver.findElements(By.partialLinkText(searchLink));
            wait.until((presenceOfElementLocated(By.xpath("//div[@id='result-stats']"))));
            searchLink(linkOnPage5);
        } if (!link){
            System.out.println("STYLUS.UA not found on first 5 pages");
        }

    }
    public void searchLink(List<WebElement> list){
        for (WebElement webElement : list) {
            System.out.println(webElement.getText());
            link = true;
            break;
        }
    }
}




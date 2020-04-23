import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class RozetkaFilters extends BaseTestClass {

    String homeUrl = "https://rozetka.com.ua/";

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.navigate().to(homeUrl);
    }

    @Test
    public void verifyManufacturerFilter() throws Exception {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(( presenceOfElementLocated(By.xpath("//span[@class='active']")) ));
        driver.findElement(By.xpath("//a[@name='show_more_categories']/ancestor::ul/li[3]//span[text()='Еще']")).click();
        driver.findElement(By.xpath("//a[text()='Планшеты']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='link-dotted']")));
        WebElement checkBoxAcer = driver.findElement(By.xpath("//label[@for='Acer']"));
        checkBoxAcer.click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='link-dotted']")));
        WebElement checkBoxAsus = driver.findElement(By.xpath("//label[@for='Apple']"));
        checkBoxAsus.click();
        driver.findElement(By.xpath("//span[@class='catalog-more__text']")).click();
        List<WebElement> allTitle = driver.findElements(By.xpath("//span[@class='goods-tile__title']"));
        assertTrue(verifyFilterManufacturer(allTitle));
    }

    @Test
    public void verifyPriceFilter() throws Exception {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(( presenceOfElementLocated(By.xpath("//span[@class='active']")) ));
        driver.findElement(By.xpath("//a[@name='show_more_categories']/ancestor::ul/li[3]//span[text()='Еще']")).click();
        driver.findElement(By.xpath("//a[text()='Планшеты']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='link-dotted']")));
        WebElement priceMin = driver.findElement(By.xpath("//input[@formcontrolname='min']"));
        priceMin.click();
        priceMin.clear();
        priceMin.sendKeys("5000");
        WebElement priceMax = driver.findElement(By.xpath("//input[@formcontrolname='max']"));
        priceMax.click();
        priceMax.clear();
        priceMax.sendKeys("15000");
        WebElement buttonOk = driver.findElement(By.xpath("//button[@type='submit' and text()=' Ok ']"));
        buttonOk.click();
        List<WebElement> allPrice = driver.findElements(By.xpath("//span[@class='goods-tile__price-value']"));
        assertTrue(verifyPriceFilter(allPrice));
    }

    @Test
    public void verifyThreeFilters() throws Exception {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(( presenceOfElementLocated(By.xpath("//span[@class='active']")) ));
        driver.findElement(By.xpath("//a[@name='show_more_categories']/ancestor::ul/li[3]//span[text()='Еще']")).click();
        driver.findElement(By.xpath("//a[text()='Планшеты']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='link-dotted']")));
        WebElement checkBoxMemory = driver.findElement(By.xpath("//label[@for='4 ГБ']"));
        checkBoxMemory.click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='link-dotted']")));
        WebElement checkBoxScreenType = driver.findElement(By.xpath("//label[@for='AMOLED']"));
        checkBoxScreenType.click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='link-dotted']")));
        WebElement checkBoxVersionOs = driver.findElement(By.xpath("//label[@for='Android 9.x']"));
        checkBoxVersionOs.click();
        WebElement goodAfterFilters = driver.findElement(By.xpath("//li[@class='catalog-grid__cell catalog-grid__cell_type_slim']"));
        hoverToElement(goodAfterFilters);
        List<WebElement> goodWithThreeFilters = driver.findElements(By.xpath("//p[@class='goods-tile__description goods-tile__description_type_text']"));
        assertTrue(verifyThreeFilters(goodWithThreeFilters));
    }

    private boolean verifyFilterManufacturer(List<WebElement> elem) throws Exception {
        for (WebElement webElement : elem) {
            if (webElement.getText().contains("Acer") && webElement.getText().contains("Samsung") && webElement.getText().contains("Asus")) {
                return true;
            }
        }
        throw new Exception("No Acer or Samsung or Asus");
    }

    private boolean verifyThreeFilters(List<WebElement> elem) throws Exception {
        for (WebElement webElement : elem) {
            if (webElement.getText().contains("Android 9") && webElement.getText().contains("AMOLED") && webElement.getText().contains("4 ГБ")) {
                return true;
            }
        }
        throw new Exception("Not selected Android 9 and AMOLED and RAM 4 Gb");
    }

    private boolean verifyPriceFilter(List<WebElement> elem) throws Exception {
        for (WebElement webElement : elem) {
            int price = Integer.parseInt(webElement.getText().replaceAll(" ", ""));
            if (price >= 3000 && price <= 15000) {
                return true;
            }
        }
        throw new Exception("Selected products do not fall within the range of selected prices");
    }

    private void hoverToElement(WebElement elem) {
        Actions actions = new Actions(driver);
        actions.moveToElement(elem).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.AssertJUnit.assertTrue;

public class Guru99Iframes extends BaseTestClass {

    String homeUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.navigate().to(homeUrl);
    }

    @Test
    public void IframesTest() throws Exception {
        WebElement iFrame1 = driver.findElement(By.xpath("//iframe[contains (@id, 'primis_playerSekindoSPlayer')]"));
        driver.switchTo().frame(iFrame1);

        WebElement iFrame2 = driver.findElement(By.xpath("//iframe[@src='about:blank']"));
        driver.switchTo().frame(iFrame2);
        WebElement pauseButton = driver.findElement(By.xpath("//div[@id='pauseBtn']"));

        Robot robot = new Robot();
        WebElement playButton = driver.findElement(By.xpath("//div[@id='playBtn']"));
        playButton.click();
        robot.setAutoDelay(1000);

        robot.mouseMove(180, 620);
        assertTrue(isDisplayedPauseButton(pauseButton));

        robot.mouseMove(400, 620);
        assertTrue(isNotDisplayedPauseButton(pauseButton));
    }

    public boolean isDisplayedPauseButton(WebElement elem) throws Exception {
        if (elem.isDisplayed()) {
            return true;
        }
        throw new Exception("The cursor of mouse is not hovering iframe");
    }

    public boolean isNotDisplayedPauseButton(WebElement elem) throws Exception {
        if (!elem.isDisplayed()) {
            return true;
        }
        throw new Exception("The cursor of mouse is hovering iframe");
    }
}

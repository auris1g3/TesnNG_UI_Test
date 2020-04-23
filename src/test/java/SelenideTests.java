import com.codeborne.selenide.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    @BeforeTest
    public void configurationBrowser() {
        Configuration.timeout = 30000;
        Configuration.browserSize = "1366x768";
    }

    @Test
    public void wizzairTicketSearchTest() {

        open("https://wizzair.com/");

        $("#search-departure-station").should(Condition.visible).setValue("Vienna");
        $x("//mark[text()='Vienna']").should(Condition.visible).click();
        $("#search-arrival-station").should(Condition.visible).setValue("Kyiv - Zhulyany");
        $x("//mark[text()='Kyiv - Zhulyany']").should(Condition.visible).click();

        $("#search-departure-date").click();
        $x("//button[@data-pika-year='2020'][@data-pika-month='5'][@data-pika-day='8']").click();
        $x("//button[@data-pika-year='2020'][@data-pika-month='5'][@data-pika-day='11']").click();
        $x("//button[@class='rf-button rf-button--medium rf-button--primary rf-button--shrinkable']").click();

        $("#search-passenger").click();
        $x("//button[@data-test='services-flight-search-increment']").click();
        $x("//button[@data-test='flight-search-hide-panel']").click();
        $x("//span[text()='Search']").click();

        switchTo().window(1);
        $$x("//div[@class='flight-select__flight__title-container']").shouldHaveSize(2);
        $$x("//time[@class='flight-select__flight-info__day']").get(0).shouldHave(text("Mon, 8 Jun 2020"));
        $$x("//time[@class='flight-select__flight-info__day']").get(1).shouldHave(text("Thu, 11 Jun 2020"));
    }

    @Test
    public void ryanairTicketSearchTest() {

        open("https://www.ryanair.com/gb/en");

        $("#input-button__departure").clear();
        $("#input-button__departure").setValue("Vienna");
        $x("//span[contains(text(),'Vienna')]").click();
        $("#input-button__destination").setValue("Kyiv");
        $x("//span[contains(text(),'Kyiv')]").click();

        $x("//div[@class='datepicker__arrow-wrap']").click();
        $x("//div[@data-id='2020-06-08']").click();
        $x("//div[@data-id='2020-06-11']").click();

        $$x("//ry-counter[@data-ref='passengers-picker__adults'] div").findBy(text("1"));
        $x("//div[@class='counter__button-wrapper--enabled']").shouldBe(Condition.visible).click();
        $$x("//ry-counter[@data-ref='passengers-picker__adults'] div").findBy(text("2"));
        $x("//button[contains(text(), 'Done')]").click();
        $x("//ry-spinner[contains(text(), 'Search')]").click();

        $$x("//div[contains(@class, 'header__container')]").shouldHaveSize(2);
        $$x("//span[@class='date-item__day-of-month h4 date-item__day-of-month--selected']").get(0).shouldHave(text("08"));
        $$x("//span[@class='date-item__day-of-month h4 date-item__day-of-month--selected']").get(1).shouldHave(text("11"));
        $$x("//span[@class='date-item__month h4 date-item__month--selected']").get(0).shouldHave(text("Jun"));
        $$x("//span[@class='date-item__month h4 date-item__month--selected']").get(1).shouldHave(text("Jun"));
    }

    @Test
    public void flyuiaTicketSearchTest() {

        open("https://www.flyuia.com/ua/en/home");

        $x("//input[@placeholder='Departure']").setValue("Vienna").pressEnter();
        $x("//input[@placeholder='Arrival']").setValue("Kyiv").pressEnter();

        $x("//sw-form-control-datepicker[@formcontrolname='departureDate']//div[@class='value-as-text-container']").click();
        WebElement nextMonth = $x("//i[@class='obe-sw-icon-navigate_next']");
        nextMonth.click();
        nextMonth.click();

        SelenideElement dapartureDate = $$x("//button[@class='calendar-day calendar-day-btn fx-flex-fill fx-row__center__center']").findBy(text("8"));
        dapartureDate.click();

        $x("//sw-form-control-datepicker[@formcontrolname='returnDate']//div[@class='value-as-text-container']").click();
        SelenideElement returnDate = $$x("//button[@class='calendar-day calendar-day-btn fx-flex-fill fx-row__center__center']").findBy(text("11"));
        returnDate.click();

        $x("//div[@class='value-as-text-container' and text()=' 1 Passenger ']").click();
        $$x("//span[@class='pax-type']").get(0).shouldHave(text("1"));
        $x("//div[@class='type-count fx-row__start__center fx-flex-fill']/following::button[text()='+ ']").click();
        $$x("//span[@class='pax-type']").get(0).shouldHave(text("2"));
        $("#SEARCH_WIDGET_FORM_BUTTONS_SEARCH_FLIGHTS").click();

        switchTo().window(1);
        $$x("//div[@class='product__title']").shouldHaveSize(2);
        $$x("//button[@class='date-container nonclickable ng-star-inserted']").get(0).shouldHave(text("Mon, 08 Jun"));
        $$x("//button[@class='date-container nonclickable ng-star-inserted']").get(1).shouldHave(text("Thu, 11 Jun"));
    }
}



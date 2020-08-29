package ru.netology;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    @Test
    void shouldReturnOK() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDate.now().plusDays(3));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Пермь");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Салтыков-Щедрин Михаил");
        $("[data-test-id='phone'] input").setValue("+70123456789");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='notification']").waitUntil(visible, 15000).shouldHave(text("Успешно!" + "Встреча успешно забронирована на " + date));
    }
}

package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void cardDeliveryOrder() {

        LocalDate date = LocalDate.now().plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String text = date.format(formatter);

        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[data-test-id=city] input").setValue("Саратов");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(text);
        form.$("[data-test-id=name] input").setValue("Морозова Олеся");
        form.$("[data-test-id=phone] input").setValue("+79275555555");
        form.$("[data-test-id=agreement]").click();
        form.$(("button")).click();
        $(withText("Успешно!")).shouldBe(exist);
        $ ("h2").shouldBe(exist, Duration.ofSeconds(15));
    }
}
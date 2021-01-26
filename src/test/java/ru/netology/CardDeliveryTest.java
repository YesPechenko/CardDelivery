package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @BeforeEach
    void setup () {
        open ("http://localhost:9999");
    }

    @Test
    void shouldTestCorrectForm () {
        $("[data-test-id=city] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 12000);
        $("[data-test-id=notification] .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на "+date));
    }

    @Test
    void shouldTestFormCityNotCorrect () {
        $("[data-test-id=city] input").setValue("Moscow");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestFormNameHyphen () {
        $("[data-test-id=city] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов-Алекксаров Василий");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 12000);
        $("[data-test-id=notification] .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на "+date));

    }

    @Test
    void shouldTestFormNameSpace () {
        $("[data-test-id=city] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("   Иванов Василий ");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 12000);
        $("[data-test-id=notification] .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на "+date));
    }

    @Test
    void shouldTestFormNameNotCorrect () {
        $("[data-test-id=city] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("Ivanov Vasiliy");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestFormNameEmpty () {
        $("[data-test-id=city] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFormPhoneIncorrectly () {
        $("[data-test-id=city] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+46546");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestFormAgreementEmpty () {
        $("[data-test-id=city] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(today);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79273332211");

        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=agreement].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }

    @Test
    void shouldTestFormCityAndNameAndPhoneEmpty () {

        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }
}

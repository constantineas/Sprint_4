package com.example.spintsixproject;

import Constants.Const;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LandingPage;
import pageObject.ScooterOrderRent;
import pageObject.ScooterOrderWho;

@RunWith(Parameterized.class)
public class ScooterOrderTest {

    WebDriver driver;

    private final String userName;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;
    private final String verifyText;


    public ScooterOrderTest(
            String userName,
            String surname,
            String address,
            String metro,
            String phone,
            String date,
            String comment,
            String verifyText)
    {
        this.userName = userName;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment= comment;
        this.verifyText = verifyText;
    }



    @Parameterized.Parameters
    public static Object[] getSumData()
    {
        return new Object[][]
                { // передали тестовые данные
                        {"Костя", "Асалин", "Москва", "сокольники", "+79636563763", "30.10.2022", "Позвонить за час", "Заказ оформлен"},
                        {"Артем", "Тарасов", "Москва", "черкизовская", "+79853654102", "29.10.2022", "Позвонить за пол часа", "Заказ оформлен"},
                };
    }

    @Before
    public void startWebDriver() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.get(Const.LANDING_PAGE_URL);
    }

    // Проверка заказа самоката по кнопке вверху
    @Test
    public void testClickUpButton() throws InterruptedException {
        ScooterOrderWho scooterOrderWho = new ScooterOrderWho(driver);
        ScooterOrderRent scooterOrderRent = new ScooterOrderRent(driver);
        LandingPage landingPage = new LandingPage(driver);

        // Клик на верхнюю кнопку Заказать
        landingPage.clickHeadButton();

        // Заполняем данные на странице Для кого самокат
        scooterOrderWho.setOrderWho(userName, surname, address, metro, phone);

        // нажимаем на кнопку далее
        scooterOrderWho.clickButtonNext();

        // Заполняем данные на странице Про аренду
        scooterOrderRent.setAboutRent(date, comment);

        // Нажимаем кнопку Заказать
        scooterOrderRent.clickOrderButton();

        // Нажимаем кнопку Да
        scooterOrderRent.clickYesButton();

        // Проверяем, что заказ успешно оформлен
        scooterOrderRent.isOrderIsProcessed(verifyText);

    }

    // Проверка заказа самоката по кнопке внизу
    @Test
    public void testClickDownButton() throws InterruptedException {

        ScooterOrderWho scooterOrderWho = new ScooterOrderWho(driver);
        ScooterOrderRent scooterOrderRent = new ScooterOrderRent(driver);
        LandingPage landingPage = new LandingPage(driver);

        // Клик на нижнюю кнопку Заказать
        landingPage.clickMiddleButton();

        // Заполняем данные на странице Для кого самокат
        scooterOrderWho.setOrderWho(userName, surname, address, metro, phone);

        // нажимаем на кнопку далее
        scooterOrderWho.clickButtonNext();

        // Заполняем данные на странице Про аренду
        scooterOrderRent.setAboutRent(date, comment);

        // Нажимаем кнопку Заказать
        scooterOrderRent.clickOrderButton();

        // Нажимаем кнопку Да
        scooterOrderRent.clickYesButton();

        // Проверяем, что заказ успешно оформлен
        scooterOrderRent.isOrderIsProcessed(verifyText);
    }

    // Завершаем работу драйвера
    @After
    public void after(){
        driver.quit();
    }

}

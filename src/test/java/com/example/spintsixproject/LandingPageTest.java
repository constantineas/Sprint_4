package com.example.spintsixproject;

import Constants.Const;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LandingPage;

import java.time.Duration;

@RunWith(value = Parameterized.class)
public class LandingPageTest {

    private WebDriver driver;

    LandingPage scrollDown;

    // Переменная для передачи параметрезированных данных
    private final String qaText;
    private final int count;

    public LandingPageTest(String qaText, int count){
        this.qaText = qaText;
        this.count = count;
    }

    @Parameterized.Parameters
    public static Object[] getData()
    {
        return new Object[][]
                {// передали тестовые данные
                        {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                        {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                        {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                        {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                        {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                        {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                        {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                        {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7},
                };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(Const.LANDING_PAGE_URL);
        scrollDown = new LandingPage(driver);
        scrollDown.scrollPageDown();
    }

    // Завершаем работу драйвера
    @After
    public void tearDown() {
        driver.quit();
    }

    // Параметризированный тест проверки списка "Вопросы о важном"
    @Test
    public void questionsMenu() {
        String expectedText = qaText;
        LandingPage questionsFaq = new LandingPage(driver, count);
        scrollDown.scrollPageDown();
        questionsFaq.listClassName.get(count).click();
        questionsFaq.waitForLoadQuestion();
        String actual = questionsFaq.accordionPanel.get(count).getText();
        Assert.assertEquals(expectedText, actual);
    }

}

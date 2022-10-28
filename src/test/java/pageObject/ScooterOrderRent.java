package pageObject;

import Constants.Const;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://qa-scooter.praktikum-services.ru/order
public class ScooterOrderRent {

    private WebDriver driver;
    
    // Локатор для поля Когда привезти самокат
    @FindBy(css = "input[placeholder=\"* Когда привезти самокат\"]")
    public WebElement userDate;

    // Локатор для поля Срок аренды
    @FindBy(css = "div[class=\"Dropdown-placeholder\"]")
    public WebElement userTime;

    // Локатор для выбора кол-ва суток аренды
    @FindBy(css = "div[class=\"Dropdown-menu\"]")
    public WebElement userDays;

    // Локатор для чек-бокса черный жемчуг
    @FindBy(css = "input[id=\"black\"]")
    public WebElement blackCheckbox;

    // Локатор для чек-бокса серая безысходность
    @FindBy(css = "input[id=\"grey\"]")
    public WebElement greyCheckbox;

    // Локатор для комментарий для курьера
    @FindBy(css = "input[placeholder=\"Комментарий для курьера\"]")
    public WebElement userComment;

    // Локатор для кнопки Заказать
    @FindBy(css = "button[class=\"Button_Button__ra12g Button_Middle__1CSJM\"]")
    public WebElement orderButton;

    // Локатор для кнопки Да в модальном окне
    @FindBy(css = "div.Order_Buttons__1xGrp:nth-child(2) > button:nth-child(2)")
    public WebElement yesButton;

    // Локатор для проверки текста "Заказ оформлен"
    @FindBy(css = ".Order_ModalHeader__3FDaJ")
    public WebElement orderIsProcessed;

    // Метод заполняем поле Когда привезти самокат
    public void setDate(String date) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userDate));

        userDate.sendKeys(date);
        userDate.sendKeys(Keys.ENTER);
    }

    // Метод для нажатия по выпадающему списку Срок аренды
    public void setTime() {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userTime));
        userTime.click();
        userDays.click();
    }

    // Метод для нажатия чек-бокс черный жемчуг
    public void setCheckBoxBlack() {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(blackCheckbox));
        blackCheckbox.click();
    }

    // Метод для нажатия чек-бокс серая безысходность
    public void setCheckBoxGray() {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(greyCheckbox));
        greyCheckbox.click();
    }

    // Метод для заполнения поле Комментарий
    public void setComment(String text) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userComment));
        userComment.sendKeys(text);
    }

    // Метод для нажатия по Заказать
    public void clickOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(orderButton));
        orderButton.click();
    }

    // Метод для подтверждение заказа
    public void clickYesButton(){
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(yesButton));
        yesButton.click();
    }

    // Метод проверки сообщения о заказе
    public void isOrderIsProcessed(String expected) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(orderIsProcessed));
        WebElement actual = orderIsProcessed;
        Assert.assertTrue("Произошла ошибка, заказ не создан",actual.getText().contains(expected));
    }

    // Заполнение формы Про аренду
    public void setAboutRent(String date, String textForCourier) throws InterruptedException {
        setDate(date);
        setTime();
        setCheckBoxBlack();
        setCheckBoxGray();
        setComment(textForCourier);
    }

    public ScooterOrderRent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
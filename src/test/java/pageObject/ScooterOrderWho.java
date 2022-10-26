package pageObject;

import Constants.Const;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ScooterOrderWho {

    private WebDriver driver;

    // Локатор для поля Имя
    @FindBy(xpath = "//input[@placeholder=\"* Имя\"]")
    public WebElement userName;

    // Локатор для поля Фамилия
    @FindBy(xpath = "//input[@placeholder=\"* Фамилия\"]")
    public WebElement userSurname;

    // Локатор для поля Адреса
    @FindBy(xpath = "//input[contains(@placeholder, \"куда\")]")
    public WebElement userAdress;

    // Локатор для поля Станция метро
    @FindBy(xpath = "//*[@placeholder=\"* Станция метро\"]")
    public WebElement userMetro;

    // Локатор для поля Телефон
    @FindBy(xpath = "//input[contains(@placeholder, \"на\")]")
    public WebElement userPhone;

    // Локатор для кнопки Далее
    @FindBy(css = "button[class*=\"Middle\"]")
    public WebElement nextButton;


    // Метод заполняет поле Имя
    public void setName(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userName));
        userName.sendKeys(username);
    }

    // Метод заполняет поле Фамилия
    public void setSurname(String surname) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userSurname));
        userSurname.sendKeys(surname);
    }

    // Метод заполняет поле Адрес
    public void setAdress(String adress) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userAdress));
        userAdress.sendKeys(adress);
    }

    // Метод заполняет поле Станция метро
    public void setMetro(String metro) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userMetro));
        userMetro.click();
        userMetro.sendKeys(metro);
        userMetro.sendKeys(Keys.UP);
        userMetro.sendKeys(Keys.ENTER);
    }

    // Метод заполняет поле номера
    public void setPhone(String numberPhone) {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(userPhone));
        userPhone.sendKeys(numberPhone);
    }

    // Метод для нажатия по кнопке Далее
    public void clickButtonNext(){
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();
    }

    // Заполнение формы "Для кого самокат"
    public void setOrderWho(String username, String surname, String adress, String metro, String numberPhone) throws InterruptedException {
        setName(username);
        setSurname(surname);
        setAdress(adress);
        setMetro(metro);
        setPhone(numberPhone);
    }

    // Конструктор
    public ScooterOrderWho(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
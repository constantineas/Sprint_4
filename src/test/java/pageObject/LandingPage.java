package pageObject;

import Constants.Const;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LandingPage {

    // Веб драйвер
    private WebDriver driver;
    private int count;


    // Значение для локатора прокрутки
    protected String locatorScroll = "arguments[0].scrollIntoView();";

    // "Ответы" о важном
    @FindBy(xpath = "//p")
    public List<WebElement> accordionPanel;

    // "Вопросы" о важном
    @FindBy(xpath = "//*[@class='accordion__button']")
    public List<WebElement> listClassName;

    @FindBy(css = "button[class=\"Button_Button__ra12g\"]")
    public WebElement headButton;

    @FindBy(css = "button[class*=\"Middle\"]")
    public WebElement middleButton;

    // Метод прокрутка до элемента на странице "Самокат на пару дней"
    public void scrollPageDown(){
        WebElement element = driver.findElement(By.cssSelector("#accordion__heading-7"));
        ((JavascriptExecutor)driver).executeScript(locatorScroll, element);
    }

    // Метод ожидания прогрузки элементов
    public void waitForLoadQuestion() {
        new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                until(ExpectedConditions.visibilityOf(accordionPanel.get(count)));

    }

    // Метод для нажатия по кнопке Заказать
    public void clickOrderButton(int x){
        if (x == 0) {
            new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                    until(ExpectedConditions.visibilityOf(headButton));
            headButton.click();
        } else if (x == 1) {
            ((JavascriptExecutor)driver).executeScript(locatorScroll, middleButton);
            new WebDriverWait(driver, Duration.ofSeconds(Const.TIME_OUT_IN_SECONDS)).
                    until(ExpectedConditions.visibilityOf(middleButton));
            middleButton.click();
        }

    }

    // Конструктор
    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Конструктор для параметризированного теста
    public LandingPage(WebDriver driver,int count) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.count = count;
    }


}


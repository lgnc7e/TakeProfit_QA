package botsystem.core;

import botsystem.pages.HomePage;
import botsystem.pages.LoginPage;
import botsystem.pages.RegisterPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class BasePage {

    Logger logger = LoggerFactory.getLogger(BasePage.class);
    public WebDriver driver;
    JavascriptExecutor js;
    public WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    public void click(WebElement element) {
        //scrollToElement(element);
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }
    public void scrollDownOnePage() {
        js.executeScript("window.scrollBy(0, window.innerHeight);");

    }
    public void scrollDownByPixels(int pixels) {
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public void scrollUpOnePage() {
        js.executeScript("window.scrollBy(0, -window.innerHeight);");
    }

    public void scrollUpByPixels(int pixels) {
        js.executeScript("window.scrollBy(0, -" + pixels + ");");
    }

    public void scrollToElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);",element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    @FindBy(id = "email")
    WebElement enterEmail;

    @FindBy(id = "password")
    WebElement enterPassword;

    @FindBy(id = "repeat-password")
    WebElement confPassword;

    public BasePage enterPersonalData(String user, String password) {
        type(enterEmail, user);
        type(enterPassword, password);
        return this;
    }

    public BasePage confirmPassword(String passwordConfirm) {
        type(confPassword, passwordConfirm);
        return this;
    }

    @FindBy(xpath = "//button[normalize-space(text())='Create account']")
    WebElement confButton;

    public RegisterPage clickOnSignUpButton() {
        click(confButton);
        pause(100);
        return new RegisterPage(driver);
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    public LoginPage clickOnSubmitButton() {
        click(submitButton);
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Account')]")
    WebElement accountButton;

    public HomePage clickOnAccountButton() throws AWTException {
        pause(500);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(accountButton));
        click(accountButton);
        return new HomePage(driver);
    }


    public HomePage clickOnOKButton() throws AWTException {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return new HomePage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='You are registered and logged in']")
    WebElement infRegMessage;

    public HomePage verifyInfRegMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(infRegMessage));

        assert infRegMessage.getText().contains("You are registered and logged") :
                "Not expected message";
        return new HomePage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='You are logged in']")
    WebElement infLoginMessage;

    public LoginPage verifyInfLoginMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(infLoginMessage));

        assert infLoginMessage.getText().contains("You are logged") :
                "Not expected login message";
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//div[contains(text(),'Account deleted successfully')]")
    WebElement infDelMessage;

    public HomePage verifyInfDelMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(infDelMessage));

        assert infDelMessage.getText().contains("Account deleted successfully") :
                "Not expected email message";
        return new HomePage(driver);
    }

    public void roboterAufstand(int tab, int down, int space, int enter) throws AWTException {
        Robot robot = new Robot();

        for (int i = 0; i < tab; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            pause(100);
        }

        for (int i = 0; i < down; i++) {
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            pause(100);
        }

        for (int i = 0; i < space; i++) {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            pause(100);
        }

        for (int i = 0; i < enter; i++) {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            pause(100);
        }

    }

    public void roboterAufstandZweiteSeries(int pageUp, int pageDown) throws AWTException {
        Robot robot = new Robot();

        for (int i = 0; i < pageUp; i++) {
            robot.keyPress(KeyEvent.VK_PAGE_UP);
            robot.keyRelease(KeyEvent.VK_PAGE_UP);
            pause(100);
        }

        for (int i = 0; i < pageDown; i++) {
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
            pause(100);
        }
    }


}

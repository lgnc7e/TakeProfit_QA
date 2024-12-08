package botsystem.pages;

import botsystem.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HomePage extends BasePage {
    Logger logger = LoggerFactory.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space(text())='Log in']")
    WebElement loginButton;

    public HomePage clickOnLoginButton() {
        pause(500);
        click(loginButton);
        return new HomePage(driver);
    }

    @FindBy(xpath = "//a[normalize-space(text())='Sing up']")
    WebElement regButton;

    public HomePage clickOnRegistrationButton() {
        click(regButton);
        return new HomePage(driver);
    }

    @FindBy(xpath = "//a[normalize-space(text())='Exchanges']")
    WebElement exchButton;

    public HomePage clickOnExchangesButton() {
        pause(500);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(exchButton));
        click(exchButton);
        return new HomePage(driver);
    }

    @FindBy(xpath = "//a[normalize-space(text())='Bot']")
    WebElement botButton;

    @FindBy(xpath = "//button[@aria-label='Close']")
    WebElement closeButton;

    public HomePage clickOnBotButton() {
        click(closeButton);
        click(botButton);
        return new HomePage(driver);
    }

    @FindBy(xpath = "//a[normalize-space(text())='Simulation']")
    WebElement simulationButton;

    public HomePage clickOnSimulationButton() {
        pause(100);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(simulationButton));
        click(simulationButton);
        return new HomePage(driver);
    }
}

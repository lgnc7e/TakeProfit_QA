package botsystem.pages;

import botsystem.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;


public class RegisterPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(RegisterPage.class);

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Logout']")
    WebElement logoutButton;

    public void clickOnLogoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        click(logoutButton);
    }


    public RegisterPage verifyPassValidMessage(String expectedMessage) {
        WebElement messageElement = driver.findElement(
                By.xpath("//p[normalize-space(text())='" + expectedMessage + "']")

        );
        assert messageElement.getText().equals(expectedMessage) :
                "Expected message not displayed or incorrect: " + expectedMessage;
        return this;
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailInput;

    public RegisterPage verifyEmailValidMessage() {
        String[] expectedMessages = {
                "Часть адреса после символа '@' не должна содержать символ '@'.",
                "Адрес электронной почты должен содержать символ '@'. В адресе 'regqa.test' отсутствует символ '@'.",
                "Введите часть адреса до символа '@'. Адрес '@qa.test' неполный.",
                "Введите часть адреса после символа '@'. Адрес 'reg@' неполный.",
                "Часть адреса после символа '@' не должна содержать символ ' '.",
                "Часть адреса после символа '@' не должна содержать символ ' '.",
                "Часть адреса после символа '@' не должна содержать символ '#'.",
                "Недопустимое положение символа '.' в адресе 'qa.'.",
                "Недопустимое положение символа '.' в адресе '.test'."
        };

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", emailInput);

        boolean checkMessage = Arrays.asList(expectedMessages).contains(validationMessage);
        System.out.println("Validation Message: " + validationMessage);

        Assert.assertTrue(validationMessage != null, "Validation message is null");

        return new RegisterPage(driver);
    }


}

package botsystem.pages;

import botsystem.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.time.Duration;

public class AccountPage extends BasePage {

    Logger logger = LoggerFactory.getLogger(AccountPage.class);

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Delete Account']")
    WebElement deleteButton;

    @FindBy(xpath = " //button[normalize-space(text())='Yes, delete my account']")
    WebElement confirmDeleteButton;

    public AccountPage deleteAccount() throws AWTException {
        scrollToElement(deleteButton);
        click(deleteButton);
        click(confirmDeleteButton);
        clickOnOKButton();
        return new AccountPage(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Change Email']")
    WebElement changeEmailButton;

    @FindBy(xpath = "//input[contains(@class,'mt-3 block')]")
    WebElement newEmailField;

    @FindBy(xpath = "//button[normalize-space(text())='Save Changes']")
    WebElement saveChangesButton;


    public AccountPage changeUserEmail(String newEmail) {
        click(changeEmailButton);
        type(newEmailField, newEmail);
        click(saveChangesButton);
        return new AccountPage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='Email changed successfully']")
    WebElement changeEmailMessage;

    public AccountPage verifyInfChangeEmailMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(changeEmailMessage));

        assert changeEmailMessage.getText().contains("Email changed successfully") :
                "Not expected email message";
        return new AccountPage(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Change Password']")
    WebElement changePasswordButton;

    @FindBy(id = "password")
    WebElement newPasswordField;

    @FindBy(id = "repeat-password")
    WebElement repeatNewPasswordField;

    @FindBy(xpath= "//button[normalize-space(text())='Update Password']")
    WebElement updatePasswordButton;


    public AccountPage changeUserPassword(String password) throws AWTException {
        pause(500);
        click(changePasswordButton);
        type(newPasswordField, password);
        type(repeatNewPasswordField, password);
        click(updatePasswordButton);
        return new AccountPage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='Password changed successfully']")
    WebElement changePasswordMessage;

    public AccountPage verifyChangePasswordMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(changePasswordMessage));

        assert changePasswordMessage.getText().contains("Password changed successfully") :
                "Not expected email message";
        return new AccountPage(driver);
    }
}

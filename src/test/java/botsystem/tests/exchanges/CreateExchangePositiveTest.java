package botsystem.tests.exchanges;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.ExchangesPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import botsystem.utils.AllureIgnore;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class CreateExchangePositiveTest extends TestBase {

    @BeforeMethod
    @io.qameta.allure.Step(" ")
    public void precondition() {
        new HomePage(driver)
                .clickOnRegistrationButton();
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton();
        new HomePage(driver)
                .clickOnExchangesButton();
    }

    @Test(invocationCount = 1)
    public void creationExchangePositiveTest() {
        new ExchangesPage(driver)
                .clickOnAddButton()
                .enterExchangeName(ExchangeName)
                .setAsDefault()
                .enterKeys(apiKey, secretKey)
                .clickOnAddExchangeButton()
                .verifyExchangeIsExist(ExchangeName);
    }

    @AfterMethod
    @io.qameta.allure.Step(" ")
    public void postCondition() throws AWTException {
        new ExchangesPage(driver)
                .choiseExchange(ExchangeName)
                .clickOnDelExchangeButton()
                .verifyIsDeletionSafe()
                .verifyDeletion();
        new HomePage(driver)
                .clickOnAccountButton();
        new AccountPage(driver)
                .deleteAccount();
    }

}

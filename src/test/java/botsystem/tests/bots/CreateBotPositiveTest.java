package botsystem.tests.bots;

import botsystem.core.TestBase;
import botsystem.pages.*;
import botsystem.utils.AllureIgnore;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class CreateBotPositiveTest extends TestBase {


    @BeforeMethod
    @io.qameta.allure.Step(" ")
    public void precondition() {
        new HomePage(driver)
                .clickOnRegistrationButton();
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton();
    }

    @Test(invocationCount = 1)
    public void createBotPositiveTest() {
        new BotPage(driver)
                .clickCreateNewBotButton()
                .enterBotData("WLDEUR", "Short", "13.0", "true", "true", "true","EMA", "1M")
                .clickOnCreateBotButton()
                .verifyBotAddMessage();
    }

    @AfterMethod
    @io.qameta.allure.Step(" ")
    public void postCondition() throws AWTException {
        new BotPage(driver)
                .deleteBot();
        new HomePage(driver)
                .clickOnAccountButton();
        new AccountPage(driver)
                .deleteAccount();
    }

}

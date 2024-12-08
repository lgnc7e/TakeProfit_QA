package botsystem.tests.bots;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.BotPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class DeleteBotPositiveTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver)
                .clickOnRegistrationButton();
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton();
        new BotPage(driver)
                .clickCreateNewBotButton()
                .enterBotData("WLDEUR", "Short", "13.0", "true", "true", "true","EMA", "1M")
                .clickOnCreateBotButton();
    }

    @Test(invocationCount = 1)
    public void deleteBotPositiveTest() throws AWTException {
        new BotPage(driver)
                .deleteBot()
                .verifyBotDeleteMessage();

    }

    @AfterMethod
    public void postCondition() throws AWTException {
        new HomePage(driver)
                .clickOnAccountButton();
        new AccountPage(driver)
                .deleteAccount();
    }
}

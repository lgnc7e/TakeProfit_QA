package botsystem.tests.botsCSV;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.BotPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import botsystem.utils.AllureIgnore;
import botsystem.utils.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class CreateBotsShortPositiveTest extends TestBase {

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


    @Test(dataProvider = "ValidShortBotTest", dataProviderClass = DataProviders.class, invocationCount = 1)
    public void createBotShortTest(String tradingPair, String type, String deposit, String stopLoss, String takeProfit, String pumpDump, String indicator, String interval) {
        new BotPage(driver)
                .clickCreateNewBotButton()
                .enterBotData(tradingPair,type,deposit,stopLoss,takeProfit,pumpDump,indicator,interval)
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

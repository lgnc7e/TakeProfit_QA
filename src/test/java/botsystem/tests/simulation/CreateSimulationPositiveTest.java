package botsystem.tests.simulation;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.BotPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class CreateSimulationPositiveTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver)
                .clickOnRegistrationButton();
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton();
        new HomePage(driver)
                .clickOnSimulationButton();
    }

    @Test(invocationCount = 1)
    public void createSimulationTest() {
        new BotPage(driver)
                .enterBotData("WLDEUR", "Short", "13.0", "true", "true", "true","EMA", "3m")
                .clickRunSimulationButton()
                .verifyIsSimulationExsist();
    }

    @AfterMethod
    public void postСondition() throws AWTException {
        new HomePage(driver)
                .clickOnAccountButton();
        new AccountPage(driver)
                .deleteAccount();
    }

}
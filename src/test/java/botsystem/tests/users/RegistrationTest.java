package botsystem.tests.users;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class RegistrationTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    @BeforeMethod
    public void precondition() {
        new HomePage(driver)
                .clickOnRegistrationButton();
    }

    @Test(invocationCount = 1)
    public void registrationPositiveTest() {
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton()
                .verifyInfRegMessage();
    }

    @AfterMethod
    public void postCondition() throws AWTException {
        new HomePage(driver)
                .clickOnAccountButton();
        new AccountPage(driver)
                .deleteAccount();
    }

}

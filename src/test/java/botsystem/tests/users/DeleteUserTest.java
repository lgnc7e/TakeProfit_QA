package botsystem.tests.users;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class DeleteUserTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(DeleteUserTest.class);

    @BeforeMethod
    public void precondition() {
        new HomePage(driver)
                .clickOnRegistrationButton();
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton();
    }

    @Test(invocationCount = 1)
    public void deleteUserPositiveTest() throws AWTException {
        new HomePage(driver)
                .clickOnAccountButton();
        new AccountPage(driver)
                .deleteAccount();
        new HomePage(driver)
                .verifyInfDelMessage();
    }
}

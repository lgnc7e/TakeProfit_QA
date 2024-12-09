package botsystem.tests.users;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import botsystem.utils.AllureIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class ChangePasswordTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(ChangePasswordTest.class);


    @BeforeMethod
    @io.qameta.allure.Step(" ")
    public void precondition() throws AWTException {
        new HomePage(driver)
                .clickOnRegistrationButton();
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton();
        new HomePage(driver)
                .clickOnAccountButton();
    }

    @Test(invocationCount = 1)
    public void changeUserPasswordPositiveTest() throws AWTException {
        new AccountPage(driver)
                .changeUserPassword(newPassword)
                .verifyChangePasswordMessage();
    }

    @AfterMethod
    @io.qameta.allure.Step(" ")
    public void postCondition() throws AWTException {
        new AccountPage(driver)
                .deleteAccount();
    }

}

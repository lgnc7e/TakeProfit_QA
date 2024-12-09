package botsystem.tests.users;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.HomePage;
import botsystem.pages.LoginPage;
import botsystem.pages.RegisterPage;
import botsystem.utils.AllureIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class LoginTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(LoginTest.class);


    @BeforeMethod
    @io.qameta.allure.Step(" ")
    public void precondition() {
        new HomePage(driver)
                .clickOnRegistrationButton();
        new RegisterPage(driver)
                .enterPersonalData(email, password)
                .confirmPassword(password)
                .clickOnSignUpButton()
                .clickOnLogoutButton();
    }

    @Test(invocationCount = 1)
    public void loginPositiveTest() {
        new HomePage(driver)
                .clickOnLoginButton();
        new LoginPage(driver)
                .enterPersonalData(email, password)
                .clickOnSubmitButton()
                .verifyInfLoginMessage();
    }


    @AfterMethod
    @io.qameta.allure.Step(" ")
    public void postCondition() throws AWTException {
        new HomePage(driver)
                .clickOnAccountButton();
        new AccountPage(driver)
                .deleteAccount();
    }

}

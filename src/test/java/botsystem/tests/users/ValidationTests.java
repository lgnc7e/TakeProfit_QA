package botsystem.tests.users;

import botsystem.core.TestBase;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import botsystem.utils.DataProviders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ValidationTests extends TestBase {

    Logger logger = LoggerFactory.getLogger(ValidationTests.class);

    @Test(dataProvider = "registration1", dataProviderClass = DataProviders.class)
    public void passwordValidationNegativeTest(String email, String password, String expectedMessage) {
        try {
            new HomePage(driver).clickOnRegistrationButton();
            new RegisterPage(driver)
                    .enterPersonalData(email, password)
                    .confirmPassword(password)
                    .clickOnSignUpButton()
                    .verifyPassValidMessage(expectedMessage);

            logger.info("Password validation test passed for email: {}", email + " and password: " + password);
        } catch (AssertionError e) {
            logger.error("Password validation test failed for: {}. Expected: {}, but got an error: {}",
                    email, expectedMessage, e.getMessage());
            throw e;
        }
    }


    @Test(dataProvider = "registration2", dataProviderClass = DataProviders.class)
    public void emailValidationNegativeTest(String email, String password) {
        try {
            new HomePage(driver).clickOnRegistrationButton();
            new RegisterPage(driver)
                    .enterPersonalData(email, password)
                    .confirmPassword(password)
                    .clickOnSignUpButton()
                    .verifyEmailValidMessage();

            logger.info("Email validation test passed for email: {}", email + " and password: " + password);
        } catch (AssertionError e) {
            logger.error("Email validation test failed for: {}. Error message: {}",
                    email, e.getMessage());
            throw e;
        }
    }
}

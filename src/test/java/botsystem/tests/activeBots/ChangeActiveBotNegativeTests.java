package botsystem.tests.activeBots;

import botsystem.core.TestBase;
import botsystem.pages.AccountPage;
import botsystem.pages.BotPage;
import botsystem.pages.HomePage;
import botsystem.pages.RegisterPage;
import botsystem.utils.AllureIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class ChangeActiveBotNegativeTests extends TestBase {

    private static final Logger logger = LoggerFactory.getLogger(ChangeActiveBotNegativeTests.class);


    @BeforeMethod
    @io.qameta.allure.Step(" ")
    public void preCondition() throws AWTException {
        logger.info("Starting preCondition setup...");
        try {
            new HomePage(driver)
                    .clickOnRegistrationButton();
            logger.info("Clicked on registration button.");
            new RegisterPage(driver)
                    .enterPersonalData(email, password)
                    .confirmPassword(password)
                    .clickOnSignUpButton();
            logger.info("User registered with email: {}", email);
            logger.info("Starting bot creation process...");
            new BotPage(driver)
                    .clickCreateNewBotButton()
                    .enterBotData("WLDEUR", "Short", "13.0", "true", "true", "true","EMA", "1M")
                    .clickOnCreateBotButton();
            logger.info("Bot created successfully with the specified data.");
        } catch (Exception e) {
            logger.error("Error during preCondition setup: {}", e.getMessage());
            throw e;
        }
    }


    @Test
    public void changeActiveBotNegativeTest() throws AWTException {
        logger.info("Running changeActiveBotNegativeTest...");
        new BotPage(driver)
                .changeBotStatus( "START BOT")
                .editBot()
                .clickOnSaveChangesButton()
                .verifyMessage("Unable to change running bot");
        logger.info("Unable to change running bot");
    }


    @AfterMethod
    @io.qameta.allure.Step(" ")
    public void postCondition() throws AWTException {
        logger.info("Starting postCondition cleanup...");
        try {
            new BotPage(driver)
                    .clickOnReturnButton()
                    .changeBotStatus( "STOP BOT")
                    .deleteBot();
//                    .scrollUp(300);
            logger.info("Bot Stopped and Deleted");
            new HomePage(driver)
                    .clickOnAccountButton();
            logger.info("Navigated to account page.");
            new AccountPage(driver)
                    .deleteAccount();
            logger.info("User account deleted.");
        } catch (Exception e) {
            logger.error("Error during postCondition cleanup: {}", e.getMessage());
            throw e;
        }
    }



}

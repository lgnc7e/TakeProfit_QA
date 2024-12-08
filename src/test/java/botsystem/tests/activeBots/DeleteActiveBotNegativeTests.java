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

public class DeleteActiveBotNegativeTests extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(DeleteActiveBotNegativeTests.class);

    @AllureIgnore
    @BeforeMethod
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
    public void deleteActiveBotNegativeTest() throws AWTException {
        logger.info("Running deleteActiveBotNegativeTest...");
        new BotPage(driver)
                .changeBotStatus( "START BOT")
                .deleteBot()
                .verifyMessage("Cannot delete running bot");
        logger.info("Cannot delete running bot");
    }

    @AllureIgnore
    @AfterMethod
    public void postCondition() throws AWTException {
        logger.info("Starting postCondition cleanup...");
        try {
            BotPage botPage = new BotPage(driver);
            if (botPage.isBotRunning()) {
                logger.info("Bot is running. Stopping the bot before deletion...");
                botPage.changeBotStatus( "STOP BOT");
                logger.info("Bot stopped successfully.");
            } else {
                logger.info("Bot is already stopped. Skipping stop action.");
            }
            botPage.deleteBot();
            logger.info("Bot deleted successfully.");
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

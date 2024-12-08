package botsystem.pages;

import botsystem.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.time.Duration;

public class BotPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(BotPage.class);
    WebDriverWait wait;

    public BotPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(text(),'Create new Bot')]")
    WebElement addBot;

    public BotPage clickOnAddBotButton() throws AWTException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int maxScrollAttempts = 10;
        int attempts = 0;

        while (attempts < maxScrollAttempts) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(addBot));
               click(addBot);
                return new BotPage(driver);
            } catch (Exception e) {
                scrollDownByPixels(200);
                attempts++;
            }
        }
        throw new RuntimeException("Unable to find and click the 'Add Bot' button after " + maxScrollAttempts + " scroll attempts.");
    }


    @FindBy(xpath = "(//select[contains(@class,'bg-gray-50 border')])[1]")
    WebElement setExchangeValue;

    @FindBy(xpath = "//div[@class='css-1jqq78o-placeholder']/following-sibling::div[1]")
    WebElement setTradingPairValue;

    @FindBy(xpath = "//select[contains(.,'LongShort')]")
    WebElement setTypeValue;

    @FindBy(xpath = "(//input[contains(@class,'bg-gray-50 border')])[1]")
    WebElement setDepositValue;

    @FindBy(xpath = "(//div[contains(@class,'relative w-11')])[1]")
    WebElement setStopLossValue;

    @FindBy(xpath = "(//div[contains(@class,'relative w-11')])[2]")
    WebElement setTakeProfitValue;

    @FindBy(xpath = "(//div[contains(@class,'relative w-11')])[3]")
    WebElement setPumpDumpValue;

    @FindBy(xpath = "(//select[contains(@class,'bg-gray-50 border')])[3]")
    WebElement setIndicatorValue;

    @FindBy(xpath = "(//input[contains(@class,'bg-gray-50 border')])[3]")
    WebElement setPeriodValue;

    @FindBy(xpath = "//span[text()='Interval']/following-sibling::select")
    WebElement setIntervalValue;

    @FindBy(xpath = "//button[normalize-space(text())='Delete bot']")
    WebElement deleteBotButton;


    public BotPage enterBotData(String tradingPair, String type, String deposit, String stopLoss, String takeProfit, String pumpDump, String indicator, String interval) {
        setTradingPair(tradingPair);
        setType(type);
        setDeposit(deposit);
        pause(500);
        setStopLoss(stopLoss);
        setTakeProfit(takeProfit);
        setPumpDump(pumpDump);
        clickOnAddIndicatorButton();
        setIndicator(indicator);
        setInterval(interval);
        return new BotPage(driver);
    }

    public BotPage deleteBot() throws AWTException {
        pause(500);
        scrollToElement(deleteBotButton);
        click(deleteBotButton);
        roboterAufstandZweiteSeries(1, 0);
        return new BotPage(driver);
    }

    private BotPage setExchangeValue(String ExchangeValue) {
        Select select = new Select(setExchangeValue);
        select.selectByVisibleText(ExchangeValue);
        return new BotPage(driver);
    }

    public BotPage setTradingPair(String TradingPair) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'css-19bb58m')]")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
            dropdown.click();

            WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text' and @aria-autocomplete='list']")));

            searchField.sendKeys(TradingPair);

            WebElement matchingOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + TradingPair + "']")));
            matchingOption.click();
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
        return new BotPage(driver);
    }

    private BotPage setType(String setType) {
        //pause(500);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(setTypeValue));

        Select select = new Select(setTypeValue);
        select.selectByVisibleText(setType);
        return new BotPage(driver);
    }

    private BotPage setDeposit(String setDeposit) {
        scrollToElement(setDepositValue);
        type(setDepositValue, setDeposit);
        return new BotPage(driver);
    }

    private BotPage setStopLoss(String stopLoss) {
        scrollToElement(setStopLossValue);
        if ("true".equalsIgnoreCase(stopLoss)) {
            click(setStopLossValue);
        }
        return new BotPage(driver);
    }

    private BotPage setTakeProfit(String takeProfit) {
        scrollToElement(setTakeProfitValue);
        if ("true".equalsIgnoreCase(takeProfit)) {
            click(setTakeProfitValue);
        }
        return new BotPage(driver);
    }

    public BotPage setPumpDump(String pumpDump) {
        scrollToElement(setPumpDumpValue);
        if ("true".equalsIgnoreCase(pumpDump)) {
            click(setPumpDumpValue);
        }
        return new BotPage(driver);
    }

    public BotPage setIndicator(String indicator) {
        scrollToElement(setIndicatorValue);
        Select select = new Select(setIndicatorValue);
        select.selectByVisibleText(indicator);
        return new BotPage(driver);
    }

    public BotPage setPeriod(String period) {
        scrollToElement(setPeriodValue);
        type(setPeriodValue, period);
        return new BotPage(driver);
    }

    public BotPage setInterval(String interval) {
        scrollToElement(setIntervalValue);
        Select select = new Select(setIntervalValue);
        select.selectByVisibleText(interval);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//button[contains(text(),'Create Bot')]")
    WebElement createBotGreenButton;

    public BotPage clickSaveButton() {
        scrollToElement(createBotGreenButton);

        click(createBotGreenButton);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Add indicator']")
    WebElement addIndicator;

    public BotPage clickOnAddIndicatorButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ожидание до 10 секунд
        wait.until(ExpectedConditions.elementToBeClickable(addIndicator)); // Ожидание, пока элемент станет кликабельным
        scrollToElement(addIndicator);
        click(addIndicator);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Create new Bot']")
    WebElement createNewBotButton;

    public BotPage clickCreateNewBotButton() {
        pause(500);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ожидание до 10 секунд
//        wait.until(ExpectedConditions.elementToBeClickable(createNewBotButton)); // Ожидание, пока кнопка станет кликабельной
        scrollToElement(createNewBotButton);
        click(createNewBotButton);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//button[contains(text(),'STOP BOT')]")
    WebElement stopBotButton;

    @FindBy(xpath = "//button[contains(text(),'START BOT')]")
    WebElement startBotButton;

    public BotPage changeBotStatus(String desiredStatus) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if ("START BOT".equalsIgnoreCase(desiredStatus)) {
            scrollUpOnePage();
            scrollToElement(startBotButton);
            //  wait.until(ExpectedConditions.elementToBeClickable(startBotButton));
            click(startBotButton);
            logger.info("Changing bot status to START BOT...");
        } else if ("STOP BOT".equalsIgnoreCase(desiredStatus)) {
            scrollUpOnePage();
            scrollToElement(stopBotButton);
           // wait.until(ExpectedConditions.visibilityOf(stopBotButton));
            click(stopBotButton);
            logger.info("Changing bot status to STOP BOT...");
        } else {
            throw new IllegalArgumentException("Invalid status provided: " + desiredStatus);
        }

        logger.info("Bot status changed to: {}", desiredStatus);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='Cannot delete running bot']")
    WebElement statusMessage;

    public BotPage verifyfBotStartStatus() {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(stopBotButton));

        assert stopBotButton.getText().contains("STOP BOT") :
                "Not expected text";
        return new BotPage(driver);
    }


    public BotPage verifyBotStopStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(startBotButton));

        assert startBotButton.getText().contains("START BOT") :
                "Not expected text";
        return new BotPage(driver);

    }

    public boolean isBotRunning() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(stopBotButton));
            logger.info("Bot is currently running (STOP BOT button is visible).");
            return true;
        } catch (Exception e) {
            logger.info("STOP BOT button is not visible. Checking for START BOT button...");
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(startBotButton));
            logger.info("Bot is currently stopped (START BOT button is visible).");
            return false;
        } catch (Exception e) {
            logger.error("Neither STOP BOT nor START BOT button is visible. Unable to determine bot status.");
            throw new IllegalStateException("Bot status could not be determined.");
        }
    }

    public BotPage verifyMessage(String expectedMessage) {
        String dynamicXpath = String.format("//p[contains(text(),'%s')]", expectedMessage);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));
            String actualText = messageElement.getText();

            assert actualText.contains(expectedMessage) :
                    "Expected message: " + expectedMessage + ", but found: " + actualText;

            System.out.println("Message found: " + actualText);
        } catch (Exception e) {
            System.err.println("Message not found with text: " + expectedMessage);
            throw e;
        }
        return this;
    }


    @FindBy(xpath = "//a[contains(text(),'Edit bot')]")
    WebElement editBotButton;
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveEditButton;

    public BotPage editBot() {
        scrollDownOnePage();
        scrollToElement(editBotButton);
        click(editBotButton);
        logger.info("Button edit was clicked");
        return new BotPage(driver);
    }

    public BotPage clickOnSaveChangesButton() {
        scrollDownOnePage();
        scrollToElement(saveEditButton);
        click(saveEditButton);
        logger.info("Button save was clicked");
        return this;
    }

    public BotPage scrollUp(int pixel) {
        scrollUpByPixels(pixel);
        return this;
    }

    public BotPage scrollDown(int pixel){
        scrollDownByPixels(pixel);
        return this;
    }

    @FindBy(xpath = "//button[normalize-space(text())='Create Bot']")
    WebElement createBotButton;
    public BotPage clickOnCreateBotButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(createBotButton));
        scrollToElement(createBotButton);
        click( createBotButton);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='Bot created successfully.']")
    WebElement createBotMessage;

    public BotPage verifyBotAddMessage() {
        assert createBotMessage.getText().contains("Bot created successfully.");
        return new BotPage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='Bot deleted successfully.']")
    WebElement deleteBotMessage;
    public BotPage verifyBotDeleteMessage() {
        assert deleteBotMessage.getText().contains("Bot deleted successfully.");
        return new BotPage(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Run simulation']")
    WebElement runSimulationButton;

    public BotPage clickRunSimulationButton() {
        scrollToElement(runSimulationButton);
        click(runSimulationButton);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Final Profit')]")
    WebElement isSimulationExsist;

    public BotPage verifyIsSimulationExsist() {
        assert isSimulationExsist.isDisplayed() : "The message 'Final Profit' does not exist on the page.";
        return new BotPage(driver);
    }

    @FindBy(xpath = "//a[@type='button']")
    WebElement returnButton;
    public BotPage clickOnReturnButton() {
        scrollDownOnePage();
        scrollToElement(returnButton);
        click(returnButton);
        return new BotPage(driver);
    }

    @FindBy(xpath = "//button[@aria-label='Close']")
    WebElement xButton;
    public BotPage clickCloseMessage() {
        try {
            if (xButton.isDisplayed()) {
               // scrollToElement(xButton);
                click(xButton);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Message not found.");
        }
        return new BotPage(driver);
    }





}

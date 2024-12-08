package botsystem.pages;

import botsystem.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;

public class ExchangesPage extends BasePage {

    Logger logger = LoggerFactory.getLogger(ExchangesPage.class);

    public ExchangesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Add exchange']")
    WebElement addButton;

    public ExchangesPage clickOnAddButton() {
        click(addButton);
        return new ExchangesPage(driver);
    }

    @FindBy(name = "name")
    WebElement enterName;

    public ExchangesPage enterExchangeName(String name) {
        type(enterName, name);
        return new ExchangesPage(driver);
    }

    @FindBy(xpath = "//input[@name='isDefault']/following-sibling::div[1]")
    WebElement isDefault;

    public ExchangesPage setAsDefault() {
        click(isDefault);
        return new ExchangesPage(driver);
    }

    @FindBy(name = "apiKey")
    WebElement apiKeyField;

    @FindBy(name = "secretKey")
    WebElement secretKeyField;

    public ExchangesPage enterKeys(String apiKey, String secretKey) {
        type(apiKeyField, apiKey);
        type(secretKeyField, secretKey);
        return new ExchangesPage(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Add Exchange']")
    WebElement addExchange;

    public ExchangesPage clickOnAddExchangeButton() {
        click(addExchange);
        return new ExchangesPage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='Exchange added successfully!']")
    WebElement infExchangeAddedMessage;

    public ExchangesPage verifyExchangeAddedMessage() {
        assert infExchangeAddedMessage.getText().contains("Exchange added successfully!");
        return new ExchangesPage(driver);
    }

    @FindBy(xpath = "//button[@aria-label='Close']")
    WebElement closeButton;

    public ExchangesPage clickOnCloseWindowButton() {
        click(closeButton);
        return new ExchangesPage(driver);
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ExchangesPage enterNewName(String newName) {
        type(enterName, newName);
        return new ExchangesPage(driver);
    }

    @FindBy(xpath = "//button[normalize-space(text())='Edit']")
    WebElement editButton;

    public ExchangesPage clickOnEditButton() {
        scrollToElement(editButton);
        click(editButton);
        return new ExchangesPage(driver);
    }

    public ExchangesPage verifyExchangeIsExist(String exchangeName) {
        String exchangeNameXPath = "//h5[contains(.,'" + exchangeName + "')]";
        System.out.println(exchangeNameXPath);
        Assert.assertTrue(isElementPresent(By.xpath(exchangeNameXPath)),
                "Exchange with name " + exchangeName + " does not exist");
        return new ExchangesPage(driver);
    }

    public ExchangesPage choiseExchange(String exchangeName) {

        By exchangeNameLocator = By.xpath("//h5[contains(.,'" + exchangeName + "')]");

        if (isElementPresent(exchangeNameLocator)) {
            WebElement exchangeElement = driver.findElement(exchangeNameLocator);
            scrollToElement(exchangeElement);
            click(exchangeElement);
        } else {
            System.out.println("Exchange with name \"" + exchangeName + "\" not found");
        }
        return new ExchangesPage(driver);
    }


    @FindBy(xpath = "//button[normalize-space(text())='Delete Exchange']")
    WebElement deleteExchButton;

    public ExchangesPage clickOnDelExchangeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(deleteExchButton));
        scrollToElement(deleteExchButton);
        click(deleteExchButton);
        return new ExchangesPage(driver);
    }

    @FindBy(xpath = "//p[normalize-space(text())='This exchange does not have bots, deletion is safe']")
    WebElement isDeletionSafeMessage;

    @FindBy(xpath = "//button[normalize-space(text())='Confirm deletion']")
    WebElement confirmDeletionButton;

    public ExchangesPage verifyIsDeletionSafe() {
        try {
            if (isDeletionSafeMessage.isDisplayed()) {
                scrollToElement(confirmDeletionButton);
                click(confirmDeletionButton);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Deletion is not safe");
        }
        return new ExchangesPage(driver);
    }

    @FindBy(xpath = "//div[normalize-space(text())='Exchange deleted successfully.']")
    WebElement ExchangeDeletedMessage;

    public ExchangesPage verifyDeletion() {
        Assert.assertTrue(ExchangeDeletedMessage.isDisplayed(),
                "Exchange deleted message is not displayed.");
        return new ExchangesPage(driver);
    }
}



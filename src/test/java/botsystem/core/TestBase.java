package botsystem.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    public String millis = String.valueOf(System.currentTimeMillis());
    public String email = millis +"qa@test.com";
    public String newEmail = millis +"new@test.com";
    public String ExchangeName = millis +"testExchange";
    public String apiKey = millis +"001";
    public String secretKey = millis +"002";
    public String password = "Qq$12345";
    public String newPassword = "Qq$123456";

    @BeforeMethod
    public void init(){
        String browser = System.getProperty("browser", "chrome");

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default: // Chrome как браузер по умолчанию
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
        }

        //driver = new ChromeDriver();
        //driver.manage().window().setPosition(new Point(2500,0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://takeprofit.tech/");
        //driver.get("http://localhost:5173/");




        }
    @AfterMethod(enabled = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

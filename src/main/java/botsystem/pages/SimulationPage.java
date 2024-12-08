package botsystem.pages;

import botsystem.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulationPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(SimulationPage.class);

    public SimulationPage(WebDriver driver) {
        super(driver);
    }

}

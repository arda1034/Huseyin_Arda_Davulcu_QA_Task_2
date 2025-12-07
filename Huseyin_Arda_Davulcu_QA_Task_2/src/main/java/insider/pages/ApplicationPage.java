package insider.pages;

import insider.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ApplicationPage extends BasePage {

    public ApplicationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean verifyApplicationPageUrl() {
        return driver != null && driver.getCurrentUrl() != null && driver.getCurrentUrl().contains("jobs.lever.co/useinsider");
    }
}

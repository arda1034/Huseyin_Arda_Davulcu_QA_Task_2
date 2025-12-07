package insider.pages;

import insider.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QaPage extends BasePage {

    public QaPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    private String qaPageTitle = "quality assurance job";

    @FindBy(css = "a[href*='department=qualityassurance']")
    private WebElement seeAllJobsButton;

    public boolean verifyQaPageTitle() {
        return driver != null && driver.getTitle() != null && driver.getTitle().contains(qaPageTitle);
    }

    public JobsPage clickSeeAllJobs() {
        waitForElementToClick(seeAllJobsButton);
        seeAllJobsButton.click();
        return new JobsPage(driver);
    }
}

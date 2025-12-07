package insider.pages;

import insider.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {

    // Classic POM for this page
    public CareersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        // PageFactory.initElements(this.driver, this);
    }

    public boolean verifyCareersPage() {
        return driver != null && driver.getTitle() != null && driver.getTitle().contains("Careers");
    }

    private final By teamsBlock = By.cssSelector("section#career-find-our-calling");
    private final By locationsBlock = By.cssSelector("section#career-our-location");
    private final By lifeAtInsiderBlock = By.cssSelector("section[data-id='a8e7b90']");

    public boolean isTeamsBlockExist() {
        return isElementExist(teamsBlock);
    }

    public boolean isLocationsBlockExist() {
        return isElementExist(locationsBlock);
    }

    public boolean islifeAtInsiderBlockExist() {
        return isElementExist(lifeAtInsiderBlock);
    }
}

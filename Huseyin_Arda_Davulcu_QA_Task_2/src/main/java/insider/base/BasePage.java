package insider.base;

import insider.pages.QaPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;

    @FindBy(css = "a#wt-cli-accept-btn")
    private WebElement acceptNecessaryCookiesButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void acceptCookies() {
        if (acceptNecessaryCookiesButton.isDisplayed())
            acceptNecessaryCookiesButton.click();
    }

    public void openHomePage() {
        driver.get("https://useinsider.com");
    }

    public QaPage openQaPage() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
        return new QaPage(driver);
    }

    public void waitForElementToVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForTextToBePresent(WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitForElementToClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementExist(By element) {
        return !driver.findElements(element).isEmpty();
    }
}

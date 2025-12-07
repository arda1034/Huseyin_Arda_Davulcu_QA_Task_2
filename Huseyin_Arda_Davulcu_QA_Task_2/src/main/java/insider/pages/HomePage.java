package insider.pages;

import insider.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    // Page Factory for this page
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = ".footer_copyright_holder")
    private WebElement copyrightInfo;

    @FindBy(xpath = "//a[@id='navbarDropdownMenuLink' and contains(text(), 'Company')]")
    private WebElement companyButton;

    @FindBy(xpath = "//a[@class='dropdown-sub' and contains(text(), 'Careers')]")
    private WebElement careersButton;

    public boolean verifyHomePageTitle() {
        return driver != null && driver.getTitle() != null && driver.getTitle().contains("Insider");
    }

    public String getTextCopyrightInfo() {
        return copyrightInfo.getText();
    }

    public CareersPage openCareersPage() {
        openHomePage();
        waitForElementToClick(companyButton);
        companyButton.click();
        waitForElementToClick(careersButton);
        careersButton.click();
        return new CareersPage(driver);
    }
}

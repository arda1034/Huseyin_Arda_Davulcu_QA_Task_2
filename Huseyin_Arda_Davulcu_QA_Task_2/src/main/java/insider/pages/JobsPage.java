package insider.pages;

import insider.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class JobsPage extends BasePage {

    public JobsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    private final String IstanbulLocation = "Istanbul, Turkiye";
    private final String qaDepartment = "Quality Assurance";

    @FindBy(css = "span#select2-filter-by-location-container")
    private WebElement filterByLocation;

    @FindBy(css = "span#select2-filter-by-department-container")
    private WebElement filterByDepartment;

    @FindBy(xpath = "//li[text()='" + IstanbulLocation + "']")
    private WebElement selectIstanbulTurkiye;

    @FindBy(xpath = "//li[text()='" + qaDepartment + "']")
    private WebElement selectQualityAssurance;

    @FindBy(css = ".position-list-item")
    private List<WebElement> jobListingItems;

    @FindBy(css = ".position-title")
    private List<WebElement> positionsTitles;

    @FindBy(css = ".position-location")
    private List<WebElement> positionsLocations;

    @FindBy(css = ".position-department")
    private List<WebElement> positionsDepartments;

    @FindBy(xpath = "//a[text()='View Role']")
    private List<WebElement> viewRoleButtons;

    public void selectLocationFilter() throws InterruptedException {
        waitForTextToBePresent(filterByDepartment, qaDepartment);
        waitForElementToClick(filterByLocation);
        filterByLocation.click();
        waitForElementToVisible(selectIstanbulTurkiye);
        scrollIntoView(selectIstanbulTurkiye);
        waitForElementToClick(selectIstanbulTurkiye);
        selectIstanbulTurkiye.click();
        waitForTextToBePresent(filterByLocation, IstanbulLocation);
        Thread.sleep(5000); // Waits until the job list is updated. I haven't found a stable and better method.
    }

    public boolean anyJobExist() {
        return jobListingItems != null && !jobListingItems.isEmpty();
    }

    public List<String> getAllJobsTitles() {
        return positionsTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAllJobsLocations() {
        return positionsLocations.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAllJobsDepartments() {
        return positionsDepartments.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void clickElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public ApplicationPage viewRoleButton() {
        if (viewRoleButtons == null || viewRoleButtons.isEmpty())
            throw new IllegalStateException("No view role buttons found!");
        WebElement firstButton = viewRoleButtons.get(0);
        scrollIntoView(firstButton);
        clickElement(firstButton);
        return new ApplicationPage(driver);
    }
}


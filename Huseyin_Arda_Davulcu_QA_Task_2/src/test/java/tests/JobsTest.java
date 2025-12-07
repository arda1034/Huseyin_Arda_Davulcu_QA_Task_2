package tests;

import insider.pages.ApplicationPage;
import insider.pages.JobsPage;
import insider.pages.QaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JobsTest extends BaseTest {

    private JobsPage jobsPage;
    public QaPage qaPage;
    public ApplicationPage applicationPage;

    @Test
    public void filterJobs() throws InterruptedException {
        qaPage = new QaPage(driver);
        qaPage.openQaPage();
        qaPage.acceptCookies();
        jobsPage = qaPage.clickSeeAllJobs();
        jobsPage.selectLocationFilter();
        Assert.assertTrue(jobsPage.anyJobExist());
        jobsPage.getAllJobsTitles().forEach(title -> Assert.assertTrue(title.contains("Quality Assurance")));
        jobsPage.getAllJobsDepartments().forEach(department -> Assert.assertTrue(department.contains("Quality Assurance")));
        jobsPage.getAllJobsLocations().forEach(location -> Assert.assertTrue(location.contains("Istanbul, Turkiye")));
    }

    @Test(dependsOnMethods = "filterJobs", alwaysRun = true)
    public void verifyApplicationPage() {
        String originalWindow = driver.getWindowHandle();
        applicationPage = jobsPage.viewRoleButton();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertTrue(applicationPage.verifyApplicationPageUrl());
    }
}

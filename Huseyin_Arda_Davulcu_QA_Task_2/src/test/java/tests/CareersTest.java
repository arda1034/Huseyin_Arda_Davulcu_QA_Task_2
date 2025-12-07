package tests;

import insider.pages.CareersPage;
import insider.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CareersTest extends BaseTest {

    private CareersPage careersPage;
    public HomePage homePage;

    @BeforeMethod
    public void setupCareerPage() {
        homePage = new HomePage(driver);
        careersPage = homePage.openCareersPage();
        careersPage.acceptCookies();
    }

    @Test
    public void verifyCareersPage() {
        Assert.assertTrue(careersPage.verifyCareersPage());
    }

    @Test
    public void checkBlocks() {
        Assert.assertTrue(careersPage.isTeamsBlockExist());
        Assert.assertTrue(careersPage.isLocationsBlockExist());
        Assert.assertTrue(careersPage.islifeAtInsiderBlockExist());
    }
}

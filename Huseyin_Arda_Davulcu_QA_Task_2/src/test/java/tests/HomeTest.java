package tests;

import insider.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class HomeTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setupHomePage() {
        homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.acceptCookies();
    }

    @Test
    public void verifyHomePage() {
        Assert.assertTrue(homePage.verifyHomePageTitle());
        Calendar calendar = Calendar.getInstance();
        String currentYear = Integer.toString(calendar.get(Calendar.YEAR));
        String expectedCopyright = "© " + currentYear + " Insider. All rights reserved.";
        assertEquals(homePage.getTextCopyrightInfo(), expectedCopyright);
    }

    @Test // for negative case
    public void negativeVerifyHomePage() {
        Assert.assertTrue(homePage.verifyHomePageTitle());
        String expectedCopyright = "© 2020 Insider. All rights reserved.";
        assertNotEquals(homePage.getTextCopyrightInfo(), expectedCopyright);
    }
}

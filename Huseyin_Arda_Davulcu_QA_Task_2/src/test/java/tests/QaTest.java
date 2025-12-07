package tests;

import insider.base.BasePage;
import insider.pages.QaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QaTest extends BaseTest {

    public BasePage basePage;

    @Test
    public void verifyQaPage() {
        basePage = new BasePage(driver);
        QaPage qaPage = basePage.openQaPage();
        qaPage.acceptCookies();
        Assert.assertTrue(qaPage.verifyQaPageTitle());
    }
}

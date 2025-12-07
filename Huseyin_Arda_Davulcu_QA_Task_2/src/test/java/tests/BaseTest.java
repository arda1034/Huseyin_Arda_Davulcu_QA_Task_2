package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    private Properties config;

    private WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    public WebDriver ConfigureDriver() {
        String browser = Objects.requireNonNullElse(config.getProperty("browser"), "chrome");
        switch (browser.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "chrome" -> driver = createChromeDriver();
            default -> {
                System.out.println("Unsupported browser: " + browser + ", defaulting to Chrome.");
                driver = createChromeDriver();
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException {
        config = new Properties();
        try (FileInputStream input = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/insider/resources/config.properties")) {
            config.load(input);
        }
        driver = ConfigureDriver();
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws IOException {
        boolean activateScreenshot = Boolean.parseBoolean(config.getProperty("screenshot"));
        if (activateScreenshot) {
            String testName = result.getMethod().getMethodName();
            String status = switch (result.getStatus()) {
                case ITestResult.SUCCESS -> "PASS";
                case ITestResult.FAILURE -> "FAIL";
                case ITestResult.SKIP -> "SKIP";
                default -> "UNKNOWN";
            };
            Files.createDirectories(Paths.get("test-output/screenshots"));
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("test-output/screenshots/" + testName + "_" + status + ".png");
            Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @AfterClass
    public void quit() {
        if (driver != null)
            driver.quit();
    }
}

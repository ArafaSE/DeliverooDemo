package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.util.concurrent.TimeUnit;

public class TestBase extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public  String HOME_URL = "https://deliveroo.co.uk/";

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        // headless browser testing with Chrome headless option
        else if (browserName.equalsIgnoreCase("chrome-headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--allow-running-insecure-content");
            driver = new ChromeDriver(chromeOptions);
        }

        driver.navigate().to(HOME_URL);
    }

    @AfterSuite
    public void stopDriver() throws InterruptedException {
       driver.quit();
    }

    // take screenshot when test case fail and add it in the screenshots folder
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed! - Taking screenshots..");
            Helper.captureScreenshot(driver, result.getName());
        }
    }

}

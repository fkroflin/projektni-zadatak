import org.example.pages.AmazonSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ThirdTest {
    private WebDriver driver;
    private AmazonSearchPage amazonSearchPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://www.amazon.com/");
        amazonSearchPage = new AmazonSearchPage(driver);
    }

    @Test
    public void testAmazonSearch() {
        amazonSearchPage.enterSearchTerm("Wireless Headphones");
        amazonSearchPage.clickSearchButton();

        String firstResultTitle = amazonSearchPage.getFirstResultTitle();
        System.out.println("First result title: " + firstResultTitle);

        Assert.assertTrue(firstResultTitle.toLowerCase().contains("headphone"), "Result does not match search query.");
    }

    @AfterMethod
    public void teardown() {
        //driver.quit();
    }
}

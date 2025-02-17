import org.example.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FifthTest {
    private WebDriver driver;
    private SearchPage searchPage;
    public String testURL = "https://weather.com/";

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to(testURL);
        searchPage = new SearchPage(driver);
        searchPage.handleCookies();
    }

    @Test
    public void testWeatherSearch() {
        searchPage.searchForItem("Zagreb");
        searchPage.clickFirstResult();

        String displayedCity = searchPage.getDisplayedCity();
        Assert.assertTrue(displayedCity.contains("Zagreb"), "Nije prikazan očekivani grad.");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
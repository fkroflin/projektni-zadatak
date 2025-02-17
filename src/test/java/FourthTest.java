import org.example.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FourthTest {
    private WebDriver driver;
    private SearchPage searchPage;
    public String testURL = "https://www.imdb.com/";

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to(testURL);
        searchPage = new SearchPage(driver);
    }

    @Test
    public void testImdbSearch() {
        searchPage.searchForItem("Inception");
        searchPage.clickFirstImdbResult();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

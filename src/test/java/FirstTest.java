import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
    //------------------- Global Variables -----------------------------------
    public WebDriver driver;
    public String testURL = "https://www.wikipedia.org/";

    //---------------------- Test Setup -----------------------------------
    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.navigate().to(testURL);
    }

    @Test
    public void wikipediaSearchTest() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        Thread.sleep(3000);

        WebElement firstResult = driver.findElement(By.cssSelector(".mw-search-result-heading a"));
        firstResult.click();

        Thread.sleep(3000);
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}

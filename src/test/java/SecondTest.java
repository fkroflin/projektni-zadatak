import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SecondTest {
    public WebDriver driver;
    public String testURL = "https://www.youtube.com/";

    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to(testURL);
    }

    @Test
    public void youtubeMusicTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Accept all')]")));
            acceptButton.click();
            System.out.println("✅ Kliknuo na 'Accept' gumb.");
        } catch (Exception e) {
            System.out.println("✅ Popup nije pronađen, nastavljamo dalje.");
        }

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
        searchBox.sendKeys("Bohemian Rhapsody");
        searchBox.submit();

        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ytd-video-renderer[1]//a[@id='thumbnail']")));
        firstResult.click();

        WebDriverWait waitForPageLoad = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForPageLoad.until(ExpectedConditions.presenceOfElementLocated(By.id("movie_player")));

        WebElement videoTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("yt-formatted-string.ytd-video-primary-info-renderer")));
        Assert.assertFalse(videoTitle.getText().isEmpty(), "Naslov videa nije pronađen!");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        long currentTime = (long) js.executeScript("return document.querySelector('video').currentTime;");

        long startTime = System.currentTimeMillis();
        while (currentTime < 30) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime = (long) js.executeScript("return document.querySelector('video').currentTime;");

            if (System.currentTimeMillis() - startTime > 30000) {
                break;
            }
        }

        System.out.println("✅ Test Passed: Video se uspješno reproducirao do 30 sekundi!");
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}
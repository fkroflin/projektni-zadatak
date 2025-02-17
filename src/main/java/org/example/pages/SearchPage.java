package org.example.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@name='q' or @id='suggestion-search']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit' or @id='suggestion-search-button']")
    private WebElement searchButton;

    @FindBy(xpath = "(//div[contains(@class,'styles__item')])[1]")
    private WebElement firstResult;

    @FindBy(xpath = "//h1[@class='CurrentConditions--location--1Ayv3']")
    private WebElement locationTitle;

    @FindBy(xpath = "//button[contains(text(),'Accept all') or contains(text(),'Reject all')]")
    private WebElement acceptCookiesButton;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void searchForItem(String query) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(query);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickFirstImdbResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//ul[contains(@class,'ipc-metadata-list')]/li)[1]//a")
        ));

        firstResult.click();
    }
    public void searchForCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(city);
    }

    public void clickFirstResult() {
        wait.until(ExpectedConditions.elementToBeClickable(firstResult)).click();
    }

    public String getDisplayedCity() {
        return wait.until(ExpectedConditions.visibilityOf(locationTitle)).getText();
    }

    public void handleCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
            System.out.println("Cookies prozor zatvoren.");
        } catch (Exception e) {
            System.out.println("Cookies prozor nije pronađen, nastavljamo s testom.");
        }
    }
}

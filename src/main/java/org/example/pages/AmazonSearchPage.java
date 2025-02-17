package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AmazonSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locatori
    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By firstResult = By.cssSelector(".s-title-instructions-style");

    // Konstruktor
    public AmazonSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Metoda za unos teksta u pretragu
    public void enterSearchTerm(String searchTerm) {
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchInput.sendKeys(searchTerm);
    }

    // Klik na dugme pretrage
    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    // Dohvati naslov prvog rezultata
    public String getFirstResultTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstResult)).getText();
    }
}

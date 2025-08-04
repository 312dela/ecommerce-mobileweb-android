package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    private By submitButton = By.id("login");
    public By toast = By.id("toast-container");
    private By hamburgerMenu = By.cssSelector("label[for='check']");

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    public void goToUrl() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
    }

    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForText(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void safeClick(By locator) {
        waitForElementVisible(locator);
        waitForElementClickable(locator);
        driver.findElement(locator).click();
    }

    public void changeInput(By locator, String text) {
        waitForElementVisible(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void safeType(By locator, String text) {
        waitForElementVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        waitForElementVisible(locator);
        return driver.findElement(locator).getText();
    }

    public void scrollToElement(By locator) {
        waitForElementVisible(locator);

        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void forceClick(By locator) {
        waitForElementVisible(locator);
        waitForElementClickable(locator);
        scrollToElement(locator);

        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickSubmitButton() {
        forceClick(submitButton);
    }

    public String getToastMessage() throws InterruptedException {
        waitForElementVisible(toast);
        Thread.sleep(500);
        return getText(toast);
    }

    public void openHamburgerMenu() {
        forceClick(hamburgerMenu);
    }
}

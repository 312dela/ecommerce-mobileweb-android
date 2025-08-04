package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumDriver;

public class OrderPage extends BasePage {
    private By shippingEmailLabel = By.cssSelector(".user__name label");
    private By shippingEmailField = By.cssSelector(".user__name input[type='text']");
    private By countryField = By.cssSelector("input[placeholder='Select Country']");
    private By orderButton = By.className("action__submit");
    private By toast = By.id("toast-container");

    public OrderPage(AppiumDriver driver) {
        super(driver);
    }

    public void changeEmailShippingInfo(String email) {
        scrollToElement(shippingEmailField);
        changeInput(shippingEmailField, email);

    }

    public void inputCountryShippingInfo(String locationInput, String selectLocation) {
        scrollToElement(shippingEmailField);
        safeType(countryField, locationInput);
        By locationOption = By.xpath("//*[contains(text(),'" + selectLocation + "')]");
        forceClick(locationOption);
    }

    public void clickOrder() {
        forceClick(orderButton);
    }

    public String getShippingEmailLabel() {
        return getText(shippingEmailLabel);
    }

    public String getToastMessage() throws InterruptedException {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                toast, "Please Enter Full Shipping Information"));
        return getText(toast);
    }
}

package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class OrderDetailsPage extends BasePage {
    private By emailBillingAddress = By.xpath("//div[contains(text(),'Billing Address')]/following-sibling::p[1]");

    public OrderDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public String getEmailBillingAddress() {
        scrollToElement(emailBillingAddress);
        return getText(emailBillingAddress);
    }
}

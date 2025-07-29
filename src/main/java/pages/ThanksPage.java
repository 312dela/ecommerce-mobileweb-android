package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class ThanksPage extends BasePage {
    private By ordersNavbar = By.cssSelector("button[routerlink='/dashboard/myorders']");
    private By orderIdLabel = By.cssSelector(".ng-star-inserted label[class='ng-star-inserted']:first-of-type");

    public ThanksPage(AppiumDriver driver) {
        super(driver);
    }

    public void goToMyOrders() {
        openHamburgerMenu();
        safeClick(ordersNavbar);
    }

    public String getOrderId() {
        scrollToElement(orderIdLabel);
        String text = getText(orderIdLabel);
        String orderId = text.split("\\|")[1].trim();
        return orderId;
    }
}

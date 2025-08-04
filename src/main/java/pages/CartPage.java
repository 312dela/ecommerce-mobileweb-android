package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class CartPage extends BasePage {
    private By productPrice = By.cssSelector(".cartSection p:not([class])");
    private By checkoutButton = By.cssSelector(".totalRow button");
    private By actualTotalPrice = By.xpath("//span[text()='Total']/following-sibling::span[1]");

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickCheckout() {

        forceClick(checkoutButton);
    }

    public int calculateTotalPrice() {
        waitForElementVisible(checkoutButton);

        List<WebElement> allProductsPrice = driver.findElements(productPrice);

        int expectedTotal = 0;
        for (WebElement el : allProductsPrice) {
            if (el.isDisplayed() && el.getText().contains("MRP $")) {
                String text = el.getText();
                String[] textParts = text.trim().split(" ");
                int pricePerProduct = Integer.parseInt(textParts[2]);
                expectedTotal += pricePerProduct;
            }

        }
        return expectedTotal;


    }

    public int getActualTotalPrice() {
        String totalPrice = getText(actualTotalPrice);
        String price = totalPrice.trim().split("\\$")[1];
        int actualTotal = Integer.parseInt(price);
        return actualTotal;
    }

}

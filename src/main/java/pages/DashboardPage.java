package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class DashboardPage extends BasePage {
    private By cartNavbar = By.cssSelector("button[routerlink='/dashboard/cart']");

    public DashboardPage(AppiumDriver driver) {
        super(driver);
    }

    public void addProductToCart(String product) throws InterruptedException {
        waitForElementVisible(By.xpath("//b[text()='" + product + "']"));
        forceClick(By.xpath("//b[contains(text(),'" + product
                + "')]/parent::h5/following-sibling::button[contains(text(),'Add To Cart')]"));
        waitForElementToDisappear(toast);
        Thread.sleep(2000);
    }

    public void goToCart() {
        openHamburgerMenu();
        safeClick(cartNavbar);
    }

}

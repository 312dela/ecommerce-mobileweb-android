package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class MyOrdersPage extends BasePage {
    public MyOrdersPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickViewOrder(String orderId) {
        forceClick(By.xpath("//th[text()='" + orderId + "']/parent::tr//button[contains(@class,'btn-primary')]"));
    }
}

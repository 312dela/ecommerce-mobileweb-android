package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class LoginPage extends BasePage {

    private By emailField = By.id("userEmail");
    private By passwordField = By.id("userPassword");
    

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillLoginForm(String email, String password) {
        safeType(emailField, email);
        safeType(passwordField, password);
    }
}

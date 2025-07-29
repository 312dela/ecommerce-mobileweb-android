package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.AppiumDriver;

public class RegistrationPage extends BasePage {
    private By registerLauncher = By.cssSelector("a[routerlink='/auth/register']");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By phoneField = By.id("userMobile");
    private By occupationDropdown = By.cssSelector("[formcontrolname='occupation']");
    private By genderFemaleRadio = By.cssSelector("[value='Female']");
    private By passwordField = By.id("userPassword");
    private By confirmPasswordField = By.id("confirmPassword");
    private By requiredCheckbox = By.cssSelector("[formcontrolname='required']");    

    public RegistrationPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillRegistrationForm(String email) throws InterruptedException {
        safeClick(registerLauncher);
        safeType(firstNameField, "John");
        safeType(lastNameField, "Doe");
        safeType(emailField, email);
        safeType(phoneField, "1234567890");

        waitForElementVisible(occupationDropdown);
        Select dropdown = new Select(driver.findElement(occupationDropdown));
        dropdown.selectByValue("3: Engineer");

        safeClick(genderFemaleRadio);
        safeType(passwordField, "Test12345");
        safeType(confirmPasswordField, "Test12345");
        safeClick(requiredCheckbox);
    }

}

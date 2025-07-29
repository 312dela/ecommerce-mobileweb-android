package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import pages.RegistrationPage;
import utils.EmailGenerator;

public class RegistrationSteps {
    private RegistrationPage registrationPage;

    public RegistrationSteps() {
        this.registrationPage = new RegistrationPage(Hooks.driver);
    }

    @When("I fill the registration form with lowercase email")
    public void i_fill_the_registration_form_with_lowerase_email() throws InterruptedException {
        Hooks.scenario.log("This email is used in this test: " + EmailGenerator.emailLower);
        registrationPage.fillRegistrationForm(EmailGenerator.emailLower);
    }

    @When("I fill the registration form with uppercase email")
    public void i_fill_the_registration_form_with_uppercase_email() throws InterruptedException {
        Hooks.scenario.log("This email is used in this test: " + EmailGenerator.emailUpper);
        registrationPage.fillRegistrationForm(EmailGenerator.emailUpper);
    }
}

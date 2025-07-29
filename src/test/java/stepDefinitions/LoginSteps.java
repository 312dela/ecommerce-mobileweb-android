package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import pages.LoginPage;
import utils.JSONDataLoader;

public class LoginSteps {

    private LoginPage loginPage;
    private String password = JSONDataLoader.getLoginPassword();;

    public LoginSteps() {
        this.loginPage = new LoginPage(Hooks.driver);
    }

    @When("I fill the login form with {string}")
    public void i_fill_the_login_form(String email) {
        loginPage.fillLoginForm(email, password);
    }
}

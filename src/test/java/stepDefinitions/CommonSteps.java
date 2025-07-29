package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;

public class CommonSteps {
    private BasePage basePage;

    public CommonSteps() {
        this.basePage = new BasePage(Hooks.driver);
    }

    @Given("I am on the website page")
    public void i_am_on_the_website_page() {
        basePage.goToUrl();
    }

    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
        basePage.clickSubmitButton();
    }

    @Then("I should see the 'successfully' message")
    public void i_should_see_success_message() throws InterruptedException {
        String toastText = basePage.getToastMessage();
        String cleanedText = toastText.trim().replaceAll("\\s+", " ");
        assertTrue(cleanedText.contains("Successfully"),
                "Expected contains 'successfully' message, but got: " + cleanedText);
    }


    @Then("I should see the 'already' message")
    public void i_should_see_already_message() throws InterruptedException {
        String toastText = basePage.getToastMessage();
        String cleanedText = toastText.trim().replaceAll("\\s+", " ");
        assertTrue(cleanedText.contains("already"),
                "Expected contains 'already' message, but got: " + cleanedText);
    }
}
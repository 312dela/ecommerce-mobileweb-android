package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.JavascriptExecutor;

import hooks.Hooks;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pages.BasePage;
import pages.CartPage;
import pages.DashboardPage;
import pages.MyOrdersPage;
import pages.OrderDetailsPage;
import pages.OrderPage;
import pages.ThanksPage;
import utils.AuthAPI;
import utils.JSONDataLoader;
import utils.OrderAPI;

public class OrderSteps {
    private BasePage basePage;
    private DashboardPage dashboardPage;
    private CartPage cartPage;
    private OrderPage orderPage;
    private ThanksPage thanksPage;
    private MyOrdersPage myOrdersPage;
    private OrderDetailsPage orderDetailsPage;

    private String email = JSONDataLoader.getLoginEmail();
    private String otherUserEmail = JSONDataLoader.getOtherUserEmail();
    private String password = JSONDataLoader.getLoginPassword();
    private String product1 = JSONDataLoader.getProduct1();
    private String product2 = JSONDataLoader.getProduct2();
    private String productId = JSONDataLoader.getProductId();
    private String insertLocation = JSONDataLoader.getInsertLocation();
    private String selectLocation = JSONDataLoader.getSelectLocation();

    private String orderId;
    private String token;
    private Response response;

    public OrderSteps() {
        this.basePage = new BasePage(Hooks.driver);
        this.dashboardPage = new DashboardPage(Hooks.driver);
        this.cartPage = new CartPage(Hooks.driver);
        this.orderPage = new OrderPage(Hooks.driver);
        this.thanksPage = new ThanksPage(Hooks.driver);
        this.myOrdersPage = new MyOrdersPage(Hooks.driver);
        this.orderDetailsPage = new OrderDetailsPage(Hooks.driver);
    }

    @Given("I am already logged on the website page")
    public void i_am_already_logged_on_the_website_page() {
        basePage.goToUrl();

        token = AuthAPI.loginAndGetToken(email, password);

        JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;
        js.executeScript("window.localStorage.setItem('token', '" + token + "')");
        Hooks.driver.get("https://rahulshettyacademy.com/client");
    }

    @Given("a different user creates an order")
    public void a_different_user_creates_an_order() {
        String otherUserToken = AuthAPI.loginAndGetToken(otherUserEmail, password);

        orderId = OrderAPI.placeOrder(otherUserToken, productId);
    }

    @When("I add products to the cart")
    public void i_add_products_to_the_cart() throws InterruptedException {
        dashboardPage.addProductToCart(product1);
        dashboardPage.addProductToCart(product2);
    }

    @When("I go to cart page")
    public void i_go_to_the_cart_page() {
        dashboardPage.goToCart();
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        dashboardPage.goToCart();
        cartPage.clickCheckout();
    }

    @When("I input the Country Shipping Info")
    public void i_input_the_country_shipping_info() {
        orderPage.inputCountryShippingInfo(insertLocation, selectLocation);
    }

    @When("I click order button")
    public void i_click_order_button() {
        orderPage.clickOrder();
    }

    @When("I try to view that order using current user")
    public void i_try_to_view_that_order_using_current_user() {
        response = OrderAPI.getOrderDetails(token, orderId);
    }

    @When("I change the shipping email to a different one")
    public void i_change_the_shipping_email_to_a_different_one() {
        orderPage.changeEmailShippingInfo(otherUserEmail);
    }

    @Then("I should see the calculated total price in cart page is correct")
    public void i_should_see_the_calculated_total_price_in_cart_page_is_correct() {
        int expectedTotal = cartPage.calculateTotalPrice();
        int actualTotal = cartPage.getActualTotalPrice();
        assertEquals(actualTotal, expectedTotal,
                "Expected is '" + expectedTotal + "' but got '" + actualTotal + "'");
    }

    @Then("I should see the email billing address is the same as the respective account")
    public void i_should_see_the_email_billing_address_is_the_same_as_the_respective() {
        String orderId = thanksPage.getOrderId();
        thanksPage.goToMyOrders();
        myOrdersPage.clickViewOrder(orderId);

        String emailText = orderDetailsPage.getEmailBillingAddress();
        String cleanedText = emailText.trim();
        assertTrue(cleanedText.contains(email),
                "Email billing address expected contains '" + email + "' message, but got: " + cleanedText);
    }

    @Then("I should see the email label is updated in the form")
    public void i_should_see_the_email_label_is_updated_in_the_form() {
        String emailLabel = orderPage.getShippingEmailLabel();
        String cleanedEmailLabel = emailLabel.trim();
        assertTrue(cleanedEmailLabel.contains(otherUserEmail),
                "Email label expected contains '" + otherUserEmail + "' message, but got: "
                        + cleanedEmailLabel);
    }

    @Then("I should see the email billing address is different from the respective account")
    public void i_should_see_the_email_billing_address_is_different_from_the_respective() {
        String orderId = thanksPage.getOrderId();
        thanksPage.goToMyOrders();
        myOrdersPage.clickViewOrder(orderId);

        String emailText = orderDetailsPage.getEmailBillingAddress();
        String cleanedText = emailText.trim();
        assertTrue(cleanedText.contains(otherUserEmail),
                "Email billing address expected contains '" + otherUserEmail + "' message, but got: "
                        + cleanedText);
    }

    @Then("I should see 'Please Enter Full Shipping Information' message")
    public void i_should_see_please_enter_full_shipping_information_message() throws InterruptedException {
        String toastText = basePage.getToastMessage();
        String cleanedText = toastText.replaceAll("//s+", " ");
        assertTrue(cleanedText.equals("Please Enter Full Shipping Information"),
                "Toast expected to be '" + cleanedText + "' but got: " + toastText);
    }

    @Then("the response should be 403")
    public void the_response_should_be_403() {
        assertEquals(response.statusCode(), 403, "Expected 403 Forbidden when viewing other's order.");
    }
}

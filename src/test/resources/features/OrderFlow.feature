
Feature: Order Flow Validation

    Background:
        Given I am already logged on the website page

    Scenario: Count the total price of added products in cart
        When I add products to the cart
        And I go to cart page

        Then I should see the calculated total price in cart page is correct

    Scenario: Create order with same email as the respective account
        When I add products to the cart
        And I proceed to checkout
        And I input the Country Shipping Info
        And I click order button

        Then I should see the email billing address is the same as the respective account

    Scenario: Create order with different email from the respective account
        When I add products to the cart
        And I proceed to checkout
        And I change the shipping email to a different one

        Then I should see the email label is updated in the form

        When I input the Country Shipping Info
        And I click order button

        Then I should see the email billing address is different from the respective account
    
    Scenario: Create order without shipping location provided
        When I add products to the cart
        And I proceed to checkout
        And I click order button

        Then I should see 'Please Enter Full Shipping Information' message

    Scenario: View order created by different account from the respective account should return 403
        Given a different user creates an order

        When I try to view that order using current user

        Then the response should be 403
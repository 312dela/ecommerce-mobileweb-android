Feature: Registration Flow Validation

    Background:
        Given I am on the website page

    Scenario: Register New Account - Lowercase Email
        When I fill the registration form with lowercase email
        And I click on the submit button
        Then I should see the 'successfully' message

    Scenario: Register Existing Account - Lowercase Email
        When I fill the registration form with lowercase email
        And I click on the submit button
        Then I should see the 'already' message

    Scenario: Register Existing Account - Uppercase Email
        When I fill the registration form with uppercase email
        And I click on the submit button
        Then I should see the 'already' message
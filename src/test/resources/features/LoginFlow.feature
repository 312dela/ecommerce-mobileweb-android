Feature: Login Flow Validation

  Background:
    Given I am on the website page

  Scenario Outline: Successful login with valid inputs
    When I fill the login form with "<email>"
    And I click on the submit button
    Then I should see the 'successfully' message

    Examples:
        | email               |
        | email1@yopmail.com  |
        | Email1@yopmail.com  |
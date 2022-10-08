Feature: Shopping automation
  Scenario: Testing the authentication
    Given I go to the Website
    When I click on the sign in bottom
    And I specify my credentials and click login
    Then I can log into the website

  Scenario: Testing the purchase of two items
    Given I go to the Website
    When I add two elements to the cart
    And I proceed the checkout
    And I confirm address, shipping and final order
    Then The elements are bought

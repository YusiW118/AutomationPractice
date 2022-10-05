Feature: Shopping automation
  Scenario: Testing the authentication
    Given I go to the Website
    When I click on the sign in bottom
    And I specify my credentials and click login
    Then I can log into the website
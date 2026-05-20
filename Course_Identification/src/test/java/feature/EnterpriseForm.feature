Feature: Validate Enterprise Form on Coursera

  Scenario: Display error message for invalid data
    Given user opens Coursera website
    When user navigates to Enterprise page
    And user enters invalid data
    Then error message should be displayed


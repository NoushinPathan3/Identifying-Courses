Feature: Coursera Automation

  Scenario: Search web development courses
    Given user opens Coursera website
    When user searches for "Web Development"
    And user applies Beginner and English filters
    Then display first two course details

  Scenario: Extract language learning
    Given user opens Coursera website
    When user navigates to Language Learning
    Then display languages and levels

  Scenario: Validate enterprise form
    Given user opens Coursera website
    When user navigates to Enterprise page
    And user enters invalid data
    Then error message should be displayed
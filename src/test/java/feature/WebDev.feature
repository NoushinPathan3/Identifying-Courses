Feature: Identify Courses
  Scenario: Search web development courses
    Given user opens Coursera website
    When user searches for "Web Development"
    And user applies Beginner and English filters
    Then display first two course details

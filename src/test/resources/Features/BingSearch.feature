Feature: Bing search

  @test
  Scenario: user can search any keyword
    Given an open browser with bing.com
    When a keyword selenium is entered in input field
    Then the first one should contain "Selenium"
      And close browser
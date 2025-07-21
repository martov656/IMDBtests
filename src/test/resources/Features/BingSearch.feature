Feature: Bing search

  @test
  Scenario: user can search any keyword
    Given an open browser with bing.com
    When a keyword Reese Witherspoon is entered in input field
    Then user should see results from browser search
    Then close browser


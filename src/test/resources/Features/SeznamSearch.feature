Feature: Seznam search

  @test2
  Scenario: user can search any keyword
    Given an open browser with
    When a keyword Jennifer A. is entered in input field
    Then user should see results from search
    Then close search browser

  @test3
  Scenario: user can search any keyword
    Given an open browser with google.cz
    When a keyword Scarlett Johansson is entered in input field
    Then user should see results from google search
    Then close google browser

  @test4
  Scenario: user can search any keyword
    Given an open browser with bing.com
    When a keyword Reese Witherspoon is entered in input field
    Then user should see results from browser search
    Then close browser

  @test5
  Scenario: user can search any keyword
    Given an open browser with booking for search
    When a keyword Victoria is entered in input field
    Then user should see Victoria results from search
    Then close booking browser

  @test6
  Scenario: user can search any keyword
    Given an open browser for searching word Lindsey
    When a keyword Lindsey is entered in input field
    Then user should see Lindsey results from search
    Then close browser word Lindsey

Feature: Seznam search

  @test2
  Scenario: user can search any keyword
    Given an open browser with seznam.cz
    When a keyword Jennifer Aniston is entered in input field
    Then user should see results from seznam search
    Then close seznam browser

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


 Feature: IMDb celebrity search with multiple names

  @multiple
  Scenario: User searches for multiple celebrities on IMDb
    Given the user opens the IMDb homepage
    When the user searches for the following celebrities:
      | Reese Witherspoon  |
      | Sandra Bullock     |
      | Scarlett Johansson |
      | Tom Cruise         |
      | Brad Pitt          |
      | Ridley Scott       |
      | Steven Spielberg   |
      | Victoria Silvstedt |
      | Karen Black        |
    Then the browser is closed


Feature: Demo Feature File

  @test
  Scenario: Demo cucumber scenario
    Given This is given step
    When This is when step
    Then This is then step

  @test
  Scenario: Demo calculator test
    Given We set x = 2
    When We add 3 to x
    Then We got 5

  @test
  Scenario Outline: Demo calculator test with examples
    Given We set x = <x>
    When We add 3 to x
    Then We got <result>

    Examples:
      | x | result |
      | 1 | 4      |
      | 2 | 5      |
      | 3 | 6      |



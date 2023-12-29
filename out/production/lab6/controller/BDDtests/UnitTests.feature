Feature: UntiTest

  Scenario: interface and stack calculator
    Given open calculator
    When push "7"
    When push "+"
    When push "3"
    When push "*"
    When push "2"
    When push "="
    Then app result is 13

  Scenario: interface and zero divide
    Given open calculator
    When push "7"
    When push "/"
    When push "0"
    When push "="
    Then arithmetic error
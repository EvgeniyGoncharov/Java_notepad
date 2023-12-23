Feature: UntiTest

  Scenario: write text
    Given open notepad
    When enter "текст"
    Then app result is "текст"
  Scenario: click copy
    Given open notepad for copy
    When enter "текст"
    When click copy
    Then copy result is "текст"

  Scenario: click paste
    Given open notepad for paste
    When click paste
    Then paste result

  Scenario: open notepad for new file
    Given open notepad for new file
    When enter "текст"
    When click new
    Then result of New file

  Scenario: unit write text
    Given open notepad
    When enter "текст"
    Then app result is "текст"
  Scenario: unit copy
    Given open notepad for copy
    When enter "текст"
    When click copy
    Then copy result is "текст"
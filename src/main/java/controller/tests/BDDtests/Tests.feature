Feature: Tests

  Scenario: write text
    Given open notepad
    When enter "текст"
    Then app result is "текст"

  Scenario: click copy
    Given open notepad for copy
    When enter "текст"
    When click copy
    Then copy result is "текст"

  Scenario: open notepad for new file
    Given open notepad for new file
    When enter "текст"
    When click new
    Then result of new file


Feature: Select specific radio button
  As a user
  I want to select a specific radio button on the web page
  So that the correct option is chosen based on user input

  Scenario Outline: User selects a specific radio button
    Given the user is on the radio button page
    When the user selects the "<radio_option>" option
    Then the "<radio_option>" radio button should be selected

    Examples:
      | radio_option |
      | black        |
      | blue |
      | red        |

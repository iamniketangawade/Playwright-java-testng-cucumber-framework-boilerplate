Feature: Login Feature
    In order to perform successful Login
    As a User
    I Have to enter correct username  and Password

 Scenario: Login to AutomationFramework website with "valid" user
    Given User navigate to website URL
    When user validates the website title
    Then user enters username and password
      | username | password             |
      | practice | SuperSecretPassword! |
    And user clicks on the submit button
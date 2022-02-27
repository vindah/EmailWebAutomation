@email @test @smoke
Feature: Email Login/Send feature
  In order to send an email
  As a registered email user



    # Email Login - Checking page loads properly
  @smoke @acceptance
  Scenario: Verify login page will load properly
    Given The merchant is on the email login page
    Then The Logo should be displayed
    Then The sign in text should be displayed
    Then The Email field should be present and editable
    Then The Next button should be present and clickable
    Then The Forgot email text should be displayed

    # Email Login - Logging in
  @acceptance @regression
  Scenario: Verify user can login successfully
    Given The merchant is on the email login page
    When The user enters their email
    And  The user clicks on the next button
    Then The password page should be displayed
    When The user enters their password
    And The user clicks on the password next button
    Then The users email page should be displayed successfully.


     #Send Email
  @smoke @acceptance
  Scenario: Verify user can navigate to send
    Given The merchant is on the email login page
    When The user enters their email
    And  The user clicks on the next button
    Then The password page should be displayed
    When The user enters their password
    And The user clicks on the password next button
    Then The users email page should be displayed successfully.
    When The user clicks on Compose
    Then The New message modal should be displayed

  #Receiving email and validating
  @acceptance @regression
  Scenario: Verify user can send email and receive
    Given The merchant is on the email login page
    When The user enters their email
    And  The user clicks on the next button
    Then The password page should be displayed
    When The user enters their password
    And The user clicks on the password next button
    Then The users email page should be displayed successfully.
    When The user clicks on Compose
    When The user enters the recipient email
    And The user enters the subject and body
    And The user clicks on send
    Then The email should be sent successfully
    When The user clicks on received email
    Then The body of the email should be visible and correct


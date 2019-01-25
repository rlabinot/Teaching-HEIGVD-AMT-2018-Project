Feature: Testing of the badges

  Background:
    Given there is a Badges server

  Scenario: Create a badge
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    Then I receive a 201 status code for the badges
    When I DELETE a badge
    Then I receive a 202 status code for the badges


  Scenario: Create the same badge twice
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    Then I receive a 201 status code for the badges
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    Then I receive a 409 status code for the badges
    When I DELETE all the badges
    Then I receive a 202 status code for the badges

  Scenario: Get a badge that exist
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    When I GET it from the /badges api with its id
    Then I receive a 200 status code for the badges
    Then I check my badge with the expected one : "test"
    When I DELETE a badge
    Then I receive a 202 status code for the badges

  Scenario: Get a badge that doesn't exist
    When I GET a fake badge from the /badges api
    Then I receive a 404 status code for the badges

  Scenario: I POST many badges and find them
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    Given I have a badge named "test1"
    When I POST it to the /badges endpoint
    Then I GET all my badges
    Then I check if my badges are GET : "test" and "test1"
    Then I receive a 200 status code for the badges
    When I DELETE all the badges
    Then I receive a 202 status code for the badges

  Scenario: I PUT a badge that exist
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    When I PUT a new name to this badge as "test1"
    When I GET it from the /badges api with its id
    Then I receive a 200 status code for the badges
    Then I check my badge with the expected one : "test1"
    When I DELETE a badge
    Then I receive a 202 status code for the badges

  Scenario: I DELETE a badge
    Given I have a badge named "test1"
    Given I POST it to the /badges endpoint
    When I DELETE a badge
    Then I receive a 202 status code for the badges

  Scenario: I DELETE all the badges
    Given I have at least one badge in the database
    When I DELETE all the badges
    Then I receive a 202 status code for the badges
    Then There is no badges
    Then I receive a 200 status code for the badges
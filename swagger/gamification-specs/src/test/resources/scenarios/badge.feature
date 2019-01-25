Feature: Creation of fruits

  Background:
    Given there is a Badges server

  Scenario: Create a badge
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    Then I receive a 201 status code


  Scenario: Create the same badge twice
    Given I have a badge named "test"
    When I POST it to the /badges endpoint
    Then I receive a 409 status code

  Scenario: Get a badge that exist
    Given I have a badge named "test1"
    When I POST it to the /badges endpoint
    When I GET it from the /badges api with its id
    Then I receive a 200 status code
    Then I check my badge with the expected one : "test1"

  Scenario: Get a badge that doesn't exist
    When I GET a fake badge from the /badges api
    Then I receive a 404 status code
    
  Scenario: I POST many badges and find them
    Given I have a badge named "test2"
    When I POST it to the /badges endpoint
    Given I have a badge named "test3"
    When I POST it to the /badges endpoint
    Then I GET all my badges
    Then I check if my badges are GET : "test2" and "test3"
    Then I receive a 200 status code

  Scenario: I PUT a badge that exist
    Given I have a badge named "test4"
    When I POST it to the /badges endpoint
    When I PUT a new name to this badge as "test5"
    When I GET it from the /badges api with its id
    Then I receive a 200 status code
    Then I check my badge with the expected one : "test5"

  Scenario: I DELETE a badge
    Given I have a badge named "test6"
    Given I POST it to the /badges endpoint
    When I DELETE it
    Then I receive a 202 status code

  Scenario: I DELETE all the badges
    Given I have something in the database
    When I DELETE everything
    Then I receive a 202 status code
    Then The database is empty
    Then I receive a 200 status code

















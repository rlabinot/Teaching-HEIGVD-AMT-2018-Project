Feature: Testing of the pointScales

  Background:
    Given there is a PointScales server

  Scenario: Create a pointScale
    Given I have a pointScale named "test"
    When I POST it to the /pointScales endpoint
    Then I receive a 201 status code for the pointScales


  Scenario: Create the same pointScale twice
    Given I have a pointScale named "test"
    When I POST it to the /pointScales endpoint
    Then I receive a 409 status code for the pointScales

  Scenario: Get a pointScale that exist
    Given I have a pointScale named "test1"
    When I POST it to the /pointScales endpoint
    When I GET it from the /pointScales api with its id
    Then I receive a 200 status code for the pointScales
    Then I check my pointScale with the expected one : "test1"

  Scenario: Get a pointScale that doesn't exist
    When I GET a fake pointScale from the /pointScales api
    Then I receive a 404 status code for the pointScales

  Scenario: I POST many pointScales and find them
    Given I have a pointScale named "test2"
    When I POST it to the /pointScales endpoint
    Given I have a pointScale named "test3"
    When I POST it to the /pointScales endpoint
    Then I GET all my pointScales
    Then I check if my pointScales are GET : "test2" and "test3"
    Then I receive a 200 status code for the pointScales

  Scenario: I PUT a pointScale that exist
    Given I have a pointScale named "test4"
    When I POST it to the /pointScales endpoint
    When I PUT a new name to this pointScale as "test5"
    When I GET it from the /pointScales api with its id
    Then I receive a 200 status code for the pointScales
    Then I check my pointScale with the expected one : "test5"

  Scenario: I DELETE a pointScale
    Given I have a pointScale named "test6"
    Given I POST it to the /pointScales endpoint
    When I DELETE a pointScale
    Then I receive a 202 status code for the pointScales

  #Scenario: I DELETE all the pointScales
  #  Given I have at least one pointScale in the database
  #  When I DELETE all the pointScales
  #  Then I receive a 202 status code for the pointScales
  #  Then There is no pointScales
  #  Then I receive a 200 status code for the pointScales
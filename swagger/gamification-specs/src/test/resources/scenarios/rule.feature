Feature: Testing of the rules

  Background:
    Given there is a Rules server
    Given there is a Badges server
    Given there is a PointScales server

  Scenario: Create a rule
    Given I have a rule named "test" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    When I POST it to the /rules endpoint
    Then I receive a 201 status code for the rules
    When I DELETE a rule
    Then I receive a 202 status code for the rules


  Scenario: Create the same rule twice
    Given I have a rule named "test" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    When I POST it to the /rules endpoint
    Then I receive a 201 status code for the rules
    Given I have a rule named "test" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    When I POST it to the /rules endpoint
    Then I receive a 201 status code for the rules
    When I DELETE all the rules
    Then I receive a 202 status code for the rules

  Scenario: Get a rule that exist
    Given I have a rule named "test" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    When I POST it to the /rules endpoint
    When I GET it from the /rules api with its id
    Then I receive a 200 status code for the rules
    Then I check my rule with the expected one : "test"
    When I DELETE a rule
    Then I receive a 202 status code for the rules

  Scenario: Get a rule that doesn't exist
    When I GET a fake rule from the /rules api
    Then I receive a 404 status code for the rules

  Scenario: I POST many rules and find them
    Given I have a rule named "test" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    When I POST it to the /rules endpoint
    Given I have a rule named "test1" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    When I POST it to the /rules endpoint
    Then I GET all my rules
    Then I check if my rules are GET : "test" and "test1"
    Then I receive a 200 status code for the rules
    When I DELETE all the rules
    Then I receive a 202 status code for the rules

  Scenario: I PUT a rule that exist
    Given I have a rule named "test" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    When I POST it to the /rules endpoint
    When I PUT a new name to this rule as "test1"
    When I GET it from the /rules api with its id
    Then I receive a 200 status code for the rules
    Then I check my rule with the expected one : "test1"
    When I DELETE a rule
    Then I receive a 202 status code for the rules

  Scenario: I DELETE a rule
    Given I have a rule named "test" where the trigger is "test" and the amount is 0 the badge id is 1 the pointScale is 1
    Given I POST it to the /rules endpoint
    When I DELETE a rule
    Then I receive a 202 status code for the rules

  Scenario: I DELETE all the rules
    Given I have at least one rule in the database
    When I DELETE all the rules
    Then I receive a 202 status code for the rules
    Then There is no rules
    Then I receive a 200 status code for the rules
    
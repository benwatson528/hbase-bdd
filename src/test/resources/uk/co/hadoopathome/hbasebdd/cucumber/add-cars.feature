Feature: Adding cars to HBase

  Scenario: Add a new car to HBase
    Given a car to be added with registration "AB56 XYZ", model "Vauxhall Vectra" and productionYear 2016
    When the car is added
    Then the car is available in HBase

  Scenario: Add an old car to HBase
    Given a car to be added with registration "OLD 1", model "Rover" and productionYear 1970
    When the car is added
    Then the car is available in HBase

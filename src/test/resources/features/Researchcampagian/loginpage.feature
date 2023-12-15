Feature:Research campaign

  Background:
    Given I am on the ORO login page

  Scenario: Search with PO number
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully
    Then click on customers
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then I have entered PO number
    Then click on search
    Then expand the result
    Then verify that bill of lading is getting displayed

  Scenario:Advanced search with customer id

    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then click on Advanced search
    Then I have entered customer id
    And enter the date into the calendar
    And click on search on advanced search
    Then expand the result
    Then verify that bill of lading under shipment is getting displayed

  Scenario:Advanced search with customer name

    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then click on Advanced search
    Then I have entered customername
    And enter the date into the calendar
    And click on search on advanced search
    Then expand the result
    Then verify that bill of lading under shipment is getting displayed


  Scenario: Search with invoice number
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then I have entered Invoice number
    Then click on search
    Then expand the result
    Then verify that invoice number is getting displayed

   Scenario: Search with shipment number
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then I have entered shipment number
    Then click on search
    Then expand the result
    Then verify that shipment number is getting displayed

  Scenario: Search with Order number
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then I have entered Order number
    Then click on search
    Then expand the result
    Then verify that Order number is getting displayed

 Scenario: Search with tracking number
    Given I have entered a valid username and password
   When I click on the login button
    Then I should be logged in successfully
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then I have entered tracking number
    Then click on search
#   Then expand the shipment column
    Then expand the result
    Then verify that tracking number is getting displayed


  Scenario: Clear search
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully
    When I click on the Customer 360 link
    Then I click on Research campaign
    Then I have entered PO number
    Then I have entered Invoice number
    Then click on clear search button
    Then verify that all the fields get reset


@Regression @AllTest
Feature: Placing an Order on the Swaglab Website

Background:
  Given I landed on Swaglab Website

@Regression @AllTest
Scenario Outline: Successfully Placing an Order
  Given Logged in with username <username> and password <password>
  When I add the selected product <product> to the cart
  And I view the product in the cart
  And I proceed to checkout
  And I provided firstname <firstname>, lastname <lastname>, and zip <zip> as shipping details
  And I confirm the order
  Then success message is displayed on the confirmation page

Examples:
  | username        | password      | product              | firstname | lastname | zip   |
  | standard_user   | secret_sauce  | Sauce Labs Backpack  | Abcde     | fghij    | 12345 |

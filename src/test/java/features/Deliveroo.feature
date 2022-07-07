Feature: User can add items to his Basket without registration
  In order to test Deliveroo add to cart feature
  As an unregistered user
  I want to proceed with creating order flow until reach the add to cart step

  @regression
  Scenario Outline: Unregistered user Can search for restaurants after adding his address
    Given The User in the Deliveroo home page "<URL>"
    When The User entered his "<Address>"
    And The User select the first suggestion address
    And The user redirected into restaurants page
    And The User enter the "<Restaurant>" in the search input
    And The User click on the first restaurant from the results
    Then User redirected into the "<Restaurant>" page

    Examples:
      | URL                      | Address | Restaurant |
      | https://deliveroo.co.uk/ | London  | KFC        |

  @regression
  Scenario Outline: Unregistered user get a free delivery when his basket reach the minimum cost for the free delivery items
    Given The User in the Deliveroo home page "<URL>"
    When The User entered his "<Address>"
    And The User select the first suggestion address
    And The user redirected into restaurants page
    And The User select the Free delivery from side menu filter
    And The User select the first restaurant in the free delivery results
    And The user redirected to restaurant menu page
    And The User select the first item in the restaurant
    And The User select his options and add the item into the Basket
    And The increase the order item counter until reach the "<minimum_free_delivery_cost>"
    Then The user get the free delivery fee

    Examples:
      | URL                      | Address | minimum_free_delivery_cost |
      | https://deliveroo.co.uk/ | London  | 15                         |
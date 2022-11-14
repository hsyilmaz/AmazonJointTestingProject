Feature:Home & Kitchen Department

  Background:
    Given Navigate to basqar
@yilmaz
  Scenario:
    When Send option as "Home & Kitchen" through text box
    And Click on search button
    Then Results page should appear

    When Click on the following elements
    |homeAndKitchen|
    |furniture|
    |homeOfficeFurniture|
    |officeChairs|
    |deskChairs|

    And Click on the Executive Chair and adding to cart

    Then The item should be added to the cart

    When Click on the following elements
    |goToCart|
    |quantity|
    |delete|

    Then The cart should be empty


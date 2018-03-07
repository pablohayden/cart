# Shopping Cart Application

A small shopping cart application that accepts a number of shopping cart items and calculates totals.
Discounts can be applied to products in the form of rules.

Processing requirements

The goods that can be purchased, which are all priced in GBP, are:
- Soup – 65p per tin
- Bread – 80p per loaf
- Milk – £1.30 per bottle
- Apples – £1.00 per bag

Current special offers are:
- Apples have 10% off their normal price this week
- Buy 2 tins of soup and get a loaf of bread for half price


## Code Example

Application can be launched using...

org.shopping.cart.ShoppingCartApplication BasketName Apples Milk Bread


The program should accept a list of items in the basket and output the subtotal, the special offer discounts and the final
price.

Input should be via the command line in the form PriceBasket item1 item2 item3 ...

For example: PriceBasket Apples Milk Bread

Output should be to the console, for example:
Subtotal: £3.10
Apples 10% off: -10p
Total: £3.00
If no special offers are applicable, the code should output:
Subtotal: £1.30
(no offers available)
Total: £1.30



## Motivation

Project was implemented using Java 8 and uses the spring boot framework with JPA/Hibernate and a H2 database.
The H2 console is registered at http://localhost:8080/console and tables are automatically generated using 
Spring Boot is unneccessary to fill the brief but is included to demonstrate database prototyping techniques and to potentially expose a REST API.


## Tests

A test suite has been created that will run core tests to verify the currently installed discount rules and processing against multiple rules.

Tests intentionally do not use the in memory database as this is not recommended and instead use stubbed data.


## Code Coverage

Code coverage report has been included as part of the maven test goal and will generate a Jacoco code coverage report which is output to target/site/jacoco


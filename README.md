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
The H2 console is registered at http://localhost:8080/console which can be accessed to see the tables and underlying generated data schema & data.
Entity tables are automatically generated using JPA/Hibernate create/drop table option during start up.

It uses the Streaming API feature introduced in Java8 to demonstrate the collection filtering, grouping and performing anonymous functionally actions on a collection.
In most cases streaming is not going to perform as well as using standard iterators/forloops and this would need to be considered for a production release.

Note on Patterns - Factory/Command/Strategy pattern mix that allows us to apply different discount rules sets RuleCalculator to a shopping cart and to generate different RuleCalculator using the Factory method.

Note on the use of Spring Boot - using springboot was unnecessary to fill the brief but using it means that we can quickly prototype DB integration and expose a RESTFUL web service in a very short space of time.

## Tests

A test suite has been created that will run core tests to verify the currently installed discount rules and processing against multiple rules.

Tests intentionally do not use the in memory database as this is not recommended and instead use stubbed data.


## Code Coverage

Code coverage report has been included as part of the maven test goal and will generate a Jacoco code coverage report which is output to target/site/jacoco


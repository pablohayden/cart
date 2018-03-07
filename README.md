# Shopping Cart Micro Service Application

A small shopping cart application that accept exposes a REST API.


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

[org.shopping.cart.ShoppingCartApplication](https://github.com/pablohayden/cart/blob/master/src/main/java/org/shopping/cart/ShoppingCartApplication.java)  BasketName Apples Milk Bread


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

Project was implemented using Java 8 and uses the Spring Boot and is tested using pure JUnit.

1. Note on Streaming API - project the Streaming API feature introduced in Java8 to demonstrate the collection filtering, grouping and performing anonymous functional actions on a collection. Incredible powerful feature but in most cases streaming is not going to perform as well as using standard iterators/for-loops and this would need to be considered for a production release. Also readability to some may be reduced.

2. Note on the use of Spring Boot - using Spring Boot was unnecessary to fill the brief but using it means that we can quickly prototype DB integration and expose a RESTFUL web service in a very short space of time which makes this application far more useful.
- Spring Boot initilizes the JPA using annotations.
- Initializes all components by scanning the current classpath using the @SpringBootApplication annotation.
  This is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes.
- Spring Annotations are preferred in this case as it's a relatively small application and DAO file structure helps to identify relevant spring stereotypes & JPA repositories.
- Spring Boot has a convenience utility that loads a default properties file from the class-path application.properities found [here](https://github.com/pablohayden/cart/tree/master/src/main/resources).
  Properties that have traditionally been maintained in separate file (persistence.xml and hibernate.cfg.xml are all now configured within the one place.
  Logging & tomcat properties can also be configured within [here](https://github.com/pablohayden/cart/tree/master/src/main/resources).
- CartCommandLineRunner is a Spring component interface that spring will invoke at run time and basically wraps the main method of the application. It's a convenience method that supports multiple runtime instances. Found [here](https://github.com/pablohayden/cart/tree/master/src/main/java/org/shopping/cart)

3. Note on H2 DB - The in memory database is used to prototype database integration. The H2 console is registered at http://localhost:8080/console which can be accessed to see the tables and underlying generated data schema & data.
Entity tables are automatically generated using JPA/Hibernate create/drop table option during start up.
Spring Boot automatically detects a number of file types from the classpath including data.sql, which contains an SQL data file to generate the product types found [here](https://github.com/pablohayden/cart/tree/master/src/main/resources).

4. Note on Patterns - Factory/Command/Strategy pattern mix that allows us to apply different discount rules sets RuleCalculator to a shopping cart and to generate different RuleCalculator using the Factory method. Using this pattern improves our ability to introduce new discount rules, extend the code base, to isolation test discount rules and promote the single responsibility principle. 



## Tests

A test suite has been created that will run core tests to verify the currently installed discount rules and processing against multiple rules.

Tests intentionally do not use the in memory database as this is not recommended and instead use stubbed data.

Additional unit test should be added to test services layer at a later date using a mocking framework such as Mockito.


## Code Coverage

Code coverage report has been included as part of the maven test goal and will generate a Jacoco code coverage report which is output to target/site/jacoco


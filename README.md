Spring Boot MySQL Customer Review: access JPA, MVC, MySQL, Resource
=================================================================================
Author: Jorge Whittembury
Level: Intermediate
Technologies: Spring Boot: REST, JPA, Hibernate, Resource), Embedded Apache Tomcat, MySQL)
Summary: REST API Application to manage Users, Products and Customer Reviews on Products. 
Target Product: Spring Boot
Source: <>

What is it?
-----------

The application implements a RESTful API for managing users, products and customer reviews. 
A custom JPA access repository is created for customer reviews, extending spring CRUD repository and allowing us to add a customer review only if there is no curse word on comments and the rating is > 0.
Application uses MySQL database and a SQL initializer script is provided on resources: import.sql, that get loaded when application starts. This script will create the tables User, Product and CustomerReview and will add some initial data.
Curse words are read from a resource file curseWords.txt
Spring auto wired mechanism creates a bean for the custom repository so it can be used anywhere within the application.
Response is rendered with a customized JSON object.
Default error attributes are modified to add a data object: see CustomeErrorResponse

System requirements
-------------------

- A MySQL running instance on port 3306 and a the creation on it of a database "customerreviewproduct". The provided SQL script will create the tables
- You need Maven or Gradle installed on your machine. Shell and batch scripts are provided, as well as POM and build.gradle files.

Build and Run 
-------------

1. For Maven build and run, go to the root directory of the application an type:

        mvnw spring-boot:run

2. For Gradle build, go to the root directory of the application and type:

        gradlew bootRun
 

Access the application 
----------------------

The following API actions are available:
1. http://localhost:8080/api/ -> GET welcome message
2. http://localhost:8080/api/addUser -> POST JSON request
3. http://localhost:8080/api/getAllUsers -> GET request
4. http://localhost:8080/api/updateUser/nnn -> PUT JSON request (nnn = userId). 
5. http://localhost:8080/api/deleteUser/nnn -> Delete request (nnn = userId)
6. http://localhost:8080/api/addProduct -> POST JSON request
7. http://localhost:8080/api/getAllProducts -> GET request
8. http://localhost:8080/api/updateProduct/nnn -> PUT JSON request (nnn = productId). 
9. http://localhost:8080/api/deleteProduct/nnn -> Delete request (nnn = productId)
10. http://localhost:8080/api/addCustomerReview -> POST JSON request
11. http://localhost:8080/api/getAllCustomerReviews -> GET request
12. http://localhost:8080/api/updateCustomerReview/nnn -> PUT JSON request (nnn = customerReviewId). 
13. http://localhost:8080/api/deleteCustomerReview/nnn -> Delete request (nnn = customerReviewId)
14. http://localhost:8080/api/totalProductCustomerReviewsInRange?productId=1&fromRating=0.0&toRating=3.0 -> GET JSON request 
15. http://localhost:8080/api/getProductCustomerReviewsInRange?productId=1&fromRating=0.0&toRating=3.0 -> GET JSON

Validation Rules 
----------------
- Do not allow curse words on the comments
- Do not allow rating < 0
- Comments < 255
- Descriptions < 2048
- and others: See User.java; Product.java and CustomerReview.java


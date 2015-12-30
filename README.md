#Water Bill Management
This application is for managing water bill by a Municipal authority.

##Technology Stack
- Spring Boot, no-xml Spring MVC 4 web application for Servlet 3.0 environment
- Spring Data JPA
- Database (MySQL)  
- Thymeleaf templates
- Testing (JUnit/Mockito/MockMVC/AssertJ/Hamcrest)  
- Java 7

##Local Deployment
local deployment need prior MySQL setup with the property definition. Run with maven

```
$ mvn clean install  
$ mvn spring-boot:run
```

Navigate to [http://localhost:8080/wbm](http://localhost:8080/wbm).  

The application can also be deployed by running the `Application.java` class.

### Author
[Ningthoujam Lokhendro](http://www.ningzeta.com)

# Sale Management Project
## Description
This project is a simple sale management system. It allows users to manage products, customers, and sales. The system has the following features:
### Frameworks and Libraries
- **Framework**: Spring Boot
- **Dependencies**
  - Spring Data JPA
  - Thymeleaf
  - Spring Web
  - Spring Boot DevTools
  - MySQL Connector/J
- **Libraries**: Lombok

### Connect MySQL Database
Go to application.properties file and add the following properties:
```
spring.application.name=SaleManagement
spring.datasource.url=jdbc:mysql://localhost:3306/salemanagement
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

// Hibernate properties use to update the database schema(add,edit,delete column) but don't drop the table 
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update`
```
This project will run with localhost:8080

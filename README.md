# Doctor-Paitent-Repository
This is a RESTful API for a doctor-patient management system using Spring Boot, JPA Repository, and MySQL database. The API allows doctors to create, update, delete, and view patients' records, as well as schedule appointments and send reminders. The API also allows patients to view their own records, book appointments, and cancel them. The API follows the CRUD (Create, Read, Update, Delete) operations and uses JSON as the data format.

The following are the main features of the API:

- Authentication and authorization using JWT (JSON Web Token) and Spring Security.
- Exception handling and validation using custom exceptions and annotations.
- Pagination and sorting using Spring Data JPA.
- Testing using JUnit, Mockito, and Postman.
- Documentation using Swagger UI.

To run the API, you need to have the following prerequisites:

- Java 8 or higher
- Maven
- MySQL
- Postman (optional)

The steps to run the API are:

1. Clone the repository from GitHub using the command `git clone https://github.com/Rishabh-Kumar01/Doctor-Paitent-Repository.git`.
2. Open the project in your preferred IDE and update the application.properties file with your MySQL database credentials and other configurations.
3. Run the command `mvn clean install` to build the project and run the tests.
4. Run the command `mvn spring-boot:run` to start the application.
5. Open your browser and go to `http://localhost:8080/swagger-ui.html` to see the API documentation and test the endpoints.
6. Alternatively, you can use Postman to test the API by importing the collection file from the postman folder in the project.

For more details on how to use the API, please refer to the wiki page on GitHub.

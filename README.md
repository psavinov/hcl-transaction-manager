# HCL Transaction Manager

### How to run:
On Unix-like systems: *./mvnw spring-boot:run*<br>
On Window: *mvnw.cmd spring-boot:run*<br>
<br>
See username and password in application.properties.
<br><br>
Swagger is available on <a href="http://localhost:8080/swagger-ui.html">*http://localhost:8080/swagger-ui.html*</a>
<br>

#### Usage:

See *application.properties* for the username and password.

You can access controllers directly in your browser, like this:

http://localhost:8080/transaction?page=PAGE&size=SIZE
http://localhost:8080/transaction?page=PAGE&size=SIZE&type=TYPE
http://localhost:8080/transactions-amount?type=TYPE

Where:

PAGE = page of transactions list to display, starting from 0

SIZE = size of current page, starting from 1

TYPE = type of transaction to filter by


Or, you can use Curl(or any other client, like Postman, etc.) to access the controllers.


#### Tests:
On Unix-like systems: *./mvnw test*<br>
On Window: *mvnw.cmd test*<br>

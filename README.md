## About The Project

This project exposes REST services to enable various read operations to an Oracle DB hosted on AWS.

All REST endpoints are secured using basic URL authentication.

## Built With

The project is built with the following:

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Apache Maven 3.6.1](https://maven.apache.org/)
* [IntelliJ](https://www.jetbrains.com/idea/)

The project is tested with :

* [JUnit](https://junit.org/junit4/)
* [Rest-assured](https://rest-assured.io/)
* [Postman](https://www.postman.com/)


## Local Install

1. Extract the zip file employee-manager-master.zip

2. Navigate to the project directory:
```sh
cd employee-manager-master
```

3. Update application.properties

Update src/main/java/application.properties and src/test/java/application.properties to contain the connection credentials which should be provided separately.

```sh
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

4. Using Maven, to start the Spring Boot application and enable the REST services, from the directory employee-manager-master run :
```sh
mvn spring-boot:run
```

### IDE Setup

1. Open the project using an IDE (tested with IntelliJ).

2. To download the maven dependencies right click on the project and select Maven -> Reload project:

![Screenshot](https://raw.githubusercontent.com/aronayne/public/master/reload.png)

3. To run the project run the Spring Boot main class located at /src/main/java/EmployeeManagerApplication.java

## Testing

To run all tests execute the following command from employee-manager-master directory:

```sh
mvn test
```

### Testing with Postman and Firefox

In addition, the REST services have been tested using the Firefox browser and Postman.

#### Testing with Postman

It is required to send the authorisation parameter values for each request.
On the authorisation tab set the username/password field values to user/pass:

![Screenshot](https://raw.githubusercontent.com/aronayne/public/master/postman-auth.png)

and click "Send":

![Screenshot](https://raw.githubusercontent.com/aronayne/public/master/cr2.png)

#### Testing with Firefox

Invoking a REST service will prompt a login popup, set the username/password field values to user/pass and press "OK":

![Screenshot](https://raw.githubusercontent.com/aronayne/public/master/ffpass.png)

## REST requests

The REST service requests available from a local String Boot running instance are:

### Return all system departments -  GET request
```sh
http://localhost:8080/department
```

### Return all system badges -  GET request
```sh
http://localhost:8080/badges
```

### Return all active badges -  GET request
```sh
http://localhost:8080/badges/active
```

### Return badges that match the badge_number parameter -  GET request

```sh
http://localhost:8080/badges?badge_number=badge_number
```

### Return all system job titles -  GET request
```sh
http://localhost:8080/job_titles
```

### Return system job titles that match the department_name path variable -  GET request

```sh
http://localhost:8080/job_titles/department_name
```

### Return all system employees -  GET request
```sh
http://localhost:8080/employees
```

### Return all active system employees -  GET request
```sh
http://localhost:8080/employees/active
```

### Return all system employees that match the department_name parameter -  GET request
```sh
http://localhost:8080/employees?department_name=department_name
```

Swagger is enabled for this project and a listing of the available REST services is available at:

![Screenshot](https://raw.githubusercontent.com/aronayne/public/master/swag.png)

To view the available services point Firefox to the Swagger URL:
```sh
http://localhost:8080/swagger-ui/
```

A login popup will display prior to Swagger loading, set the username/password field values to user/pass and press "OK":

![Screenshot](https://raw.githubusercontent.com/aronayne/public/master/ffpass.png)

## Troubleshooting

For any Maven related issues deleting and re-downloading the project dependencies usually resolves. Re-downloading all dependencies can take some time. For unix based systems to delete the Maven repository navigate to Maven home and execute command:

```sh
rm -R repository/
```

By default Maven home is usually set to /<USER_HOME>/.m2

## Scope for improvement

The project can be improved in the following areas:

* A Spring JPA object model can be mapped to reflect a DB schema. The mapping enables the use of cross table JPA queries to extract DB data and omits writing native queries for cross-table joins. An improvement to this project is instead of mapping each table to a single entity, use JPA to model the relationship's between tables.

* Instead of using an in memory data structure to store the code -> country mappings, cache the mappings.

* Extend the test coverage to ensure the JSON response data structure from each service is correct. For example, the below test checks that the "Software" value is mapped to the "department_name" key at index 0 in the response to the "/job_titles" REST service:

```sh
given().auth()
.basic("user", "pass").get("/job_titles").then()
.statusCode(200)
.body("size()", is(12))
.body("[0].department_name", equalTo("Software"));
```

* Extend the mocking of services to test invalid http status 404 returns, for example, apply below test logic to all REST services tests where applicable:

```sh
when(badgeService.getActiveBadges()).thenReturn(Collections.emptyList());

given().when().auth()
        .basic("user", "pass").get("/badges/active").then()
        .statusCode(HttpStatus.NOT_FOUND.value());
```

* Extend the test coverage to verify the custom exception CountryCodeServiceException is thrown when expected.

## Notes

* Country codes that do not contain a mapping for the REST service: https://restcountries.eu are set to "NA". For example, the country code "irl" is not available from https://restcountries.eu, the request  https://restcountries.eu/rest/v2/name/irl responds with 404 (not found).

* Interpreting the requirements doc I assume /:department_name is /department_name and is a URL path variable. For example, to view all job titles from the HR department use:

```sh
http://localhost:8080/job_titles/HR
```

* I assume the request parameters badge_number & department_name for the requests ""/badges?badge_number=[badge_number]" and "/employees?department_name=[department_name]" match on single value and not a list of values.

* For testing I assume the DB state is immutable, therefore the REST service's response JSON is integration tested.

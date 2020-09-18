package com.integration;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void employeesBaseEndpointTest() {
        given().auth()
                .basic("user", "pass").get("/employees").then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(20));

    }

    @Test
    public void departmentNameValidTest() {
        given().auth()
                .basic("user", "pass").get("/employees?department_name=HR").then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));

    }

    @Test
    public void activeEmployeesTest() {
        given().auth()
                .basic("user", "pass").get("/employees/active").then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(20));

    }

    @Test
    public void deptNameNotValidTest() {
        given().auth()
                .basic("user", "pass").get("/employees?department_name=HRtest").then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

    }

    @Test
    public void queryParamIncorrectTest() {
        given().auth()
                .basic("user", "pass").get("/employees?department_names=HR").then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

    }

}

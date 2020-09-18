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
public class JobTitleIntegrationTests {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void jobTitlesBaseEndpointTest() {
        given().auth()
                .basic("user", "pass").get("/job_titles").then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(12));

    }

    @Test
    public void jobTitlesByDepartmentNameTest() {
        given().auth()
                .basic("user", "pass").get("/job_titles/Software").then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(3));

    }

    @Test
    public void noJobTitlesInDeptTest() {
        given().auth()
                .basic("user", "pass").get("/job_titles/Test").then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

    }


}

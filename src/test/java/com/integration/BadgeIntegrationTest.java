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
public class BadgeIntegrationTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void badgesBaseEndpointTest() {
        given().auth()
                .basic("user", "pass").get("/badges").then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void activeBadgesTest() {

        given().when().auth()
                .basic("user", "pass").get("/badges/active").then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(18));

    }

    @Test
    public void queryBadgeParamNotCorrectTest() {
        given().auth()
                .basic("user", "pass").get("/badges?badgenumber=1111").then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void queryBadgeValueNotNumericTest() {
        given().auth()
                .basic("user", "pass").get("/badges?badgenumber=test").then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void badgeValidNumberTest() {
        given().auth()
                .basic("user", "pass").get("/badges?badge_number=101").then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(1));
    }

    @Test
    public void noBadgeMatchesTest() {
        given().auth()
                .basic("user", "pass").get("/badges?badge_number=1111").then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

}

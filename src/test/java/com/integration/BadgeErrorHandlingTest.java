package com.integration;

import com.service.BadgeService;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BadgeErrorHandlingTest {

    @LocalServerPort
    int port;
    @MockBean
    private BadgeService badgeService;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    /**
     * Test if no active badges are available then a http status code 404 is returned.
     */
    @Test
    public void notFoundWhenActiveBadgesEmptyTest() {

        when(badgeService.getActiveBadges()).thenReturn(Collections.emptyList());

        given().when().auth()
                .basic("user", "pass").get("/badges/active").then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }


}

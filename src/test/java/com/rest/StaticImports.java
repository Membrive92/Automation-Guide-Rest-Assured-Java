package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StaticImports {

    @Test
    public void simple_test_case() {
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-64ce22b475796174cd93dfba-d5f9b4d1f6afbf63ba8f32413e79e7176a").
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}

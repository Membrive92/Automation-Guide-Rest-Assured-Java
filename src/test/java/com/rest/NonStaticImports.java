package com.rest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonStaticImports {

    @Test
    public void simple_test_case(){
        RestAssured.given().
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

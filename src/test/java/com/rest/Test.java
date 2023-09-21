package com.rest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matcher.*;

public class Test {

    @org.testng.annotations.Test
    public void test(){
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

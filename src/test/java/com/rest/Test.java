package com.rest;

import static com.rest.utils.utils.postmanApiKey;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matcher.*;

public class Test {
    @org.testng.annotations.Test
    public void test(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", postmanApiKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}

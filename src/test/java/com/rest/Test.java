package com.rest;

import static com.rest.Utils.Utils.postmanApiKey;
import static io.restassured.RestAssured.*;

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

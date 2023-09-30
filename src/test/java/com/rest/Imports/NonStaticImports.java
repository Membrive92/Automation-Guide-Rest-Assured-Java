package com.rest.Imports;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static com.rest.Utils.Utils.postmanApiKey;

public class NonStaticImports {

    @Test
    public void simple_test_case(){
        RestAssured.given().
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

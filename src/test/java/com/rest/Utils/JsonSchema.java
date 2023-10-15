package com.rest.Utils;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchema {

    @Test
    public void validateJsonSchema(){
        given().
                baseUri("https://postman-echo.com").
                log().all().
        when()
                .get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("EchoGet.json"));
    }
}

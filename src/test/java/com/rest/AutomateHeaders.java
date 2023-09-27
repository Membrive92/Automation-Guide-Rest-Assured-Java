package com.rest;

import io.restassured.http.Header;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AutomateHeaders {
    @Test
    public void multiple_headers() {
        Header header = new Header("header", "value1");
        Header matchHeader = new Header("x-mock-match-request-headers", "header");

        given().
                baseUri("https://b9f7a298-1b18-4062-b3ce-6c08731ac0bd.mock.pstmn.io").
                header(header).
                header(matchHeader).
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}

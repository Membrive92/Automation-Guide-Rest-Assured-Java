package com.rest.Specifications;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestFilter {

    @Test
    public void loggingFilter() throws FileNotFoundException {
        given().
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter(LogDetail.BODY)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS)).
        when().
                get("/get").
        then().
                assertThat().
                statusCode(200);
    }
}

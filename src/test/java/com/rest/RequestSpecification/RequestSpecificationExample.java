package com.rest.RequestSpecification;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static com.rest.Utils.Utils.postmanApiKey;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class RequestSpecificationExample {
   RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = with().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey());
    }

    @Test
    public void validate_status_code() {
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_response_body() {
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[0].name"), equalTo("Team Workspace"));
    }

}

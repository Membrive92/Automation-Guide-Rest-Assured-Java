package com.rest.RequestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
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
     /*   requestSpecification = with().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey());*/

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key", postmanApiKey());
        requestSpecBuilder.log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public void validate_status_code() {
        Response response = given(requestSpecification).
                header("testHeader", "testValue").
                get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_response_body() {
        Response response = given(requestSpecification).get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[0].name"), equalTo("Team Workspace"));
    }

}

package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

import static com.rest.utils.utils.postmanApiKey;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AutomateGet {

    @Test
    public void validate_status_code(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

   @Test
    public void validate_response_body(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key",  postmanApiKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("Team Workspace", "Rest-assured course", "GPM"),
                        "workspaces.type", hasItems("team", "personal", "team"),
                        "workspaces[0].name", equalTo("Team Workspace"),
                        "workspaces[0].name", is(equalTo("Team Workspace")),
                        "workspaces.size()", equalTo(3),
                        "workspaces.name", hasItem("GPM")
                );
    }

    @Test
    public void extract_response(){
        Response savedResponse = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();
        System.out.println("response = " + savedResponse.asString());
    }

    @Test
    public void extract_single_value_from_response(){
        Response savedResponse = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey()).
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();
        JsonPath jsonPath = new JsonPath(savedResponse.asString());
        System.out.println("workspace name = " + jsonPath.getString("workspaces[0].name"));
      //    System.out.println("workspace name = " + savedResponse.path("workspaces[0].name"));
    }
}

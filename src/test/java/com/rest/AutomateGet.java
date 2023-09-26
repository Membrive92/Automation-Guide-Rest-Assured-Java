package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.rest.utils.utils.postmanApiKey;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
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
        String name = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey()).
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[0].name");

            System.out.println("workspace name = " + name);
        // System.out.println("workspace name = " + JsonPath.from(savedResponse).getString("workspaces[0].name"));
       // System.out.println("workspace name = " + jsonPath.getString("workspaces[0].name"));
      //    System.out.println("workspace name = " + savedResponse.path("workspaces[0].name"));
    }

    @Test
    public void hamcrest_assert_on_extracted_response(){
        String name = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey()).
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[0].name");
        System.out.println("workspace name = " + name);

        //hamcrest assert
        assertThat(name, equalTo("Team Workspace"));
        //testng assert --> Assert.assertEquals(name, "Team Workspace");
    }

    @Test
    public void validate_response_body_hamcrest_learings_with_contains(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey()).
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", contains("Team Workspace", "Rest-assured course", "GPM")
                );
    }

    @Test
    public void validate_response_body_hamcrest_learings_with_contains_in_any_order(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", postmanApiKey()).
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", containsInAnyOrder("Team Workspace", "Rest-assured course", "GPM"),
                        "workspaces.name", is(not(emptyArray())),
                        "workspaces.name", hasSize(3),
                        "workspaces.name", everyItem(startsWith("T"))
                );
    }
}

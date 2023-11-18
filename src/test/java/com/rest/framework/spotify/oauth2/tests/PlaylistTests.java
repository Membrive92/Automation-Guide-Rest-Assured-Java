package com.rest.framework.spotify.oauth2.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.rest.learnings.Utils.Utils.postmanApiKey;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQCM7MfwqIUaXpSWoeN-CYu4T0UFynqv24fprVFwjugUy-xWBJiSya8Z5-sK_igsGTClyTkIbO43dSmI3z-3oaFse-WM85ZO7ZteyHuDIfgdJslDtZjOahfQOqD7tUdjVRuapKGtSNYxZ87g8tSaNbKuZYjQaZIAUs-MoM9Sl_GQb6yV2VM3woyP5jPXsk72iAwh8aVfyh82ZjogFK7ZB-rbtlRmUr8i-56kfSbLqAmSrFOhpc67BkdgzxrMStR1-jXgS92OYWWynX17";

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
            setBaseUri("https://api.spotify.com").
            setBasePath("/v1").
            addHeader("Authorization", "Bearer "+ access_token).
                    setContentType(ContentType.JSON).
                    log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void ShouldBeAbleToCreateAPlaylist(){
        String payload = "{\n" +
                "\"name\": \"JoseApiTst\",\n" +
                "\"description\": \"New playlist description\",\n" +
                "\"public\": true\n" +
                "}";

        given(requestSpecification).
                body(payload).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(responseSpecification).
                assertThat().
                statusCode(201).
                body("name", equalTo("JoseApiTst"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(true));
    }
    @Test
    public void ShouldBeAbleToGetAPlaylist(){
        given(requestSpecification).
        when().get("/playlists/2717aBVnB3oP3yncF6EJid").
        then().spec(responseSpecification).
                assertThat().
                statusCode(200).
                body("name", equalTo("Updated Playlist Name"),
                        "description", equalTo("Updated playlist description"),
                        "public", equalTo(false));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist(){
        String payload = "{\n" +
                "    \"name\": \"Updated Playlist Name auto\",\n" +
                "    \"description\": \"Updated playlist auto description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
        when().put("/playlists/2717aBVnB3oP3yncF6EJid").
        then().spec(responseSpecification).
                assertThat().
                statusCode(200);
    }
}

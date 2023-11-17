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
    String access_token = "BQD_TSCStLKMSh0neB9eVlPqLoUuN2XB8M36-tdqf6wQGsGDRVZXiUQkj96LFdOoeR-aA1Rqyn6FvOUfm85F3e3cMNMB5mNns5nXpM-cjQRbwcC4UiZHbS0sKLODJzN7WiAjp20OhN-4gdjDO9o3abftg_u14HtgRo5qNWciK-aKsdIuiT5CpThVSUxO0FANvFuma33H98mDyq2UnQiRsgzUOR8OtPQ02FVoCmEiAcMP5EThnuKNc9gpYBzrKX1OtkkYtBME4fQG20Ft";

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
                expectContentType(ContentType.JSON).
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
}

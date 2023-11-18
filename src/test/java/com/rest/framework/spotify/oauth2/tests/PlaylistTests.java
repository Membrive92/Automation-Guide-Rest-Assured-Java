package com.rest.framework.spotify.oauth2.tests;

import com.rest.framework.spotify.oauth2.pojo.Error;
import com.rest.framework.spotify.oauth2.pojo.Playlist;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQA7g8ftGchM6tq84uUKFzCcOQ9_ErfcEcJ336H4PB9i5zXQ5vqW4mRoKtj51W2FGZF_I3ZYE32Kg9ovzo98hlUzJqvcLAUmscvawVBzqTxDfUKsiv22vewtbDr3gpe0gd8f9jzF-LpfZT6TfAdbZAb1NOm7HqGx5XtMTCEb2Ninl7y9r1K-jnGmjvE31uihGOEWy8Fq1x2hrfNnpWo_LK1Qktffu4YsSERK0GE_0BILpixjLWeCLvmiziPj7J9UTW6sdScRwnCfeB2A";

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
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic(false);

      Playlist responsePlaylist =  given(requestSpecification).
                body(requestPlaylist).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(responseSpecification).
                assertThat().
                statusCode(201).
                extract().
                response().
                as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }
    @Test
    public void ShouldBeAbleToGetAPlaylist(){
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("Update Playlist");
        requestPlaylist.setDescription("New Update playlist description");
        requestPlaylist.setPublic(false);

        Playlist responsePlaylist = given(requestSpecification).
        when().get("/playlists/2717aBVnB3oP3yncF6EJid").
        then().spec(responseSpecification).
                assertThat().
                statusCode(200).
                extract().
                response().
                as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist(){
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("Update Playlist");
        requestPlaylist.setDescription("New Update playlist description");
        requestPlaylist.setPublic(false);

        given(requestSpecification).
                body(requestPlaylist).
        when().put("/playlists/2717aBVnB3oP3yncF6EJid").
        then().spec(responseSpecification).
                assertThat().
                statusCode(200);
    }

    @Test
    public void ShouldNotdBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic(false);

      Error error =  given(requestSpecification).
                body(requestPlaylist).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(responseSpecification).
                assertThat().
                statusCode(400).
                extract().
                response().
                as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void ShouldNotdBeAbleToCreateAPlaylistWithExpiredToken(){
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic(false);

        Error error = given().
                baseUri("https://api.spotify.com").
                basePath("/v1").
                header("Authorization", "Bearer 123345").
                contentType(ContentType.JSON).
                log().all().
                body(requestPlaylist).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(responseSpecification).
                assertThat().
                statusCode(401).
                extract().
                response().
                as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}

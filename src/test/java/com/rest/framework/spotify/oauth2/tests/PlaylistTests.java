package com.rest.framework.spotify.oauth2.tests;

import com.rest.framework.spotify.oauth2.pojo.Error;
import com.rest.framework.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;


import static com.rest.framework.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.rest.framework.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    public void ShouldBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

      Playlist responsePlaylist = given(getRequestSpec()).
                body(requestPlaylist).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(getResponseSpec()).
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
        Playlist requestPlaylist = new Playlist().
                setName("Update Playlist").
                setDescription("New Update playlist description").
                setPublic(false);

        Playlist responsePlaylist = given(getRequestSpec()).
        when().get("/playlists/2717aBVnB3oP3yncF6EJid").
        then().spec(getResponseSpec()).
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
        Playlist requestPlaylist = new Playlist().
                setName("Update Playlist").
                setDescription("New Update playlist description").
                setPublic(false);

        given(getRequestSpec()).
                body(requestPlaylist).
        when().put("/playlists/2717aBVnB3oP3yncF6EJid").
        then().spec(getResponseSpec()).
                assertThat().
                statusCode(200);
    }

    @Test
    public void ShouldNotdBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = new Playlist().
                setName("").
                setDescription("New playlist description").
                setPublic(false);

      Error error = given(getRequestSpec()).
                body(requestPlaylist).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(getResponseSpec()).
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
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

        Error error = given().
                baseUri("https://api.spotify.com").
                basePath("/v1").
                header("Authorization", "Bearer 123345").
                contentType(ContentType.JSON).
                log().all().
                body(requestPlaylist).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(getResponseSpec()).
                assertThat().
                statusCode(401).
                extract().
                response().
                as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}

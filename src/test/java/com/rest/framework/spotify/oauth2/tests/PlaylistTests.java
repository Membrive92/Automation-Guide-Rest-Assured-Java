package com.rest.framework.spotify.oauth2.tests;

import com.rest.framework.spotify.oauth2.api.Assert;
import com.rest.framework.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.rest.framework.spotify.oauth2.pojo.Error;
import com.rest.framework.spotify.oauth2.pojo.Playlist;
import com.rest.framework.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.rest.framework.spotify.oauth2.api.Assert.assertError;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests {

    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("Test manager system")
    @Issue("Bug 12334")
    @Description("Create a playlist with  New Playlist name")
    @Test(description = "should be able to create a playlist")
    public void ShouldBeAbleToCreateAPlaylist() throws FileNotFoundException {
      Playlist requestPlaylist = new Playlist("New Playlist","New playlist description", false);
      Response response = PlaylistApi.post(requestPlaylist);
      Assert.assertStatusCode(response.statusCode(), 201);
      Assert.assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Get a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("Test manager system")
    @Issue("Bug 12334")
    @Description("Get a specific playlist")
    @Test(description = "should be able to get a playlist")
    public void ShouldBeAbleToGetAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist("Update Playlist","New Update playlist description", false);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        Assert.assertStatusCode(response.statusCode(), 200);
        Assert.assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Update a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("Test manager system")
    @Issue("Bug 12334")
    @Description("Update a playlist with Update Playlist name")
    @Test(description = "should be able to update a playlist")
    public void ShouldBeAbleToUpdateAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist("Update Playlist","New Update playlist description", false);
        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        Assert.assertStatusCode(response.statusCode(), 200);
    }

    @Story("Negative create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("Test manager system")
    @Issue("Bug 12334")
    @Description("Check that playlist should not be create with empty name")
    @Test(description = "should not be able to create a playlist")
    public void ShouldNotdBeAbleToCreateAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist("","New Update playlist description", false);
        Response response = PlaylistApi.post(requestPlaylist);
        Assert.assertStatusCode(response.statusCode(), 400);
        assertError(response.as(Error.class),400,"Missing required field: name");
    }

    @Story("Negative Create a playlist story with expire token")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("Test manager system")
    @Issue("Bug 12334")
    @Description("Check that playlist should not be create with a expired token")
    @Test(description = "should not be  able to create a playlist with expired token")
    public void ShouldNotdBeAbleToCreateAPlaylistWithExpiredToken() throws FileNotFoundException {
        String invalidToken = "12345";
        Playlist requestPlaylist = new Playlist("New Playlist","New playlist description", false);
        Response response = PlaylistApi.post(invalidToken,requestPlaylist);
        assertThat(response.statusCode(), equalTo(401));
        assertError(response.as(Error.class),401,"Invalid access token");
    }
}

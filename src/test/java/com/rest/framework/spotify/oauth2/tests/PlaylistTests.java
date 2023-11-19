package com.rest.framework.spotify.oauth2.tests;

import com.rest.framework.spotify.oauth2.api.Assert;
import com.rest.framework.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.rest.framework.spotify.oauth2.pojo.Error;
import com.rest.framework.spotify.oauth2.pojo.Playlist;
import com.rest.framework.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.rest.framework.spotify.oauth2.api.Assert.assertError;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    public void ShouldBeAbleToCreateAPlaylist() throws FileNotFoundException {
      Playlist requestPlaylist = new Playlist("New Playlist","New playlist description", false);
      Response response = PlaylistApi.post(requestPlaylist);
      Assert.assertStatusCode(response.statusCode(), 201);
      Assert.assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist("Update Playlist","New Update playlist description", false);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        Assert.assertStatusCode(response.statusCode(), 200);
        Assert.assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist("Update Playlist","New Update playlist description", false);
        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        Assert.assertStatusCode(response.statusCode(), 200);
    }

    @Test
    public void ShouldNotdBeAbleToCreateAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist("","New Update playlist description", false);
        Response response = PlaylistApi.post(requestPlaylist);
        Assert.assertStatusCode(response.statusCode(), 400);
        assertError(response.as(Error.class),400,"Missing required field: name");
    }

    @Test
    public void ShouldNotdBeAbleToCreateAPlaylistWithExpiredToken() throws FileNotFoundException {
        String invalidToken = "12345";
        Playlist requestPlaylist = new Playlist("New Playlist","New playlist description", false);
        Response response = PlaylistApi.post(invalidToken,requestPlaylist);
        assertThat(response.statusCode(), equalTo(401));
        assertError(response.as(Error.class),401,"Invalid access token");
    }
}

package com.rest.framework.spotify.oauth2.tests;

import com.rest.framework.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.rest.framework.spotify.oauth2.pojo.Error;
import com.rest.framework.spotify.oauth2.pojo.Playlist;
import com.rest.framework.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    public void ShouldBeAbleToCreateAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

      Response response = PlaylistApi.post(requestPlaylist);
      assertThat(response.statusCode(), equalTo(201));

      Playlist responsePlaylist = response.as(Playlist.class);

      assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
      assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
      assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist().
                setName("Update Playlist").
                setDescription("New Update playlist description").
                setPublic(false);

        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertThat(response.statusCode(), equalTo(200));

        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist().
                setName("Update Playlist").
                setDescription("New Update playlist description").
                setPublic(false);

        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void ShouldNotdBeAbleToCreateAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist = new Playlist().
                setName("").
                setDescription("New playlist description").
                setPublic(false);

        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(400));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void ShouldNotdBeAbleToCreateAPlaylistWithExpiredToken() throws FileNotFoundException {
        String invalidToken = "12345";
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

        Response response = PlaylistApi.post(invalidToken,requestPlaylist);
        assertThat(response.statusCode(), equalTo(401));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}

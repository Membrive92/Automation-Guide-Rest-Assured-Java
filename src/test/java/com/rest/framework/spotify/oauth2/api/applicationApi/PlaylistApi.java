package com.rest.framework.spotify.oauth2.api.applicationApi;

import com.rest.framework.spotify.oauth2.api.RestBase;
import com.rest.framework.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.rest.framework.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist){
        return RestBase.post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists", getToken(), requestPlaylist);
    }

    public static Response post(String token,Playlist requestPlaylist){
        return RestBase.post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestBase.get("/playlists/" + playlistId, getToken());
    }

    public static Response put(String playlistId, Playlist requestPlaylist){
        return RestBase.put("/playlists/" + playlistId, getToken(), requestPlaylist);
    }
}

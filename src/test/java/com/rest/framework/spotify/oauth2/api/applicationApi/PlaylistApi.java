package com.rest.framework.spotify.oauth2.api.applicationApi;

import com.rest.framework.spotify.oauth2.api.RestBase;
import com.rest.framework.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.rest.framework.spotify.oauth2.api.TokenManager.renewToken;

public class PlaylistApi {
   // static  String access_token = "BQCR4JSpfAMSjjjLn9qvJNRNXiuIUVd1XxcKpcT2bs8Y0rlwLqfKwtgk_TM2TF96NHJdQeazUMrL4u9ejUiCH0ka-lFMNJsxSfs-e3LWxrIwxTbX347qlgXSqp-FiWLvqzyBV4mFnMFoTKe6dW52uZh9ZT-G3LmULLVixVbQBGjpJh7lLhfh5Anh9TXeGDQaA4U3CBsTSSehwKj2zQlWhPwOMieEa5807ZBdK568aAZ0QtjGxS-4y5BabKtv1KXElQfflJqQdXOnsPJu";

    public static Response post(Playlist requestPlaylist){
        return RestBase.post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists", renewToken(), requestPlaylist);
    }

    public static Response post(String token,Playlist requestPlaylist){
        return RestBase.post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestBase.get("/playlists/" + playlistId, renewToken());
    }

    public static Response put(String playlistId, Playlist requestPlaylist){
        return RestBase.put("/playlists/" + playlistId, renewToken(), requestPlaylist);
    }
}

package com.rest.framework.spotify.oauth2.api.applicationApi;

import com.rest.framework.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.rest.framework.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class PlaylistApi {
    static  String access_token = "BQCR4JSpfAMSjjjLn9qvJNRNXiuIUVd1XxcKpcT2bs8Y0rlwLqfKwtgk_TM2TF96NHJdQeazUMrL4u9ejUiCH0ka-lFMNJsxSfs-e3LWxrIwxTbX347qlgXSqp-FiWLvqzyBV4mFnMFoTKe6dW52uZh9ZT-G3LmULLVixVbQBGjpJh7lLhfh5Anh9TXeGDQaA4U3CBsTSSehwKj2zQlWhPwOMieEa5807ZBdK568aAZ0QtjGxS-4y5BabKtv1KXElQfflJqQdXOnsPJu";

    public static Response post(Playlist requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer "+ access_token).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String token,Playlist requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + token).
        when().post("/users/31n4n3stwzbz5csjh7mycjbppehi/playlists").
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    //2717aBVnB3oP3yncF6EJid
    public static Response get(String playlistId){
        return given(getRequestSpec()).
                header("Authorization", "Bearer "+ access_token).
        when().get("/playlists/" + playlistId).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response put(String playlistId, Playlist requestPlaylist){
       return given(getRequestSpec()).
                header("Authorization", "Bearer "+ access_token).
                body(requestPlaylist).
       when().put("/playlists/" + playlistId).
       then().spec(getResponseSpec()).
                extract().
                response();
    }
}

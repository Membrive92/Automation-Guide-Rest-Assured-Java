package com.rest.framework.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.rest.framework.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestBase {

    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer "+ token).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec()).
                formParams(formParams).
        when().post("/api/token").
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, String token){
        return given(getRequestSpec()).
                header("Authorization", "Bearer "+ token).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response put(String path, String token, Object requestPlaylist){
       return given(getRequestSpec()).
                header("Authorization", "Bearer "+ token).
                body(requestPlaylist).
       when().put(path).
       then().spec(getResponseSpec()).
                extract().
                response();
    }
}

package com.rest.framework.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.rest.framework.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    public static String renewToken(){
        HashMap<String,String> formParams = new HashMap<String,String>();
        formParams.put("client_id","06411ea52590448a86e5f5dab0b5b857");
        formParams.put("client_secret","85d0cbfa9ebf47968fc799a2e0e88e9b");
        formParams.put("refresh_token","AQDMbU1ipH0cgClkxlbxCACw36kntG5_N2g4ZL7v7g5oNcS6tnFyDWBaiJf4jdLJQfPdCLNzc_3EFdD64FpqCu1KiMYtNeCpWCGvTEp_tIQ1K6eiAwt74Qs3jB_IHfEyf3c");
        formParams.put("grant_type","refresh_token");

        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
                when().post("/api/token").
                then().spec(getResponseSpec()).
                extract().
                response();

        if (response.statusCode() != 200){
            throw new RuntimeException("Renew token has failed");
        }
        return response.path("access_token");
    }
}

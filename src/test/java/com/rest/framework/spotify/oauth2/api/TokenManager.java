package com.rest.framework.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.rest.framework.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){
        try{
            if(access_token == null || Instant.now().isAfter(expiry_time)){
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is used");
            }

        } catch (Exception e){
            throw  new RuntimeException("Failed to get token");

        }
        return access_token;
    }

    private static Response renewToken(){
        HashMap<String,String> formParams = new HashMap<String,String>();
        formParams.put("client_id","06411ea52590448a86e5f5dab0b5b857");
        formParams.put("client_secret","85d0cbfa9ebf47968fc799a2e0e88e9b");
        formParams.put("refresh_token","AQDMbU1ipH0cgClkxlbxCACw36kntG5_N2g4ZL7v7g5oNcS6tnFyDWBaiJf4jdLJQfPdCLNzc_3EFdD64FpqCu1KiMYtNeCpWCGvTEp_tIQ1K6eiAwt74Qs3jB_IHfEyf3c");
        formParams.put("grant_type","refresh_token");

        Response response = RestBase.postAccount(formParams);

        if (response.statusCode() != 200){
            throw new RuntimeException("Renew token has failed");
        }
        return response;
    }
}

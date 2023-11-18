package com.rest.framework.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
  static  String access_token = "BQA7g8ftGchM6tq84uUKFzCcOQ9_ErfcEcJ336H4PB9i5zXQ5vqW4mRoKtj51W2FGZF_I3ZYE32Kg9ovzo98hlUzJqvcLAUmscvawVBzqTxDfUKsiv22vewtbDr3gpe0gd8f9jzF-LpfZT6TfAdbZAb1NOm7HqGx5XtMTCEb2Ninl7y9r1K-jnGmjvE31uihGOEWy8Fq1x2hrfNnpWo_LK1Qktffu4YsSERK0GE_0BILpixjLWeCLvmiziPj7J9UTW6sdScRwnCfeB2A";

    public static RequestSpecification getRequestSpec(){
         return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer "+ access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();

    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}

package com.rest.learnings.oauth2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

//for this tests is mandatory create a goolge account and config an app in developers.google
public class GmailApi {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "ya29.a0AfB_byA9Sd1dpyq8S8roNRvMZPg2pc8w2dMHqD6KeCMmqMKlC0BA2Olk80KRCe313WabxKOXhzXK6ulWNreNjGDuHmeZAMWzlFyM5f5Uljs7DISvSnRsowTsnDFbLVDbAXoN4yLA0dk7wjrHUkDpKvVUdt5UxFqYdpsHaCgYKAYcSARESFQHGX2Mi5Uvpwtbk0HgZOwjVqEzC_w0171";
    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://gmail.googleapis.com").
                addHeader("Authorization", "Bearer "+access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void getUserProfile() {
        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userid", "joseapitst@gmail.com").
        when().
                get("/users/{userid}/profile").
        then().spec(responseSpecification);
    }

    @Test
    public void sendMessage() {
        String msg = "From: joseapitst@gmail.com\n" +
                "To: joseapitst@gmail.com\n" +
                "Subject: Test Email\n" +
                "\n" +
                "Sending from Gmail Api";

        String base64UrlEncodeMsg = Base64.getUrlEncoder().encodeToString(msg.getBytes());
        HashMap<String,String> payload = new HashMap<String,String>();
        payload.put("raw", base64UrlEncodeMsg);

        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userid", "joseapitst@gmail.com").
                body(payload).
        when().
                post("/users/{userid}/messages/send").
        then().spec(responseSpecification);
    }
}

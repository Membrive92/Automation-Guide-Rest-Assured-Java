package com.rest.Pojo.ComplexLivePojo.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

import static com.rest.Utils.Utils.postmanApiKey;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.requestSpecification;

public class ComplexPojoTest {
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key", postmanApiKey()).
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
    public void complex_pojo_create_collection() throws JsonProcessingException, JSONException {
        Header header = new Header("Content-Type", "application/json");
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        Body body = new Body("raw","{\"data\": \"123\"}");

        Request request = new Request("https://postman-echo.com/post", "POST", headerList, body,
                "This is a sample post Request");

        RequestRoot requestRoot = new RequestRoot("Sample POST Request", request);
        List<RequestRoot> requestRootList = new ArrayList<RequestRoot>();
        requestRootList.add(requestRoot);

        Folder folder = new Folder("This is a folder", requestRootList);
        List<Folder> folderList = new ArrayList<Folder>();
        folderList.add(folder);

        Info info = new Info("Sample Collection1" , "This is just a sample collection"
                , "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        Collection collection = new Collection(info, folderList);
        CollectionRoot collectionRoot = new CollectionRoot(collection);

    String collectionUid = given().
                body(collectionRoot).
    when().
                post("/collections").
    then().spec(responseSpecification).
               extract().
               response().
               path("collection.uid");

    CollectionRoot deserializedCollectionRoot = given().
            pathParam("collectionUid", collectionUid).
    when().
            get("/collections/{collectionUid}").
    then().spec(responseSpecification).
            extract().
            response().
            as(CollectionRoot.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String collectionRootStr = objectMapper.writeValueAsString(collectionRoot);
        String deserializedCollectionRootStr = objectMapper.writeValueAsString(deserializedCollectionRoot);


        //Using JSONASSERT for compare full body
        JSONAssert.assertEquals(collectionRootStr, deserializedCollectionRootStr,
               new CustomComparator(JSONCompareMode.STRICT_ORDER,

                       //Except require fields
                       new Customization("collection.item[*].item[*].request.url" , new ValueMatcher<Object>() {
                           public boolean equal(Object o1, Object o2){
                               return true;
                           }
                       })) );
    }
}

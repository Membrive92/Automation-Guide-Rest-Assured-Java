package com.rest.Pojo.ComplexLivePojo.CollectionPojos.CollectionRoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Collection.CollectionBase;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Collection.CollectionResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRootResponse extends CollectionRootBase {

    CollectionResponse collection;

    public CollectionRootResponse(CollectionResponse collection) {
        this.collection = collection;
    }

    public CollectionRootResponse() {
    }

    public CollectionResponse getCollection() {
        return collection;
    }

    public void setCollection(CollectionResponse collection) {
        this.collection = collection;
    }
}

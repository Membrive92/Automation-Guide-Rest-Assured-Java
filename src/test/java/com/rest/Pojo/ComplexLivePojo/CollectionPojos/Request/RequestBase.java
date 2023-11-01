package com.rest.Pojo.ComplexLivePojo.CollectionPojos.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Body;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Header;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class RequestBase {
    private String method;
    List<Header> header;
    Body body;
    private String description;

    public RequestBase(String method, List<Header> header, Body body, String description) {
        this.method = method;
        this.header = header;
        this.body = body;
        this.description = description;
    }

    public RequestBase() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

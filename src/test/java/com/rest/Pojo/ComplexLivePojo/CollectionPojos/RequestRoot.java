package com.rest.Pojo.ComplexLivePojo.CollectionPojos;

public class RequestRoot {
    private String name;
    Request request;

    public RequestRoot(String name, Request request) {
        this.name = name;
        this.request = request;
    }

    public RequestRoot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}

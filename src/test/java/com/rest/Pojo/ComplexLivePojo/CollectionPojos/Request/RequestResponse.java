package com.rest.Pojo.ComplexLivePojo.CollectionPojos.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Body;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Header;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.URL;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse extends RequestBase {
    private URL url;

    public RequestResponse(URL url, String method, List<Header> header, Body body, String description) {
        super(method,header,body,description);
        this.url = url;
    }

    public RequestResponse() {
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}

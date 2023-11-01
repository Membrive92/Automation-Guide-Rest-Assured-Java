package com.rest.Pojo.ComplexLivePojo.CollectionPojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection {
    Info info;
    List<Folder> item;

    public Collection(Info info, List<Folder> item) {
        this.info = info;
        this.item = item;
    }

    public Collection() {}

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Folder> getItem() {
        return item;
    }

    public void setItem(List<Folder> item) {
        this.item = item;
    }
}

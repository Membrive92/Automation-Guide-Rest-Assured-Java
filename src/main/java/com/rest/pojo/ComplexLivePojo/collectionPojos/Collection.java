package com.rest.pojo.ComplexLivePojo.collectionPojos;

import java.util.List;

public class Collection {
    Info info;
    List<Object> item;

    public Collection(Info info, List<Object> item) {
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

    public List<Object> getItem() {
        return item;
    }

    public void setItem(List<Object> item) {
        this.item = item;
    }
}

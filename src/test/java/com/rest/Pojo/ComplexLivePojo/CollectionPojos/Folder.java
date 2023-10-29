package com.rest.Pojo.ComplexLivePojo.CollectionPojos;

import java.util.List;

public class Folder {
    private String name;
    List<RequestRoot> item;

    public Folder(String name, List<RequestRoot> item) {
        this.name = name;
        this.item = item;
    }

    public Folder() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RequestRoot> getItem() {
        return item;
    }

    public void setItem(List<RequestRoot> item) {
        this.item = item;
    }
}

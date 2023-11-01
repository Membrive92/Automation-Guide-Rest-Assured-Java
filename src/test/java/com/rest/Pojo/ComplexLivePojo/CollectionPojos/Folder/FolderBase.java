package com.rest.Pojo.ComplexLivePojo.CollectionPojos.Folder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FolderBase {
    private String name;


    public FolderBase(String name) {
        this.name = name;
    }

    public FolderBase() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

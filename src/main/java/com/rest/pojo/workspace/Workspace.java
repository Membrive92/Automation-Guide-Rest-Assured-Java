package com.rest.pojo.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;


@JsonIgnoreProperties(value = {"id", "myHashMap"}, allowSetters = true)
public class Workspace {

    @JsonIgnore
    private int i;
    private String id;
    @JsonIgnore
    private HashMap<String,String> myHashMap;
    private String name;
    private String type;
    private String description;

    public Workspace (String name, String type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Workspace(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }


    public HashMap<String, String> getMyHashMap() {
        return myHashMap;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setMyHashMap(HashMap<String, String> myHashMap) {
        this.myHashMap = myHashMap;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.rest.Pojo.ComplexLivePojo.CollectionPojos.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Folder.FolderBase;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Folder.FolderRequest;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Folder.FolderResponse;
import com.rest.Pojo.ComplexLivePojo.CollectionPojos.Info;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionResponse extends CollectionBase {
    List<FolderResponse> item;

    public CollectionResponse(Info info, List<FolderResponse> item) {
        super(info);
        this.item = item;
    }

    public CollectionResponse() {}

    public List<FolderResponse> getItem() {
        return item;
    }

    public void setItem(List<FolderResponse> item) {
        this.item = item;
    }
}

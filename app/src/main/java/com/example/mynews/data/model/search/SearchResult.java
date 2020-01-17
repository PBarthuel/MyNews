package com.example.mynews.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @Expose
    @SerializedName("response")
    public Response response;
}

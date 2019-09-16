package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class SearchResult {

    @Expose
    @SerializedName("response")
    public Response response;
    @Expose
    @SerializedName("copyright")
    public String copyright;
    @Expose
    @SerializedName("status")
    public String status;
}

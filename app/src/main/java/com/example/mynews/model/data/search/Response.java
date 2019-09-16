package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @Expose
    @SerializedName("meta")
    public Meta meta;
    @Expose
    @SerializedName("docs")
    public List<Docs> docs;
}

package com.example.mynews.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docs {
    @Expose
    @SerializedName("pub_date")
    public String pubDate;
    @Expose
    @SerializedName("headline")
    public Headline headline;
    @Expose
    @SerializedName("multimedia")
    public List<Multimedia> multimedia;
    @Expose
    @SerializedName("snippet")
    public String snippet;
    @Expose
    @SerializedName("web_url")
    public String webUrl;
}

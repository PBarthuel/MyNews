package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedia {
    @Expose
    @SerializedName("crop_name")
    public String cropName;
    @Expose
    @SerializedName("subType")
    public String subtype;
    @Expose
    @SerializedName("legacy")
    public Legacy legacy;
    @Expose
    @SerializedName("width")
    public int width;
    @Expose
    @SerializedName("height")
    public int height;
    @Expose
    @SerializedName("url")
    public String url;
    @Expose
    @SerializedName("type")
    public String type;
    @Expose
    @SerializedName("rank")
    public int rank;
}

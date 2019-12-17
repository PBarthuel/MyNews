package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedia {
    @Expose
    @SerializedName("width")
    public int width;
    @Expose
    @SerializedName("height")
    public int height;
    @Expose
    @SerializedName("url")
    public String url;
}

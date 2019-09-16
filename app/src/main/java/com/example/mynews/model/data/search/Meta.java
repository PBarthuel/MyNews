package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {
    @Expose
    @SerializedName("time")
    public int time;
    @Expose
    @SerializedName("offset")
    public int offset;
    @Expose
    @SerializedName("hits")
    public int hits;
}

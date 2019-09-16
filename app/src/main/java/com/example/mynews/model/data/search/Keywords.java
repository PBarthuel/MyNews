package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keywords {
    @Expose
    @SerializedName("major")
    public String major;
    @Expose
    @SerializedName("rank")
    public int rank;
    @Expose
    @SerializedName("value")
    public String value;
    @Expose
    @SerializedName("name")
    public String name;
}

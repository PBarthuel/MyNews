package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline {
    @Expose
    @SerializedName("print_headline")
    public String printHeadline;
    @Expose
    @SerializedName("main")
    public String main;
}

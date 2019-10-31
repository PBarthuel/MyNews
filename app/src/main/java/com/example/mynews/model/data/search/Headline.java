package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline {
    @Override
    public String toString() {
        return "Headline{" +
                "printHeadline='" + printHeadline + '\'' +
                ", main='" + main + '\'' +
                '}';
    }

    @Expose
    @SerializedName("print_headline")
    public String printHeadline;
    @Expose
    @SerializedName("main")
    public String main;
}

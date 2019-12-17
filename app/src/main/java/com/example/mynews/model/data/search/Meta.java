package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {
    @Expose
    @SerializedName("hits")
    public int hits;
}

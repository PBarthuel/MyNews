package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Byline {
    @Expose
    @SerializedName("person")
    public List<Person> person;
    @Expose
    @SerializedName("original")
    public String original;
}

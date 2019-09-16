package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {
    @Expose
    @SerializedName("rank")
    public int rank;
    @Expose
    @SerializedName("organization")
    public String organization;
    @Expose
    @SerializedName("role")
    public String role;
    @Expose
    @SerializedName("lastname")
    public String lastname;
    @Expose
    @SerializedName("firstname")
    public String firstname;
}

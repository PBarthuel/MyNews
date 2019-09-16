package com.example.mynews.model.data.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docs {
    @Expose
    @SerializedName("uri")
    public String uri;
    @Expose
    @SerializedName("word_count")
    public int wordCount;
    @Expose
    @SerializedName("_id")
    public String Id;
    @Expose
    @SerializedName("type_of_material")
    public String typeOfMaterial;
    @Expose
    @SerializedName("byline")
    public Byline byline;
    @Expose
    @SerializedName("subsection_name")
    public String subsectionName;
    @Expose
    @SerializedName("section_name")
    public String sectionName;
    @Expose
    @SerializedName("news_desk")
    public String newsDesk;
    @Expose
    @SerializedName("document_type")
    public String documentType;
    @Expose
    @SerializedName("pub_date")
    public String pubDate;
    @Expose
    @SerializedName("keywords")
    public List<Keywords> keywords;
    @Expose
    @SerializedName("headline")
    public Headline headline;
    @Expose
    @SerializedName("multimedia")
    public List<Multimedia> multimedia;
    @Expose
    @SerializedName("source")
    public String source;
    @Expose
    @SerializedName("blog")
    public Blog blog;
    @Expose
    @SerializedName("print_page")
    public String printPage;
    @Expose
    @SerializedName("abstract")
    public String searchAbstract;
    @Expose
    @SerializedName("lead_paragraph")
    public String leadParagraph;
    @Expose
    @SerializedName("snippet")
    public String snippet;
    @Expose
    @SerializedName("web_url")
    public String webUrl;
}

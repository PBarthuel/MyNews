
package com.example.mynews.data.model.most_popular;

import java.util.List;

import com.example.mynews.data.model.most_popular.Medium;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostPopularArticle {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    public String getUrl() {
        return url;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getSection() {
        return section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Medium> getMedia() {
        return media;
    }

}

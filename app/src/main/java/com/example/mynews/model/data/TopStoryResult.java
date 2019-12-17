package com.example.mynews.model.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStoryResult {

    @SerializedName("results")
    @Expose
    private List<TopStoryArticle> topStoryArticles = null;

    public List<TopStoryArticle> getTopStoryArticles() {
        return topStoryArticles;
    }

}


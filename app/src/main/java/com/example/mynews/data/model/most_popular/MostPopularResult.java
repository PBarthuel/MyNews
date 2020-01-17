
package com.example.mynews.data.model.most_popular;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostPopularResult {

    @SerializedName("results")
    @Expose
    private List<MostPopularArticle> mostPopularArticles = null;

    public List<MostPopularArticle> getMostPopularArticles() {
        return mostPopularArticles;
    }

}

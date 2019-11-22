package com.example.mynews;

import com.example.mynews.model.data.MostPopularResult;
import com.example.mynews.model.data.TopStoryResult;
import com.example.mynews.model.data.search.SearchResult;

import java.net.URI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{topStorySection}.json")
    Call<TopStoryResult> getTopStory(@Path("topStorySection") String section);

    @GET("svc/mostpopular/v2/emailed/7.json")
    Call<MostPopularResult> getMostPopularStory();

    @GET("svc/search/v2/articlesearch.json")
    Call<SearchResult> getSearchResponse(@Query(value = "fq") String filteredQuery);
}
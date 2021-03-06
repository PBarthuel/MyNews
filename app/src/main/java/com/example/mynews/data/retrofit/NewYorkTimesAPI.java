package com.example.mynews.data.retrofit;

import com.example.mynews.data.model.most_popular.MostPopularResult;
import com.example.mynews.data.model.top_story.TopStoryResult;
import com.example.mynews.data.model.search.SearchResult;

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
    Call<SearchResult> getSearchResponse(@Query(value = "fq") String filteredQuery,
                                         @Query(value = "beginDate") String beginDate,
                                         @Query(value = "endDate") String endDate);

}
package com.example.mynews;

import com.example.mynews.model.MostPopularResult;
import com.example.mynews.model.TopStoryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{topStorySection}.json")
    Call<TopStoryResult> getTopStory(@Path("topStorySection") String section);

    @GET("svc/mostpopular/v2/emailed/7.json")
    Call<MostPopularResult> getMostPopularStory();

}
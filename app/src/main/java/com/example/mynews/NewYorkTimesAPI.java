package com.example.mynews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{topStorySection}.json")
    Call<TopStoryResult> getTopStory(@Path("topStorySection") String section, @Query("api-key")String apiKey);

}
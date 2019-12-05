package com.example.mynews;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mynews.model.data.search.SearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationWorker extends Worker {

    private Intent intent;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String userInput = getInputData().getString("user_input");
        NewYorkTimesAPI api = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        api.getSearchResponse(userInput).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });
        //TODO faire en sorte que tu recupère tout les article la premiere fois et que tu compare avec les nouveaux avec le getSearchResponse
        return null;
    }
}

package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mynews.model.data.search.SearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {

    public static Intent navigate (Context context, String userInput) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("courgette", userInput);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        String userInput = getIntent().getStringExtra("courgette");

        NewYorkTimesAPI api =RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        api.getSearchResponse(userInput).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.d("courgette", "" + response);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });
    }
}

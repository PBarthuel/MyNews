package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final ArticleAdapter articleAdapter = new ArticleAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_rv);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewYorkTimesAPI service = retrofit.create(NewYorkTimesAPI.class);
        Call<TopStoryResult> call = service.getTopStory("science", "5A6K8wBcTBzAf39MXVBC9IBC1K7bAdP4");
        call.enqueue(new Callback<TopStoryResult>() {
            @Override
            public void onResponse(Call<TopStoryResult> call, Response<TopStoryResult> response) {
                Toast.makeText(MainActivity.this, "Le titre du premier article est "+ response.body().getResults().get(0).getTitle() , Toast.LENGTH_SHORT).show();
                articleAdapter.setNewData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TopStoryResult> call, Throwable t) {

            }
        });

    }
}

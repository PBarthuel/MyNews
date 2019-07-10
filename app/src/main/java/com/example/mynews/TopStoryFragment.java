package com.example.mynews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.model.TopStoryResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopStoryFragment extends Fragment {

    private final ArticleAdapter articleAdapter = new ArticleAdapter();
    private Call<TopStoryResult> callTopStory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topstory, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.topstoryfragment_rv);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewYorkTimesAPI service = retrofit.create(NewYorkTimesAPI.class);
        callTopStory = service.getTopStory("science", "5A6K8wBcTBzAf39MXVBC9IBC1K7bAdP4");
        callTopStory.enqueue(new Callback<TopStoryResult>() {
            @Override
            public void onResponse(Call<TopStoryResult> call, Response<TopStoryResult> response) {
                articleAdapter.setNewData(response.body().getTopStoryArticles());
            }

            @Override
            public void onFailure(Call<TopStoryResult> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callTopStory.isExecuted() && !callTopStory.isCanceled()) {
            callTopStory.cancel();
        }
    }
}

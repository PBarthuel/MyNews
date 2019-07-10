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

public abstract class TopStoryAbsFragment extends Fragment {

    private final TopStoryArticleAdapter topStoryArticleAdapter = new TopStoryArticleAdapter();
    private Call<TopStoryResult> callTopStory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragments, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.mainfragment_rv);
        recyclerView.setAdapter(topStoryArticleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        NewYorkTimesAPI service = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        callTopStory = service.getTopStory(getSectionName());
        callTopStory.enqueue(new Callback<TopStoryResult>() {
            @Override
            public void onResponse(Call<TopStoryResult> call, Response<TopStoryResult> response) {
                topStoryArticleAdapter.setNewData(response.body().getTopStoryArticles());
            }

            @Override
            public void onFailure(Call<TopStoryResult> call, Throwable t) {

            }
        });

        return view;
    }

    protected abstract String getSectionName();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callTopStory.isExecuted() && !callTopStory.isCanceled()) {
            callTopStory.cancel();
        }
    }
}

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

import com.example.mynews.model.MostPopularResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularFragment extends Fragment {

    private final MostPopularArticleAdapter articleAdapter = new MostPopularArticleAdapter();
    private Call<MostPopularResult> callMostPopular;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragments, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.mainfragment_rv);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        NewYorkTimesAPI service = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        callMostPopular = service.getMostPopularStory();
        callMostPopular.enqueue(new Callback<MostPopularResult>() {
            @Override
            public void onResponse(Call<MostPopularResult> call, Response<MostPopularResult> response) {
                articleAdapter.setNewData(response.body().getMostPopularArticles());
            }

            @Override
            public void onFailure(Call<MostPopularResult> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callMostPopular.isExecuted() && !callMostPopular.isCanceled()) {
            callMostPopular.cancel();
        }
    }
}

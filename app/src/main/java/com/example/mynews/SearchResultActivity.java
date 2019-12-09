package com.example.mynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.model.data.search.Docs;
import com.example.mynews.model.data.search.SearchResult;
import com.example.mynews.model.ui.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity implements ArticleAdapter.Listener {

    private static final int DATA = 0;
    private static final int LOADING = 1;
    private static final int NO_DATA = 2;

    public static Intent navigate(Context context, String userInput, String beginDate, String endDate) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("USER_INPUT", userInput);
        intent.putExtra("BEGIN_DATE", beginDate);
        intent.putExtra("END_DATE", endDate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        final ViewFlipper viewFlipper = findViewById(R.id.search_result_vf);
        RecyclerView recyclerView = findViewById(R.id.search_result_rv);

        final ArticleAdapter articleAdapter = new ArticleAdapter(this);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String userInput = getIntent().getStringExtra("USER_INPUT");

        String beginDate = getIntent().getStringExtra("BEGIN_DATE");

        String endDate = getIntent().getStringExtra("END_DATE");

        viewFlipper.setDisplayedChild(LOADING);

        Log.d("courgette", userInput);

        NewYorkTimesAPI api = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        api.getSearchResponse(userInput, beginDate, endDate).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.d("courgette", "" + response);
                articleAdapter.setNewData(map(response));
                viewFlipper.setDisplayedChild(DATA);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                t.printStackTrace();
                viewFlipper.setDisplayedChild(NO_DATA);
            }
        });
    }

    private List<Article> map(Response<SearchResult> response) {

        List<Article> articleList = new ArrayList<>();

        if (response != null && response.body() != null) {
            for (Docs docs : response.body().response.docs) {
                String imageUrl = null;

                if (docs.multimedia != null && !docs.multimedia.isEmpty()) {
                    imageUrl = "https://static01.nyt.com/" + docs.multimedia.get(0).url;
                }

                Article article = new Article(imageUrl, docs.headline.printHeadline, docs.snippet, docs.pubDate, docs.webUrl);
                articleList.add(article);
            }
        }
        return articleList;
    }

    @Override
    public void onArticleClick(Article article) {

    }
}

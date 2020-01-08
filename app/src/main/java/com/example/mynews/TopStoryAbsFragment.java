package com.example.mynews;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.model.data.TopStoryArticle;
import com.example.mynews.model.data.TopStoryResult;
import com.example.mynews.model.ui.Article;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class TopStoryAbsFragment extends NamedFragment implements ArticleAdapter.Listener {

    private final ArticleAdapter articleAdapter = new ArticleAdapter(this);
    private Call<TopStoryResult> callTopStory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragments, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.mainfragment_rv);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        NewYorkTimesAPI service = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        callTopStory = service.getTopStory(getSectionName());
        callTopStory.enqueue(new Callback<TopStoryResult>() {
            @Override
            public void onResponse(@NonNull Call<TopStoryResult> call, @NonNull Response<TopStoryResult> response) {
                if (response.body() != null && response.body().getTopStoryArticles() != null) {
                    articleAdapter.setNewData(map(response.body().getTopStoryArticles()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopStoryResult> call,@NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }

    protected abstract String getSectionName();

    private List<Article> map(List<TopStoryArticle> articles) {

        List<Article> result = new ArrayList<>();
        for (TopStoryArticle article : articles) {

            String imageUrl = null;

            if (article.getMultimedia() != null && !article.getMultimedia().isEmpty()) {
                imageUrl = article.getMultimedia().get(0).getUrl();
            }

            String title = article.getTitle();

            String topic = article.getSection();
            String subTopic = article.getSubsection();
            String displayedTopic = null;

            if (topic != null && !topic.isEmpty() && subTopic != null && !subTopic.isEmpty()) {
                displayedTopic = topic + " > " + subTopic;
            } else if (topic != null && !topic.isEmpty()) {
                displayedTopic = topic;
            } else if (subTopic != null && !subTopic.isEmpty()) {
                displayedTopic = subTopic;
            }

            String nonHumanDate = article.getPublishedDate();
            LocalDate date = LocalDate.parse(nonHumanDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            String humanDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            String url = article.getUrl();

            result.add(new Article(imageUrl, title, displayedTopic, humanDate, url));
        }
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callTopStory.isExecuted() && !callTopStory.isCanceled()) {
            callTopStory.cancel();
        }
    }

    @Override
    public void onArticleClick(Article article) {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        // set toolbar color and/or setting custom actions before invoking build()
        // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        CustomTabsIntent customTabsIntent = builder.build();
        // and launch the desired Url with CustomTabsIntent.launchUrl()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(article.getUrl()));
    }
}

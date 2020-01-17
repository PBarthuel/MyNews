package com.example.mynews.search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.ArticleAdapter;
import com.example.mynews.data.retrofit.NewYorkTimesAPI;
import com.example.mynews.R;
import com.example.mynews.data.retrofit.RetrofitService;
import com.example.mynews.data.model.search.Docs;
import com.example.mynews.data.model.search.SearchResult;
import com.example.mynews.ui.Article;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatterBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.threeten.bp.temporal.ChronoField.DAY_OF_MONTH;
import static org.threeten.bp.temporal.ChronoField.HOUR_OF_DAY;
import static org.threeten.bp.temporal.ChronoField.MINUTE_OF_HOUR;
import static org.threeten.bp.temporal.ChronoField.MONTH_OF_YEAR;
import static org.threeten.bp.temporal.ChronoField.NANO_OF_SECOND;
import static org.threeten.bp.temporal.ChronoField.SECOND_OF_MINUTE;
import static org.threeten.bp.temporal.ChronoField.YEAR;

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
            public void onResponse(@NonNull Call<SearchResult> call, @NonNull Response<SearchResult> response) {
                Log.d("courgette", "" + response);
                if(response.body() != null && response.body().response.meta.hits != 0) {
                    articleAdapter.setNewData(map(response));
                    viewFlipper.setDisplayedChild(DATA);
                } else {
                    viewFlipper.setDisplayedChild(NO_DATA);
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchResultActivity.this);
                    builder.setMessage("No Result").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create().show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResult> call, @NonNull Throwable t) {
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

                LocalDate date = LocalDate.parse(
                    docs.pubDate,
                    new DateTimeFormatterBuilder()
                        .appendValue(YEAR, 4)
                        .appendLiteral('-')
                        .appendValue(MONTH_OF_YEAR)
                        .appendLiteral('-')
                        .appendValue(DAY_OF_MONTH)
                        .appendLiteral('T')
                        .appendValue(HOUR_OF_DAY, 2)
                        .appendLiteral(':')
                        .appendValue(MINUTE_OF_HOUR, 2)
                        .optionalStart()
                        .appendLiteral(':')
                        .appendValue(SECOND_OF_MINUTE, 2)
                        .appendLiteral('+')
                        .appendValue(NANO_OF_SECOND, 4)
                        .toFormatter()
                );

                Article article = new Article(
                    imageUrl,
                    docs.headline.printHeadline,
                    docs.snippet,
                    date.toString(),
                    docs.webUrl
                );
                articleList.add(article);
            }
        }
        return articleList;
    }

    @Override
    public void onArticleClick(Article article) {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        // set toolbar color and/or setting custom actions before invoking build()
        // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        CustomTabsIntent customTabsIntent = builder.build();
        // and launch the desired Url with CustomTabsIntent.launchUrl()
        customTabsIntent.launchUrl(this, Uri.parse(article.getUrl()));
    }
}

package com.example.mynews.notification;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mynews.data.retrofit.NewYorkTimesAPI;
import com.example.mynews.R;
import com.example.mynews.data.retrofit.RetrofitService;
import com.example.mynews.data.model.search.SearchResult;
import com.example.mynews.notification.data.ArticleNumberDao;
import com.example.mynews.notification.data.DailyHits;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.IOException;

import retrofit2.Response;

public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        ArticleNumberDao dao = new ArticleNumberDao(getApplicationContext());

        NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext());

        String userInput = getInputData().getString(NotificationActivity.KEY_USER_INPUT);
        NewYorkTimesAPI api = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        try {
            Response<SearchResult> result = api.getSearchResponse(userInput,
                    "17890714",
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))).execute();
            if (result.body() != null) {
                int articleNumber = result.body().response.meta.hits;


                if (dao.getDailyHits() != null) {
                    int delta = articleNumber - dao.getDailyHits().getHitsNumber();
                    notificationHelper.displayNotification(getApplicationContext().getResources().getString(R.string.notification_message, delta));
                }

                dao.insertTodayHits(new DailyHits(result.body().response.meta.hits));
            }

            Log.i("call", "succes");
            return Result.success();

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("call", "fail");
            return Result.failure();
        }

    }
}

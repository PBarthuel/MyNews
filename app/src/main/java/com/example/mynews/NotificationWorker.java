package com.example.mynews;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mynews.model.data.search.SearchResult;

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

        String userInput = getInputData().getString(NotificationActivity.KEY_USER_INPUT);
        NewYorkTimesAPI api = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        try {
            Response<SearchResult> result = api.getSearchResponse(userInput,
                    "17890714",
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))).execute();
            if (result.body() != null) {
                int articleNumber = result.body().response.meta.hits;
            }

            Log.i("call", "succes");
            return Result.success();

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("call", "fail");
            return Result.failure();
        }

        //TODO AVEC UNE SEULE REQUETE COMPARER LES HITS DE LA VEILLE ET CEUX DU JOUR ET APRES FAIRE LE LINT (ANALYZE --> INSPECT CODE) puis pour finir regarder la compatibilité avec android avant 15
    }
}

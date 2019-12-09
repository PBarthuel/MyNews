package com.example.mynews;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mynews.model.data.search.SearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationWorker extends Worker {

    private Intent intent;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String userInput = getInputData().getString("user_input");
        NewYorkTimesAPI api = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        //api.getSearchResponse(userInput, null, null).execute();
        //TODO AVEC UNE SEULE REQUETE COMPARER LES HITS DE LA VEILLE ET CEUX DU JOUR ET APRES FAIRE LE LINT (ANALYZE --> INSPECT CODE) puis pour finir regarder la compatibilité avec android avant 15
        return null;
    }
}

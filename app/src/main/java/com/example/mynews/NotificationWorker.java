package com.example.mynews;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.io.IOException;

public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String userInput = getInputData().getString("USER_INPUT");
        NewYorkTimesAPI api = RetrofitService.getInstance().create(NewYorkTimesAPI.class);
        try {
            api.getSearchResponse(userInput,
                    LocalDate.now().minus(1, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))).execute();
            Log.i("call", "succes");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("call", "fail");

        }
        
        //TODO AVEC UNE SEULE REQUETE COMPARER LES HITS DE LA VEILLE ET CEUX DU JOUR ET APRES FAIRE LE LINT (ANALYZE --> INSPECT CODE) puis pour finir regarder la compatibilité avec android avant 15
        return null;
    }
}

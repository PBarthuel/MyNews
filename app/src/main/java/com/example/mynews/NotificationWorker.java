package com.example.mynews;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //TODO faire en sorte que tu recupère tout les article la premiere fois et que tu compare avec les nouveaux avec le getSearchResponse
        return null;
    }
}

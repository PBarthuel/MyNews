package com.example.mynews;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private Context context;

    private NotificationManager mManager;

    public NotificationHelper(Context context) {
        this.context = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public void displayNotification(String message) {
        Notification notification = new NotificationCompat.Builder(context, channelID)
                .setContentTitle("Come back !")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();
        getManager().notify(0, notification);
    }
}
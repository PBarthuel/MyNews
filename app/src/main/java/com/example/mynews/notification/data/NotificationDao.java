package com.example.mynews.notification.data;

import android.content.Context;
import android.content.SharedPreferences;

public class NotificationDao {

    private SharedPreferences sharedPreferences;
    private static String SHARED_PREFS = "sharedPrefs";

    public NotificationDao(Context context) {

        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

    }

    public void savedHits(int savedHits) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("saved_hits", savedHits);

        editor.apply();
        editor.commit();
    }

    public int loadHits() {
        return sharedPreferences.getInt("saved_hits", 0);
    }

    public void notificationEnabled (Boolean isEnabled) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("isEnabled", isEnabled);

        editor.apply();
        editor.commit();
    }

    public Boolean isEnabled() {
        return sharedPreferences.getBoolean("isEnabled", false);
    }
}

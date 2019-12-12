package com.example.mynews;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.concurrent.TimeUnit;

public class NotificationActivity extends AppCompatActivity {

    private TextView mTextView;
    private PeriodicWorkRequest saveRequest;
    public EditText editText;
    SectionsCustomView sectionsCustomView;
    private SearchManager searchManager = new SearchManager();
    //TODO regarder les test d'integration via le workmanager et android espresso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mTextView = findViewById(R.id.et_notification_activity);

        final Switch switchNotification = findViewById(R.id.switch_notification_activity);

        editText = findViewById(R.id.notification_et_user);

        sectionsCustomView = findViewById(R.id.cv_notification_checkbox);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updateTimeText();
                    startAlarm();
                } else {
                    cancelAlarm();
                    mTextView.setText("");
                }
            }
        });
    }

    private void updateTimeText() {
        String timeText = "Alarm set for: ";
        timeText += LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        mTextView.setText(timeText);
    }

    private void startAlarm() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        Data.Builder dataBuilder = new Data.Builder()
                .putString("user_input", searchManager.getLucene(editText.getText().toString(), sectionsCustomView.getSectionsSelected()));

        saveRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 1, TimeUnit.DAYS)
                        .setInputData(dataBuilder.build())
                        .setConstraints(constraints)
                        .build();

        WorkManager.getInstance(this)
                .enqueue(saveRequest);

    }

    private void cancelAlarm() {
        WorkManager.getInstance(this).cancelWorkById(saveRequest.getId());
    }
}

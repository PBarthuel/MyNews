package com.example.mynews.notification;

import android.annotation.SuppressLint;
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

import com.example.mynews.R;
import com.example.mynews.notification.data.ArticleNumberDao;
import com.example.mynews.search.SearchManager;
import com.example.mynews.search.SectionsCustomView;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.concurrent.TimeUnit;

public class NotificationActivity extends AppCompatActivity {

    public static final String KEY_USER_INPUT = "KEY_USER_INPUT";
    private TextView mTextView;
    private PeriodicWorkRequest saveRequest;
    public EditText editText;
    SectionsCustomView sectionsCustomView;
    private SearchManager searchManager = new SearchManager();
    ArticleNumberDao dao = new ArticleNumberDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mTextView = findViewById(R.id.et_notification_activity);

        final Switch switchNotification = findViewById(R.id.switch_notification_activity);

        editText = findViewById(R.id.notification_et_user);

        sectionsCustomView = findViewById(R.id.cv_notification_checkbox);

        Boolean notificationEnabled = dao.isNotificationEnabled();

        if (notificationEnabled != null && notificationEnabled) {
            switchNotification.setChecked(true);
        } else {
            switchNotification.setChecked(false);
        }

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dao.isNotificationEnabled(true);
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

    @SuppressLint("RestrictedApi")
    private void startAlarm() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        Data.Builder dataBuilder = new Data.Builder()
                .putString(KEY_USER_INPUT, searchManager.getLucene(editText.getText().toString(), sectionsCustomView.getSectionsSelected()));

        saveRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 1, TimeUnit.DAYS)
                        .setInitialDelay(1, TimeUnit.DAYS)
                        .setInputData(dataBuilder.build())
                        .setConstraints(constraints)
                        .build();

        WorkManager.getInstance(this)
                .enqueue(saveRequest);

    }

    private void cancelAlarm() {
        dao.isNotificationEnabled(false);
        WorkManager.getInstance(this).cancelWorkById(saveRequest.getId());
    }
}

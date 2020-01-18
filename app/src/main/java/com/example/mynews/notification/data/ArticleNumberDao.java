package com.example.mynews.notification.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import org.threeten.bp.LocalDate;

import java.util.UUID;

public class ArticleNumberDao extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "hits_history";
    private static final String TABLE_NAME_ENABLED = "enabled_history";
    private static final String COLUMN_HITS = "hits";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ENABLED = "enabled";

    public ArticleNumberDao(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        disableWal(db);

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_HITS + " INTEGER, " +
                COLUMN_DATE + " TEXT) ");

        db.execSQL("CREATE TABLE " + TABLE_NAME_ENABLED + "(" +
                COLUMN_ENABLED + " BYTE) ");

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        disableWal(db);

        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void disableWal(SQLiteDatabase db) {

        // Disables WAL. We don't need such a dev-unfriendly feature on a simple project.
        // With this, .wal and .smh files are no longer generated, and the db is easy to extract & open
        //
        // Source : https://www.sqlite.org/wal.html
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging();
        }
    }

    public void isNotificationEnabled(boolean switchState) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ENABLED, switchState);

        if (isNotificationEnabled() != null) {
            getWritableDatabase().update(TABLE_NAME_ENABLED, contentValues, null, null);
        } else {
            getWritableDatabase().insert(TABLE_NAME_ENABLED, null, contentValues);
        }
    }

    @Nullable
    public Boolean isNotificationEnabled() {

        Cursor cursor = getReadableDatabase().query(TABLE_NAME_ENABLED,
                null,
                null,
                null,
                null,
                null,
                null,
                "1");

        Boolean isEnabled;

        if (cursor.moveToNext()) {
            isEnabled = cursor.getInt(cursor.getColumnIndex(COLUMN_ENABLED)) != 0;
        } else {
            isEnabled = null;
        }

        cursor.close();

        return isEnabled;
    }
}

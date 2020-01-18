package com.example.mynews.notification.data;

import android.content.ContentValues;
import android.content.Context;
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
    private static final String TABLE_NAME_ID = "id_history";
    private static final String COLUMN_HITS = "hits";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ENABLED = "enabled";
    private static final String COLUMN_ID = "id";

    public ArticleNumberDao(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        disableWal(db);

        db.execSQL(" CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_HITS + " INTEGER," +
                " " + COLUMN_DATE + " TEXT ) ");

        db.execSQL(" CREATE TABLE " + TABLE_NAME_ENABLED + " ( " + COLUMN_ENABLED + " BYTE ) ");

        db.execSQL(" CREATE TABLE " + TABLE_NAME_ID + " ( " + COLUMN_ID + " TEXT ) ");
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

    public void insertTodayHits(DailyHits dailyHits) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_HITS, dailyHits.getHitsNumber());
        contentValues.put(COLUMN_DATE, LocalDate.now().toString());

        if (getDailyHits() != null) {
            // We need to escape the parameter "contentValues.get(COLUMN_DATE)" because if it's not
            // explicitly considered as a String, I guess maths get in and try to actually compute
            // the date, like 2019-02-04 would result as 2019 - 02 - 04 which is, mathematically,
            // equal to 2013.
            String whereClause = COLUMN_DATE + " = \"" + contentValues.get(COLUMN_DATE) + "\"";

            getWritableDatabase().update(TABLE_NAME, contentValues, whereClause, null);
        } else {
            getWritableDatabase().insert(TABLE_NAME, null, contentValues);
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

    public void insertNotificationId (String id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);

        if (id != null && !id.isEmpty()) {
            getWritableDatabase().update(TABLE_NAME_ID, contentValues, null, null);
        }else {
            getWritableDatabase().insert(TABLE_NAME_ID, null, contentValues);
        }
    }

    @Nullable
    public DailyHits getDailyHits() {

        Cursor cursor = getReadableDatabase().query(TABLE_NAME,
                null,
                COLUMN_DATE + " = \"" + LocalDate.now().toString() + "\"",
                null,
                null,
                null,
                COLUMN_DATE + " DESC",
                "1");

        if (cursor.moveToFirst()) {
            int hits = cursor.getInt(cursor.getColumnIndex(COLUMN_HITS));

            cursor.close();

            return new DailyHits(hits);
        } else {
            return null;
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

    public String getIdNotification() {

        Cursor cursor = getReadableDatabase().query(TABLE_NAME_ID,
                null,
                null,
                null,
                null,
                null,
                null,
                "1");

        if (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));

            cursor.close();

            return id;
        } else {
            return null;
        }
    }
}

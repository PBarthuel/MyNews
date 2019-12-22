package com.example.mynews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import org.threeten.bp.LocalDate;

public class ArticleNumberDao extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "hits_history";
    private static final String COLUMN_HITS = "hits";
    private static final String COLUMN_DATE = "date";

    public ArticleNumberDao(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        disableWal(db);

        db.execSQL( " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_HITS + " INTEGER," +
                " " + COLUMN_DATE + " TEXT ) ");
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
}

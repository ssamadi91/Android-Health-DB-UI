package com.example.shamool.HeartRate;

/**
 * Created by Shamool on 10/31/16.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import com.example.shamool.HeartRate.UserData;
import com.example.shamool.HeartRate.HRContract.HREntry;

public class HeartRateDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "HeartRateDbHelper";
    // Database Info
    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "patients.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     *
     * Constructs a new instance of {@link HeartRateDbHelper}.
     *
     * @param context of the app
     */
    public HeartRateDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HeartRate_TABLE = "CREATE TABLE " + HREntry.TABLE_NAME + " ("
                + HREntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HREntry.COLUMN_TEMPERATURE + " INTEGER NOT NULL, "
                + HREntry.COLUMN_PATIENT_GENDER + " INTEGER NOT NULL, "
                + HREntry.COLUMN_HEART_RATE + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HeartRate_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

     /*
   Insert a  user detail into database
   */

    public void insertUserDetail(UserData userData) {

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(HREntry.COLUMN_TEMPERATURE, userData.body_temp);
            values.put(HREntry.COLUMN_PATIENT_GENDER, userData.gender);
            values.put(HREntry.COLUMN_HEART_RATE, userData.heart_rate);

            db.insert(HREntry.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        } finally {

            db.endTransaction();

        }
    }
       /*
   fetch all data from UserTable
    */

    public List<UserData> getAllUser() {

        List<UserData> usersdetail = new ArrayList<>();

        String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + HREntry.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    UserData userData = new UserData();
                    userData.body_temp = cursor.getString(cursor.getColumnIndex(HREntry.COLUMN_TEMPERATURE));
                    userData.gender = cursor.getString(cursor.getColumnIndex(HREntry.COLUMN_PATIENT_GENDER));
                    userData.heart_rate = cursor.getString(cursor.getColumnIndex(HREntry.COLUMN_HEART_RATE));

                    usersdetail.add(userData);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return usersdetail;
    }

    /**
     * Delete single row from UserTable
     */
    void deleteRow(String del_item) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL("DELETE FROM " + HREntry.TABLE_NAME + " WHERE "+HREntry.COLUMN_HEART_RATE+"='"+del_item+"'");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, "Error while trying to delete  users detail");
        } finally {
            db.endTransaction();

        }
    }
}

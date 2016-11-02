package com.example.shamool.HeartRate;

/**
 * Created by Shamool on 10/31/16.
 */
import android.provider.BaseColumns;

public class HRContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HRContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class HREntry implements BaseColumns {

        public final static String TABLE_NAME = "HeartRate";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TEMPERATURE ="BodyTemp";
        public final static String COLUMN_PATIENT_GENDER = "gender";
        public final static String COLUMN_HEART_RATE = "HeartRate";

    }

}


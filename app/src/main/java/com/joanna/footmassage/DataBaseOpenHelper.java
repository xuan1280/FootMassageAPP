package com.joanna.footmassage;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.joanna.footmassage.Secret.DATABASE_NAME;
import static com.joanna.footmassage.modles.entities.SqliteTableAttribute.*;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public DataBaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
        createRecordTable(db);
        createSurveyTable(db);
        createPressureTable(db);

    }

    private void createUserTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS User ("+ USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, " +
                USER_ACCOUNT + " TEXT, " +
                USER_PASSWORD + " TEXT, " +
                USER_AGE + " INTEGER, " +
                USER_GENDER + " INTEGER)");
    }

    private void createRecordTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Record ("+ RECORD_UID +" INTEGER, " +
                RECORD_RID + " INTEGER, " +
                RECORD_RESULT + " TEXT, " +
                "FOREIGN KEY ("+RECORD_UID+") REFERENCES User("+USER_ID+"), " +
                "PRIMARY KEY ("+RECORD_UID+", "+RECORD_RID+"))");
    }

    private void createSurveyTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Survey ("+ SURVEY_ID +" INTEGER, " +
                SURVEY_QUESTION1 + " INTEGER, " +
                SURVEY_QUESTION2 + " INTEGER, " +
                SURVEY_QUESTION3 + " INTEGER, " +
                SURVEY_QUESTION4 + " INTEGER, " +
                SURVEY_QUESTION5 + " INTEGER, " +
                SURVEY_QUESTION6 + " INTEGER, " +
                SURVEY_QUESTION7 + " INTEGER, " +
                SURVEY_QUESTION8 + " INTEGER, " +
                SURVEY_QUESTION9 + " INTEGER, " +
                SURVEY_QUESTION10 + " INTEGER, " +
                SURVEY_QUESTION11 + " INTEGER, " +
                SURVEY_QUESTION12 + " INTEGER, " +
                "FOREIGN KEY ("+ SURVEY_ID +") REFERENCES User(" + USER_ID + "))");
    }

    private void createPressureTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Pressure ("+ PRESSURE_ID +" INTEGER PRIMARY KEY," +
                PRESSURE_RID +  " INTGER, " +
                PRESSURE_STOMACH1 + " INTGER, " +
                PRESSURE_STOMACH2 + " INTGER, " +
                PRESSURE_STOMACH3 + " INTGER, " +
                PRESSURE_LIVER1 + " INTEGER, " +
                PRESSURE_LIVER2 + " INTGER, " +
                PRESSURE_LIVER3 + " INTGER, " +
                PRESSURE_LIVER4 + " INTGER, " +
                PRESSURE_LIVER5 + " INTGER, " +
                PRESSURE_PAINFUL + " INTGER, " +
                PRESSURE_TIME + " DATE, " +
                "FOREIGN KEY ("+ PRESSURE_RID +") REFERENCES Record(" + RECORD_RID + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

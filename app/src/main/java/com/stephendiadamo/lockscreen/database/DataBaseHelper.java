package com.stephendiadamo.lockscreen.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stephendiadamo on 2017-04-29.
 */


public class DataBaseHelper extends SQLiteOpenHelper {

    // Tables
    public static final String TABLE_PEOPLE = "people";
    public static final String KEY_ID = "id";
    public static final String IS_DELETED = "is_deleted";


    public String last_name;
    public String company;
    public String email;
    public String phone;
    public String dateOfBirth;
    public String address;

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String COMPANY = "company";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String ADDRESS = "address";


    private static final String CREATE_PEOPLE_TABLE = "CREATE TABLE " + TABLE_PEOPLE +
            "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIRST_NAME + " TEXT, " +
            LAST_NAME + " TEXT, " +
            COMPANY + " TEXT, " +
            PHONE + " TEXT, " +
            EMAIL + " TEXT, " +
            DATE_OF_BIRTH + " TEXT, " +
            ADDRESS + " TEXT, " +
            IS_DELETED + " INTEGER" +
            ")";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lockout";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PEOPLE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}

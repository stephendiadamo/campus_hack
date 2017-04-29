package com.stephendiadamo.lockscreen;

/**
 * Created by stephendiadamo on 2017-04-29.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stephendiadamo.lockscreen.data_objects.Person;
import com.stephendiadamo.lockscreen.database.DataBaseHelper;

import java.util.ArrayList;

public class PersonOperations {
    private final String[] BOOK_TABLE_COLUMNS = {
            DataBaseHelper.KEY_ID,
            DataBaseHelper.FIRST_NAME,
            DataBaseHelper.LAST_NAME,
            DataBaseHelper.COMPANY,
            DataBaseHelper.EMAIL,
            DataBaseHelper.PHONE,
            DataBaseHelper.DATE_OF_BIRTH,
            DataBaseHelper.ADDRESS
    };

    private final DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public PersonOperations(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void addPerson(Person person) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.FIRST_NAME, person.first_name);
        values.put(DataBaseHelper.LAST_NAME, person.last_name);
        values.put(DataBaseHelper.COMPANY, person.company);
        values.put(DataBaseHelper.EMAIL, person.email);
        values.put(DataBaseHelper.PHONE, person.phone);
        values.put(DataBaseHelper.DATE_OF_BIRTH, person.first_name);
        values.put(DataBaseHelper.ADDRESS, person.first_name);

        db.insert(DataBaseHelper.TABLE_PEOPLE, null, values);
        db.close();
    }

    public ArrayList<Person> getAllPeople() {
        ArrayList<Person> people = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        String query = String.format("SELECT * FROM %s",
                DataBaseHelper.TABLE_PEOPLE);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Person person = parsePerson(cursor);
                people.add(person);
            } while (cursor.moveToNext());
            cursor.close();
        }
        cursor.close();

        db.close();
        return people;
    }


    private Person parsePerson(Cursor cursor) {
        return new Person(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));
    }
}




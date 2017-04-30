package com.stephendiadamo.lockscreen;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stephendiadamo.lockscreen.data_objects.Person;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by stephendiadamo on 2017-04-29.
 */

public class ContactsAdapter extends ArrayAdapter<Person> {

    private final Context context;
    private final int layoutResourceID;
    private final ArrayList<Person> people;

    public ContactsAdapter(@NonNull Context context, ArrayList<Person> people) {
        super(context, R.layout.contact_row, people);
        this.context = context;
        this.layoutResourceID = R.layout.contact_row;
        this.people = people;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ContactHolder contactHolder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceID, parent, false);
            contactHolder = new ContactHolder();

            contactHolder.firstLetter = (TextView) row.findViewById(R.id.first_letter);
            contactHolder.name = (TextView) row.findViewById(R.id.contact_name);
            contactHolder.letterHolder = row.findViewById(R.id.letter_holder);

            row.setTag(contactHolder);
        } else {
            contactHolder = (ContactHolder) row.getTag();
        }
        Person person = people.get(position);
        contactHolder.name.setText(person.last_name + ", " + person.first_name);
        contactHolder.firstLetter.setText(person.first_name.substring(0, 1));

        int[] androidColors = context.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        GradientDrawable bgShape = (GradientDrawable) contactHolder.letterHolder.getBackground();
        bgShape.setColor(randomAndroidColor);

        return row;
    }


    static class ContactHolder {
        TextView firstLetter;
        TextView name;
        View letterHolder;
    }

}

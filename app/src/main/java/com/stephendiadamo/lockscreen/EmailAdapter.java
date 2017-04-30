package com.stephendiadamo.lockscreen;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stephendiadamo.lockscreen.data_objects.Email;
import com.stephendiadamo.lockscreen.data_objects.Person;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by stephendiadamo on 2017-04-30.
 */

public class EmailAdapter extends ArrayAdapter<Email> {

    private final Context context;
    private final int layoutResourceID;
    private final ArrayList<Email> emails;

    public EmailAdapter(@NonNull Context context, ArrayList<Email> emails) {
        super(context, R.layout.contact_row, emails);
        this.context = context;
        this.layoutResourceID = R.layout.contact_row;
        this.emails = emails;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        EmailHolder emailHolder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceID, parent, false);
            emailHolder = new EmailHolder();

            emailHolder.firstLetter = (TextView) row.findViewById(R.id.email_first_letter);
            emailHolder.name = (TextView) row.findViewById(R.id.email_name);
            emailHolder.subject = (TextView) row.findViewById(R.id.email_subject);
            emailHolder.letterHolder = row.findViewById(R.id.letter_holder);

            row.setTag(emailHolder);
        } else {
            emailHolder = (EmailHolder) row.getTag();
        }
        Email email = emails.get(position);
        emailHolder.name.setText(email.from);
        emailHolder.firstLetter.setText(email.from.substring(0, 1));
        emailHolder.subject.setText(email.subject);

        int[] androidColors = context.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        GradientDrawable bgShape = (GradientDrawable) emailHolder.letterHolder.getBackground();
        bgShape.setColor(randomAndroidColor);

        return row;
    }


    static class EmailHolder {
        TextView firstLetter;
        TextView name;
        TextView subject;
        View letterHolder;
    }

}

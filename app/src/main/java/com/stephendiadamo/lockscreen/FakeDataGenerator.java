package com.stephendiadamo.lockscreen;

import com.stephendiadamo.lockscreen.data_objects.Email;

import java.util.ArrayList;

/**
 * Created by stephendiadamo on 2017-04-28.
 */

public class FakeDataGenerator {

    public FakeDataGenerator() {
    }

    public ArrayList<Email> generateFakeEmails() {
        ArrayList<Email> emails = new ArrayList<>();
        Email e = new Email("hey", "hey", "hey", "hey", "hey");
        emails.add(e);
        return emails;
    }

}

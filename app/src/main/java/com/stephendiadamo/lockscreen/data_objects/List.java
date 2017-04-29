package com.stephendiadamo.lockscreen.data_objects;

/**
 * Created by stephendiadamo on 2017-04-28.
 */

public class List {
    public String subject;
    public List list;

    public List(String subject, List list) {
        this.subject = subject;
        this.list = list;
    }
}

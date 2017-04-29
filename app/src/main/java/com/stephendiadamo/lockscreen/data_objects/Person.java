package com.stephendiadamo.lockscreen.data_objects;

public class Person {
    public String first_name;
    public String last_name;
    public String company;
    public String email;
    public String phone;
    public String dateOfBirth;
    public String address;

    public Person(String first_name, String last_name, String company, String email, String phone, String dateOfBirth, String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}

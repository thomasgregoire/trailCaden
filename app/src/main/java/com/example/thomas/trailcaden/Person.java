package com.example.thomas.trailcaden;

/**
 * Created by Thomas on 11/02/2018.
 */

public class Person {

    private String name, firstname, date;

    public Person(){

    }

    public Person(String name, String firstname, String date) {
        this.name = name;
        this.firstname = firstname;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

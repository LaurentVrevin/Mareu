package fr.laurentvrevin.mareu.model;


import java.io.Serializable;
import java.util.ArrayList;

public class Employees implements Serializable {

    private long id;

    private String firstname;

    private String function;

    private String email;

    public Employees(long id, String firstname, String function, String email) {

        this.id = id;
        this.firstname = firstname;
        this.function = function;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String Firstname) {
        this.firstname = firstname;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String funtion) {
        this.function = funtion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

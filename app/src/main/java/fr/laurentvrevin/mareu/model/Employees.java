package fr.laurentvrevin.mareu.model;

public class Employees {

    private long id;

    private String firstname;

    private String familyname;

    private String function;


    private String email;

    public Employees(long id, String firstname, String familyname, String funtion, String email) {

        this.id = id;
        this.firstname = firstname;
        this.familyname = familyname;
        this.function = funtion;
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

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
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

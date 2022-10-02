package fr.laurentvrevin.mareu.model;

import java.util.ArrayList;

public class Meetings {

    private String meetingname;

    private String startime;

    private String roomname;

    private ArrayList<Employees> employees;

    public Meetings(String meetingname, String startime, String roomname, ArrayList<Employees> employees) {

        this.meetingname = meetingname;
        this.startime = startime;
        this.roomname = roomname;
        this.employees = employees;    }


    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public ArrayList<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employees> employees) {
        this.employees = employees;
    }
}

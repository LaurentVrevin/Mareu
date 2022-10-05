package fr.laurentvrevin.mareu.model;

import java.util.ArrayList;

public class Meetings {

    private String meetingname;

    private String startime;

    private String roomname;

    private String  date_meeting;

    private ArrayList<Employees> employeesMails;

    public Meetings(String meetingname, String startime, String roomname, String datemeeting, ArrayList<Employees> employeesMails) {

        this.meetingname = meetingname;
        this.startime = startime;
        this.roomname = roomname;
        this.date_meeting = datemeeting;
        this.employeesMails = employeesMails;    }


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

    public String getDate_meeting() {
        return date_meeting;
    }

    public void setDate_meeting(String date_meeting) {
        this.date_meeting = date_meeting;
    }

    public ArrayList<Employees> getEmployeesMails() {
        return employeesMails;
    }

    public void setEmployeesMails(ArrayList<Employees> employeesMails) {
        this.employeesMails = employeesMails;
    }
}

package fr.laurentvrevin.mareu.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Meetings {

    private String meetingname;

    private String roomname;

    private Calendar dateMeeting;

    private ArrayList<Employees> employeesMails;

    public Meetings(String meetingName, String roomName, Calendar dateMeeting, ArrayList<Employees> employeesMails) {

        this.meetingname = meetingName;
        this.roomname = roomName;
        this.dateMeeting = dateMeeting;
        this.employeesMails = employeesMails;    }


    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Calendar getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(Calendar dateMeeting) {
        this.dateMeeting = dateMeeting;
    }

    public ArrayList<Employees> getEmployeesMails() {
        return employeesMails;
    }

    public void setEmployeesMails(ArrayList<Employees> employeesMails) {
        this.employeesMails = employeesMails;
    }
}

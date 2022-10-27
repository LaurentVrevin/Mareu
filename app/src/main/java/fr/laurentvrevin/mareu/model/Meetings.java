package fr.laurentvrevin.mareu.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Meetings {

    private String meetingname;

    private String roomname;

    private Calendar dateMeeting;

    private ArrayList<Employees> employeesMails;

    public Meetings(String meetingName, String roomName, Calendar dateMeeting, ArrayList<Employees> employeesMails) {

        this.meetingname = meetingName;
        this.roomname = roomName;
        this.dateMeeting = dateMeeting;
        this.employeesMails = employeesMails;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meetings meetings = (Meetings) o;
        return meetingname.equals(meetings.meetingname) && roomname.equals(meetings.roomname) && dateMeeting.equals(meetings.dateMeeting) && employeesMails.equals(meetings.employeesMails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingname, roomname, dateMeeting, employeesMails);
    }
}

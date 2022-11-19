package fr.laurentvrevin.mareu.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Meeting {

    private String meetingname;

    private String roomname;

    private Calendar dateMeeting;
    private Calendar endTimeMeeting;

    private ArrayList<Employee> employeesMails;
    private String roomColor;

    public Meeting(String meetingColor, String meetingName, String roomName, Calendar dateMeeting, Calendar endTimeMeeting, ArrayList<Employee> employeesMails) {
        this.roomColor = meetingColor;
        this.meetingname = meetingName;
        this.roomname = roomName;
        this.dateMeeting = dateMeeting;
        this.endTimeMeeting = endTimeMeeting;
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

    public Calendar getEndTimeMeeting() {
        return endTimeMeeting;
    }

    public void setEndTimeMeeting(Calendar endTimeMeeting) {
        this.endTimeMeeting = endTimeMeeting;
    }

    public ArrayList<Employee> getEmployeesMails() {
        return employeesMails;
    }

    public void setEmployeesMails(ArrayList<Employee> employeesMails) {
        this.employeesMails = employeesMails;
    }

    public String getRoomColor() {
        return roomColor;
    }

    public void setRoomColor(String roomColor) {
        this.roomColor = roomColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meetings = (Meeting) o;
        return meetingname.equals(meetings.meetingname) && roomname.equals(meetings.roomname) && dateMeeting.equals(meetings.dateMeeting)
                && endTimeMeeting.equals(meetings.endTimeMeeting) && employeesMails.equals(meetings.employeesMails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingname, roomname, dateMeeting, endTimeMeeting, employeesMails);
    }
}

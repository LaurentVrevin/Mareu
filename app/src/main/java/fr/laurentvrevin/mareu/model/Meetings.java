package fr.laurentvrevin.mareu.model;

public class Meetings {


    private String meetingname;

    private String startime;

    private String roomname;

    private String email;

    public Meetings( String meetingname, String startime, String roomname, String email) {


        this.meetingname = meetingname;
        this.startime = startime;
        this.roomname = roomname;
        this.email = email;
    }



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

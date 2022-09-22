package fr.laurentvrevin.mareu.model;

public class Meetings {
    private long id;

    private String meetingname;

    private long startime;

    private String roomname;

    private String email;

    public Meetings(long id, String meetingname, long startime, String roomname, String email) {

        this.id = id;
        this.meetingname = meetingname;
        this.startime = startime;
        this.roomname = roomname;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public long getStartime() {
        return startime;
    }

    public void setStartime(long startime) {
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

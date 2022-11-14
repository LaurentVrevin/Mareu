package fr.laurentvrevin.mareu.service;

import java.util.Date;
import java.util.List;

import fr.laurentvrevin.mareu.model.Meeting;
import fr.laurentvrevin.mareu.model.Room;

public interface MareuApiService {


    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Meeting> getMeetingsByDay(Date date);

    List<Meeting> getMeetingsByRoom(Room rooms);


}

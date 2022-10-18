package fr.laurentvrevin.mareu.service;

import java.util.Date;
import java.util.List;

import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.model.Rooms;

public interface MareuApiService {


    List<Meetings> getMeetings();

    void createMeeting(Meetings meeting);

    void deleteMeeting(Meetings meeting);

    List<Meetings> getMeetingsByDay(Date date);

    List<Meetings> getMeetingsByRoom(Rooms rooms);


}

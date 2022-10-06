package fr.laurentvrevin.mareu.service;

import java.util.List;

import fr.laurentvrevin.mareu.model.Employees;
import fr.laurentvrevin.mareu.model.Meetings;

public interface MareuApiService {

    List<Employees> getEmployees();

    List<Meetings> getMeetings();

    void createMeeting(Meetings meeting);

    void deleteMeeting(Meetings meeting);

    void getAllMeeting(Meetings meeting);

    void getMeetingByDay(Meetings meeting);

    void getMeetingByRoom(Meetings meeting);

}

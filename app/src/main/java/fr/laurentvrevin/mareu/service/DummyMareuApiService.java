package fr.laurentvrevin.mareu.service;


import java.util.List;

import fr.laurentvrevin.mareu.model.Employees;
import fr.laurentvrevin.mareu.model.Meetings;

public class DummyMareuApiService implements MareuApiService{
    private final List<Employees> mEmployees = DummyEmployeesGenerator.generateEmployees();
    private final List<Meetings> mMeetings = MeetingListGenerator.generateMeeting();


    //private final List<Employees> selectedEmployees = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employees> getEmployees() {
        return mEmployees;
    }
    @Override
    public List<Meetings> getMeetings() {
        return mMeetings;
    }

    @Override
    public void addMeeting(Meetings meeting) {

    }

    @Override
    public void deleteMeeting(Meetings meeting) {

    }

}


package fr.laurentvrevin.mareu.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.laurentvrevin.mareu.model.Employees;
import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.model.Rooms;

public class DummyMareuApiService implements MareuApiService{
    private final List<Meetings> mMeetings = MeetingListGenerator.generateMeeting();
    private final List<Employees> mEmployees = DummyEmployeesGenerator.generateEmployees();



    //private final List<Employees> selectedEmployees = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meetings> getMeetings() {
        return mMeetings;
    }
    @Override
    public List<Employees> getEmployees() {
        return mEmployees;
    }


    @Override
    public void createMeeting(Meetings meeting) {
        mMeetings.add(meeting);

    }

    @Override
    public void deleteMeeting(Meetings meeting) {
        mMeetings.remove(meeting);

    }

    @Override
    public List<Meetings> getMeetingsByDay(Date date) {
        List<Meetings> meetingsByDateFiltered = new ArrayList<>();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
            for (int i =0; i<mMeetings.size();i++){
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(mMeetings.get(i).getDateMeeting().getTime());
            boolean sameDay = calendar1.get(Calendar.DAY_OF_YEAR)==calendar2.get(Calendar.DAY_OF_YEAR) &&
                    calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR);
            if (sameDay) meetingsByDateFiltered.add(mMeetings.get(i));
        }
        return meetingsByDateFiltered;


    }

    @Override
    public List<Meetings> getMeetingsByRoom(Rooms room) {
        List<Meetings> meetingsByRoomsFiltered = new ArrayList<>();
        for (Meetings roomFiltered:mMeetings){
            boolean sameRoom = roomFiltered.getRoomname()==room.getName();
            if (sameRoom) meetingsByRoomsFiltered.add(roomFiltered);
        }
        return meetingsByRoomsFiltered;
    }


}


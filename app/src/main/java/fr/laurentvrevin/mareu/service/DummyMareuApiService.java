package fr.laurentvrevin.mareu.service;


import java.util.ArrayList;
import java.util.Calendar;
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
    public List<Meetings> getMeetingByDay(Calendar calendar) {
        List<Meetings> meetingsByDateFiltered = new ArrayList<>();
        calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        for (Meetings m:mMeetings){
            boolean sameDay = m.getDateMeeting().get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR) &&
                    m.getDateMeeting().get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
            if (sameDay) meetingsByDateFiltered.add(m);
        }
        return meetingsByDateFiltered;
        /**for (int i =0; i<mMeetings.size();i++){
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(mMeetings.get(i).getDateMeeting().getTime());
            boolean sameDay = calendar1.get(Calendar.DAY_OF_YEAR)==calendar2.get(Calendar.DAY_OF_YEAR) &&
                    calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR);
            if (sameDay) meetingsByDateFiltered.add(mMeetings.get(i));
        }
        return meetingsByDateFiltered;*/

    }

    @Override
    public List<Meetings> getMeetingByRoom(Rooms rooms) {
        List<Meetings> meetingsByRoomsFiltered = new ArrayList<>();
        for (Meetings r:mMeetings){
            Boolean sameRoom = r.getRoomname()==rooms.getName();
            if (sameRoom) meetingsByRoomsFiltered.add(r);
        }
        return meetingsByRoomsFiltered;
    }


}


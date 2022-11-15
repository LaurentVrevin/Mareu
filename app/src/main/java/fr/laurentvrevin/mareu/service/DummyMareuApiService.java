package fr.laurentvrevin.mareu.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.laurentvrevin.mareu.model.Meeting;
import fr.laurentvrevin.mareu.model.Room;

public class DummyMareuApiService implements MareuApiService {
    private final List<Meeting> mMeetings = new ArrayList<>();

    public DummyMareuApiService() {
        mMeetings.addAll(DummyMeetingListGenerator.generateMeeting());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }


    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);

    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);

    }

    @Override
    public List<Meeting> getMeetingsByDay(Date date) {
        List<Meeting> meetingsByDateFiltered = new ArrayList<>();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        for (int i = 0; i < mMeetings.size(); i++) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(mMeetings.get(i).getDateMeeting().getTime());
            boolean sameDay = calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR) &&
                    calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
            if (sameDay) meetingsByDateFiltered.add(mMeetings.get(i));
        }
        return meetingsByDateFiltered;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(Room room) {
        List<Meeting> meetingsByRoomsFiltered = new ArrayList<>();
        for (Meeting roomFiltered : mMeetings) {
            boolean sameRoom = roomFiltered.getRoomname() == room.getName();
            if (sameRoom) meetingsByRoomsFiltered.add(roomFiltered);
        }
        return meetingsByRoomsFiltered;
    }
}


package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.laurentvrevin.mareu.model.Meetings;

public class MeetingListGenerator {
    public static List<Meetings> sDummyMeetings = new ArrayList<>(Arrays.asList());
    static List<Meetings> generateMeeting(){return new ArrayList<>(sDummyMeetings);}
}

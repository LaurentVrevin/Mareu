package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import fr.laurentvrevin.mareu.model.Meeting;

public class MeetingListGenerator {
private Calendar mcalendar = Calendar.getInstance();

    public static List<Meeting> DUMMY_MEETING = new ArrayList<>(Arrays.asList(
            new Meeting("#E9D0C6","reu01", "Oro Jakson", Calendar.getInstance(), DummyEmployeesGenerator.DUMMY_EMPLOYEES),
            new Meeting("#9ABCA4","reu02","Thousand Sunny", Calendar.getInstance(), DummyEmployeesGenerator.DUMMY_EMPLOYEES)
    ));
    static List<Meeting> generateMeeting(){return new ArrayList<>(DUMMY_MEETING);}
}

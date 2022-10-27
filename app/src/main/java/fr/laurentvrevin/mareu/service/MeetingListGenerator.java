package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import fr.laurentvrevin.mareu.Utils;
import fr.laurentvrevin.mareu.model.Employees;
import fr.laurentvrevin.mareu.model.Meetings;

public class MeetingListGenerator {
private Calendar mcalendar = Calendar.getInstance();

    public static List<Meetings> DUMMY_MEETING = new ArrayList<>(Arrays.asList(
            new Meetings("reu01", "Oro Jakson", Calendar.getInstance(), DummyEmployeesGenerator.DUMMY_EMPLOYEES),
            new Meetings("reu02","Thousand Sunny", Calendar.getInstance(), DummyEmployeesGenerator.DUMMY_EMPLOYEES)
    ));
    static List<Meetings> generateMeeting(){return new ArrayList<>(DUMMY_MEETING);}
}

package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.laurentvrevin.mareu.model.Meetings;

public class MeetingListGenerator {
    public static List<Meetings> DUMMY_MEETING = new ArrayList<>(Arrays.asList(
            //new Meetings( "refaire la gueule", "2h00","salle de réunion","coucou@jojo.fr")
    ));
    static List<Meetings> generateMeeting(){return new ArrayList<>(DUMMY_MEETING);}
}

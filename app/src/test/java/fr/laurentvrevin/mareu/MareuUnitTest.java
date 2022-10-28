package fr.laurentvrevin.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static fr.laurentvrevin.mareu.service.MeetingListGenerator.DUMMY_MEETING;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.model.Meeting;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.MareuApiService;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class MareuUnitTest {

    private MareuApiService testservice;
    private ArrayList<Meeting> testMeetingList;
    private Calendar calendarForTest = Calendar.getInstance();

    private Meeting meeting1;
    private Meeting meeting2;
    private Meeting meeting3;
    private Meeting meeting4;



    @Before
    public void setup(){
        testservice = DI.getNewInstanceApiService();
        calendarForTest.set(2022,10,27,10,30);
    }


    @Test
    public void test_CreateMeetingWithSuccess(){
        assertEquals(0, testservice.getMeetings().size());
        //On donne pour valeur à expectedMeeting, le paramètre DUMMY_MEETING
        testservice.createMeeting(meeting1);
        testservice.createMeeting(meeting2);
        //bien penser à creer les meeting
        List<Meeting> expectedMeeting = Arrays.asList(meeting1, meeting2);
        //On check que meeting est bien récupéré dans n'importe quel ordre
        MatcherAssert.assertThat(testservice.getMeetings(), IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));

    }

    @Test
    public void test_GetMeetingsByDayWithSuccess(){
        //tester sur un jour avec des meeting
        //tester sur un jour ou pas de meeting
        //REMPLACER TOUS LES ADDALL
        Calendar calendarForFilter = Calendar.getInstance();
        calendarForFilter.set(2022, 10, 25, 10,30);
        //List<Meetings> meeting = testservice.getMeetings();
        testservice.getMeetings().addAll(DUMMY_MEETING);
        List<Meeting> meetingFilteredByDate = testservice.getMeetingsByDay(calendarForFilter.getTime());
        assertTrue(meetingFilteredByDate.size()==0);

    }
    @Test
    public void test_GetMeetingsByRoomWithSuccess(){
        Rooms roomForFilter = new Rooms("Thousand Sunny");
        //List<Meetings> meeting = testservice.getMeetings();
        testservice.getMeetings().addAll(DUMMY_MEETING);
        List<Meeting>meetingFilteredByRoom = testservice.getMeetingsByRoom(roomForFilter);
        assertTrue(meetingFilteredByRoom.size()==1);

    }
    @Test
    public void test_DeleteMeetingWithSuccess(){
        List<Meeting> meeting = testservice.getMeetings();

        assertEquals(2, testservice.getMeetings().size());
        Meeting meetingToDelete = testservice.getMeetings().get(0);
        testservice.deleteMeeting(meetingToDelete);
        assertFalse(testservice.getMeetings().contains(meetingToDelete));
        assertEquals(1,testservice.getMeetings().size());
    }
}
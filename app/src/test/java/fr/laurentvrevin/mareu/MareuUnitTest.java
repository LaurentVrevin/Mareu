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
import fr.laurentvrevin.mareu.service.DummyEmployeesGenerator;
import fr.laurentvrevin.mareu.service.MareuApiService;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class MareuUnitTest {

    private MareuApiService testservice;
    private ArrayList<Meeting> testMeetingList;
    private final Calendar calendarForTest01 = Calendar.getInstance();
    private final Calendar calendarForTest02 = Calendar.getInstance();

    private final Meeting meeting1 = new Meeting("#9ABCA4", "reunion 01", "Oro Jakson", calendarForTest01, DummyEmployeesGenerator.DUMMY_EMPLOYEES);
    private final Meeting meeting2 = new Meeting ("#E9D0C6", "reunion 02", "Thousand Sunny", calendarForTest01, DummyEmployeesGenerator.DUMMY_EMPLOYEES);
    private final Meeting meeting3 = new Meeting ("#9ab8bc", "reunion 03", "Moby Dick", calendarForTest02, DummyEmployeesGenerator.DUMMY_EMPLOYEES);
    private final Meeting meeting4 = new Meeting ("#9ab8bc", "reunion 04", "Moby Dick", calendarForTest01, DummyEmployeesGenerator.DUMMY_EMPLOYEES);

    @Before
    public void setup(){
        testservice = DI.getNewInstanceApiService();
        calendarForTest01.set(2022,10,27,10,30);
        calendarForTest02.set(2022,11,1,14,0);
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
        calendarForFilter.set(2022,11,1,14,0);
        //List<Meetings> meeting = testservice.getMeetings();
        testservice.getMeetings().add(meeting1);
        testservice.getMeetings().add(meeting3);
        List<Meeting> meetingFilteredByDate = testservice.getMeetingsByDay(calendarForFilter.getTime());
        assertTrue(meetingFilteredByDate.size()==1);


    }
    @Test
    public void test_GetMeetingsByRoomWithSuccess(){
        Rooms roomForFilter = new Rooms("Thousand Sunny", "#E9D0C6");
        //List<Meetings> meeting = testservice.getMeetings();
        testservice.getMeetings().add(meeting2);
        testservice.getMeetings().add(meeting4);
        List<Meeting>meetingFilteredByRoom = testservice.getMeetingsByRoom(roomForFilter);
        assertTrue(meetingFilteredByRoom.size()==1);

    }
    @Test
    public void test_DeleteMeetingWithSuccess(){
        //List<Meeting> meeting = testservice.getMeetings();
        assertEquals(0, testservice.getMeetings().size());
        testservice.getMeetings().add(meeting1);
        testservice.getMeetings().add(meeting2);
        testservice.getMeetings().add(meeting3);
        testservice.getMeetings().add(meeting4);
        assertEquals(4, testservice.getMeetings().size());
        Meeting meetingToDelete = testservice.getMeetings().get(0);
        testservice.deleteMeeting(meetingToDelete);
        assertFalse(testservice.getMeetings().contains(meetingToDelete));
        assertEquals(3,testservice.getMeetings().size());
    }
}
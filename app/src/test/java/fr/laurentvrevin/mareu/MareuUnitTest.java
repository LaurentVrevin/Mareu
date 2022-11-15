package fr.laurentvrevin.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.model.Meeting;
import fr.laurentvrevin.mareu.model.Room;
import fr.laurentvrevin.mareu.service.DummyEmployeesGenerator;
import fr.laurentvrevin.mareu.service.MareuApiService;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class MareuUnitTest {

    //private ArrayList<Meeting> testMeetingList;
    private final Calendar calendarForTest01 = Calendar.getInstance();
    private final Calendar calendarForTest02 = Calendar.getInstance();
    private final Meeting meeting1 = new Meeting("#9ABCA4", "reunion 01", "Oro Jakson", calendarForTest01, DummyEmployeesGenerator.DUMMY_EMPLOYEES);
    private final Meeting meeting2 = new Meeting("#E9D0C6", "reunion 02", "Thousand Sunny", calendarForTest01, DummyEmployeesGenerator.DUMMY_EMPLOYEES);
    private final Meeting meeting3 = new Meeting("#9ab8bc", "reunion 03", "Moby Dick", calendarForTest02, DummyEmployeesGenerator.DUMMY_EMPLOYEES);
    private final Meeting meeting4 = new Meeting("#9ab8bc", "reunion 04", "Moby Dick", calendarForTest01, DummyEmployeesGenerator.DUMMY_EMPLOYEES);
    private MareuApiService testservice;

    @Before
    public void setup() {
        testservice = DI.getNewInstanceApiService();
        calendarForTest01.set(2022, 10, 27, 10, 30);
        calendarForTest02.set(2022, 11, 1, 14, 0);
    }

    @Test
    public void test_GetMeetingWithSuccess() {
        //Tester que nous récupérons bien la liste de meeting dans n'importe quel ordre
        //Que cette liste contient bien 2 meeting
        List<Meeting> meetingList = testservice.getMeetings();
        MatcherAssert.assertThat(testservice.getMeetings(), IsIterableContainingInAnyOrder.containsInAnyOrder(meetingList.toArray()));
        assertEquals(2, meetingList.size());
    }

    @Test
    public void test_CreateMeetingWithSuccess() {
        //Tester que l'on crée bien 2 meetings et que ceux-ci s'ajoutent bien aux 2 existant.
        assertEquals(2, testservice.getMeetings().size());
        testservice.createMeeting(meeting1);
        testservice.createMeeting(meeting2);
        //bien penser à creer les meeting
        List<Meeting> expectedMeeting = testservice.getMeetings();
        assertEquals(4, expectedMeeting.size());

    }

    @Test
    public void test_GetMeetingsByDayWithSuccess() {
        //Tester que l'on récupère bien le meeting correspondant à la date du filtre.
        Calendar calendarForFilter = Calendar.getInstance();
        calendarForFilter.set(2022, 11, 1, 14, 0);
        testservice.getMeetings().add(meeting1);
        testservice.getMeetings().add(meeting3);
        List<Meeting> meetingFilteredByDate = testservice.getMeetingsByDay(calendarForFilter.getTime());
        assertEquals(1, meetingFilteredByDate.size());

    }

    @Test
    public void test_GetMeetingsByRoomWithSuccess() {
        Room roomForFilter = new Room("Thousand Sunny", "#E9D0C6");
        testservice.getMeetings().add(meeting2);
        testservice.getMeetings().add(meeting4);
        List<Meeting> meetingFilteredByRoom = testservice.getMeetingsByRoom(roomForFilter);
        assertEquals(2, meetingFilteredByRoom.size());

    }

    @Test
    public void test_DeleteMeetingWithSuccess() {
        assertEquals(2, testservice.getMeetings().size());
        testservice.getMeetings().add(meeting1);
        testservice.getMeetings().add(meeting2);
        testservice.getMeetings().add(meeting3);
        testservice.getMeetings().add(meeting4);
        assertEquals(6, testservice.getMeetings().size());
        Meeting meetingToDelete = testservice.getMeetings().get(0);
        testservice.deleteMeeting(meetingToDelete);
        assertFalse(testservice.getMeetings().contains(meetingToDelete));
        assertEquals(5, testservice.getMeetings().size());
    }
}
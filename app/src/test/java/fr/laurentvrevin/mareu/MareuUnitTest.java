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
import java.util.Calendar;
import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.MareuApiService;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class MareuUnitTest {

    private MareuApiService testservice;
    private ArrayList<Meetings> testMeetingList;
    private Calendar calendarForTest = Calendar.getInstance();


    @Before
    public void setup(){
        testservice = DI.getNewInstanceApiService();
        calendarForTest.set(2022,10,27,10,30);
    }

    @Test
    public void test_GetMeetingsWithSuccess(){
        //on doit vérifier qu'on récupère bien la liste des réunions
        List<Meetings> meeting = testservice.getMeetings();
        assertEquals(0, testservice.getMeetings().size());
        //On donne pour valeur à expectedMeeting, le paramètre DUMMY_MEETING
        testservice.getMeetings().addAll(DUMMY_MEETING);
        List<Meetings> expectedMeeting = DUMMY_MEETING;
        //On check que meeting est bien récupéré dans n'importe quel ordre
        MatcherAssert.assertThat(meeting, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }
    @Test
    public void test_CreateMeetingWithSuccess(){
        //on doit créer une réunion et vérifier si celle-ci est bien créée

        //Affirme qu'il n'y a aucune réunion
        assertEquals(0, testservice.getMeetings().size());
        //Ajoute la réunion 01 via DUMMY_MEETING
        Meetings meetingToAdd = DUMMY_MEETING.get(0);
        testservice.createMeeting(meetingToAdd);
        //Affirme qu'il y a bien une réunion maintenant qui a été ajoutée
        assertEquals(1, testservice.getMeetings().size());
        Meetings meetingCreated = testservice.getMeetings().get(0);
        //vérifie que meetingToAdd est bien celle de MeetingCreated
        assertEquals(meetingToAdd, meetingCreated);

    }

    @Test
    public void test_GetMeetingsByDayWithSuccess(){
        Calendar calendarForFilter = Calendar.getInstance();
        calendarForFilter.set(2022, 10, 25, 10,30);
        //List<Meetings> meeting = testservice.getMeetings();
        testservice.getMeetings().addAll(DUMMY_MEETING);
        List<Meetings> meetingFilteredByDate = testservice.getMeetingsByDay(calendarForFilter.getTime());
        assertTrue(meetingFilteredByDate.size()==0);

    }
    @Test
    public void test_GetMeetingsByRoomWithSuccess(){
        Rooms roomForFilter = new Rooms("Thousand Sunny");
        //List<Meetings> meeting = testservice.getMeetings();
        testservice.getMeetings().addAll(DUMMY_MEETING);
        List<Meetings>meetingFilteredByRoom = testservice.getMeetingsByRoom(roomForFilter);
        assertTrue(meetingFilteredByRoom.size()==1);

    }
    @Test
    public void test_DeleteMeetingWithSuccess(){
        List<Meetings> meeting = testservice.getMeetings();
        testservice.getMeetings().addAll(DUMMY_MEETING);
        assertEquals(2, testservice.getMeetings().size());
        Meetings meetingToDelete = testservice.getMeetings().get(0);
        testservice.deleteMeeting(meetingToDelete);
        assertFalse(testservice.getMeetings().contains(meetingToDelete));
        assertEquals(1,testservice.getMeetings().size());
    }
}
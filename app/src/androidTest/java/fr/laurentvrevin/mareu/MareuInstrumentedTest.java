package fr.laurentvrevin.mareu;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static fr.laurentvrevin.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.laurentvrevin.mareu.activities.MainActivity;
import fr.laurentvrevin.mareu.utils.ClickItemWithId;
import fr.laurentvrevin.mareu.utils.DeleteViewAction;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MareuInstrumentedTest {

    @Rule
    public ActivityTestRule mainActivityActivityTestRule =
            new ActivityTestRule(MainActivity.class);
    int ITEM_COUNT;
    private MainActivity mMainActivity;

    @Before
    public void setUp() {
        mMainActivity = (MainActivity) mainActivityActivityTestRule.getActivity();
        assertThat(mMainActivity, notNullValue());
        ITEM_COUNT = 2;

    }

    @Test
    public void create_Meeting_With_Success() {

        //OUVRIR L'ACTIVITE DE CREATION DE REUNION
        onView(ViewMatchers.withId(R.id.activity_list_Meeting_Add_Fab)).perform(click());
        //l'activité s'ouvre, j'écris dans l'objet de la réunion : Reunion
        onView(ViewMatchers.withId(R.id.txt_Meeting_Object)).perform(click());
        onView(ViewMatchers.withId(R.id.test_txt_object)).perform(typeText("Reunion"));

        //DATEPICKER DIALOG
        onView(ViewMatchers.withId(R.id.tv_date_time_toselect)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2022, 11, 30));
        onView(withText("OK")).perform(click());
        //TIMEPICKER DIALOG
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(19, 0));
        onView(withText("OK")).perform(click());
        //SPINNER
        onView(ViewMatchers.withId(R.id.spinner_room_toselect)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        //RECYCLERVIEW INVITATIONS
        onView(ViewMatchers.withId(R.id.button_invitation_employees)).perform(click());
        onView(ViewMatchers.withId(R.id.recycler_view_employees)).perform(RecyclerViewActions.actionOnItemAtPosition
                (5, ClickItemWithId.clickChildViewWithId(R.id.checkbox_item)));
        onView(ViewMatchers.withId(R.id.recycler_view_employees)).perform(RecyclerViewActions.actionOnItemAtPosition
                (2, ClickItemWithId.clickChildViewWithId(R.id.checkbox_item)));
        onView(ViewMatchers.withId(R.id.recycler_view_employees)).perform(RecyclerViewActions.actionOnItemAtPosition
                (3, ClickItemWithId.clickChildViewWithId(R.id.checkbox_item)));
        onView(withText("VALIDER")).perform(click());
        onView(ViewMatchers.withId(R.id.button_save_meeting)).perform(click());

        //CHECK MEETINGLIST = 1 REUNION
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).check(withItemCount(ITEM_COUNT = 3));
    }

    @Test
    public void delete_Meeting_With_Success() {
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).check(withItemCount(ITEM_COUNT = 2));
        //SUPPRIME 1ERE REUNION
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // VERIFIE REUNION = 0
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).check(withItemCount(ITEM_COUNT = 1));
    }


    @Test
    public void filter_By_Date_With_Success() {
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).check(withItemCount(ITEM_COUNT = 2));
        onView(ViewMatchers.withId(R.id.menu_activity_item_sort_by)).perform(click());
        onView(withText("Filtrer par date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2022, 11, 29));
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).check(withItemCount(ITEM_COUNT = 0));
    }

    @Test
    public void filter_By_Room_With_Success() {
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).check(withItemCount(ITEM_COUNT = 2));
        onView(ViewMatchers.withId(R.id.menu_activity_item_sort_by)).perform(click());
        onView(withText("Filtrer par salle")).perform(click());
        onView(ViewMatchers.withId(R.id.spinner_room_filter)).perform(click());
        onView(withText("Oro Jackson")).inRoot(isPlatformPopup()).perform(click());
        onView(ViewMatchers.withId(R.id.button_OK_RoomFilter)).perform(click());
        onView(ViewMatchers.withId(R.id.activity_list_Meeting)).check(withItemCount(ITEM_COUNT = 1));
    }

}


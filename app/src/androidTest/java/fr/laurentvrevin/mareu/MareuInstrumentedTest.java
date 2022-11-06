package fr.laurentvrevin.mareu;




import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.MaterialDatePicker;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.laurentvrevin.mareu.activities.MainActivity;
import fr.laurentvrevin.mareu.activities.MeetingRoomBookingActivity;
import fr.laurentvrevin.mareu.utils.ClickItemWithId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MareuInstrumentedTest {

    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule mainActivityActivityTestRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp(){
        mMainActivity = (MainActivity) mainActivityActivityTestRule.getActivity();
        assertThat(mMainActivity, notNullValue());
    }

    @Test
    public void open_MeetingRoomBookingActivity(){

        onView(ViewMatchers.withId(R.id.activity_list_Meeting_Add_Fab)).perform(click());
        intended(hasComponent(MeetingRoomBookingActivity.class.getName()));

    }

    @Test
    public void create_MeetingWithAllControls(){

        //OUVRIR L'ACTIVITE DE CREATION DE REUNION
        onView(ViewMatchers.withId(R.id.activity_list_Meeting_Add_Fab)).perform(click());
        //l'activité s'ouvre, j'écris dans l'objet de la réunion : Reunion
        onView(ViewMatchers.withId(R.id.txt_Meeting_Object)).perform(click());
        onView(ViewMatchers.withId(R.id.test_txt_object)).perform(typeText("Reunion"));

        //DATEPICKER DIALOG
        //Choisis une date et une heure de début
        onView(ViewMatchers.withId(R.id.tv_date_time_toselect)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2022,11,30));
        //onView(withId(R.id.ok_button)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.button_invitation_employees)).perform(click());
        /*try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        onView(ViewMatchers.withId(R.id.recycler_view_employees)).perform(RecyclerViewActions.actionOnItemAtPosition
                (5, ClickItemWithId.clickChildViewWithId(R.id.checkbox_item)));
        onView(ViewMatchers.withId(R.id.recycler_view_employees)).perform(RecyclerViewActions.actionOnItemAtPosition
                (2, ClickItemWithId.clickChildViewWithId(R.id.checkbox_item)));
        onView(ViewMatchers.withId(R.id.recycler_view_employees)).perform(RecyclerViewActions.actionOnItemAtPosition
                (3, ClickItemWithId.clickChildViewWithId(R.id.checkbox_item)));
    }
}


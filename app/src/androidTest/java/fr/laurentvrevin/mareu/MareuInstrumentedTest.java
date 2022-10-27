package fr.laurentvrevin.mareu;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.notNullValue;

import static org.junit.Assert.assertThat;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.laurentvrevin.mareu.activities.MainActivity;
import fr.laurentvrevin.mareu.activities.MeetingRoomBookingActivity;

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
        Intents.init();
        onView(ViewMatchers.withId(R.id.activity_list_Meeting_Add_Fab)).perform(click());
        intended(hasComponent(MeetingRoomBookingActivity.class.getName()));
        Intents.release();
    }


}


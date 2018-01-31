package rick.trainset;

import android.app.Activity;
import android.os.SystemClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rick.trainset.Presentation.AuthActivities.RegisterActivity.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Rick on 1/29/2018.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterAct  {

    @Rule
    public IntentsTestRule<RegisterActivity> registerActivity = new IntentsTestRule<RegisterActivity>(RegisterActivity.class);

    @Test
    public void registerNewUser() {

        String email = "rcad.moraes@gmail.com";
        String name = "Rick Prata";
        String password = "123test";
        String company = "Trainset";

        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.name)).perform(typeText(name), closeSoftKeyboard());

        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.company)).perform(typeText(company), closeSoftKeyboard());

        onView(withId(R.id.confirmButton)).perform(click());

        SystemClock.sleep(5000);

        intended(allOf(
                hasComponent(hasShortClassName(".SignInActivity")),
                toPackage("rick.trainset")
        ));
    }
}

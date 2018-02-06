package rick.trainset;

import android.os.SystemClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rick.trainset.Presentation.AuthActivities.LoginActivity.LoginActivity;
import rick.trainset.Util.Injection;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Rick on 2/4/2018.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public IntentsTestRule<LoginActivity> loginActivity =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void loginUser() {

        String email = "rcad.moraes@gmail.com";
        String password = "123test";

        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.confirmButton)).perform(click());

        SystemClock.sleep(2000);

        intended(allOf(
                hasComponent(hasShortClassName(".HomeActivity")),
                toPackage("rick.trainset")
        ));
    }
}

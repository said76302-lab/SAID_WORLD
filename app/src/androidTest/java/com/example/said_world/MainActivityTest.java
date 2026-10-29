package com.example.said_world;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented UI / Integration test for MainActivity.
 * Executes on an Android device or emulator.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.said_world", appContext.getPackageName());
    }

    @Test
    public void initialUiStateIsEmptyText() {
        // Verify text1 is initially empty
        Espresso.onView(ViewMatchers.withId(R.id.text1))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));

        // Verify button1 and button2 are displayed
        Espresso.onView(ViewMatchers.withId(R.id.button1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.button2))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void addTextButtonDisplaysHelloWorld() {
        // Click "Add text" button
        Espresso.onView(ViewMatchers.withId(R.id.button1))
                .perform(ViewActions.click());

        // Check text1 contains "Hello World!"
        Espresso.onView(ViewMatchers.withId(R.id.text1))
                .check(ViewAssertions.matches(
                        ViewMatchers.withText(containsString("Hello World!"))
                ));
    }

    @Test
    public void changeColourButtonChangesTextColor() {
        // Click "Add text" button first
        Espresso.onView(ViewMatchers.withId(R.id.button1))
                .perform(ViewActions.click());

        // Click "change colour" button
        Espresso.onView(ViewMatchers.withId(R.id.button2))
                .perform(ViewActions.click());

        // Check text1 has the purple_200 text color
        Espresso.onView(ViewMatchers.withId(R.id.text1))
                .check(ViewAssertions.matches(hasTextColor(R.color.purple_200)));
    }

    /**
     * Custom Espresso matcher to verify the text color of a TextView.
     */
    private static Matcher<View> hasTextColor(final int colorResId) {
        return new BoundedMatcher<>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView textView) {
                int expectedColor = ContextCompat.getColor(textView.getContext(), colorResId);
                return textView.getCurrentTextColor() == expectedColor;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has text color with resource ID: " + colorResId);
            }
        };
    }
}

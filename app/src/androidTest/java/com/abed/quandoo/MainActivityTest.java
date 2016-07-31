package com.abed.quandoo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.abed.quandoo.ui.Main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }

    @Test
    public void changeText_sameActivity() {

        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.recylcer_items)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

//        onView(withId(R.id.changeTextBt)).perform(click());
//
//        // Check that the text was changed.
//        Espresso.onView(ViewMatchers.withId(R.id.textToBeChanged))
//                .check(matches(withText(mStringToBetyped)));
    }
}

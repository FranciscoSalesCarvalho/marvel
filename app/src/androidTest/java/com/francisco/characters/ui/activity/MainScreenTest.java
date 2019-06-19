package com.francisco.characters.ui.activity;

import android.content.Intent;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

import com.francisco.characters.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.francisco.characters.util.MyViewAction.clickChildViewWithId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> activity =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setup() {
        activity.launchActivity(new Intent());
    }

    @Test
    public void should_ShowTitle() {
        onView(withText("Characters"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void should_NavigateToComicsScreen_WhenClickOnThumbnail() throws InterruptedException {
        Thread.sleep(3000);
        onView(withId(R.id.list_feed))
                .perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.item_image)));
    }

}
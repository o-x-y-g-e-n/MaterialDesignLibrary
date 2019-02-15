package com.gc.materialdesigndemo;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;
import android.view.View;

import com.gc.materialdesigndemo.ui.MainActivity;
import com.gc.materialdesigndemo.ui.WidgetActivity;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.uiautomator.By.text;
import static android.support.test.uiautomator.Until.findObject;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class WidgetActivityTest {
    public UiDevice mDevice;

    @Rule
    public ActivityTestRule<WidgetActivity> mActivityRule = new ActivityTestRule<>(WidgetActivity.class);

    @Before
    public void initializeMe() {
        mDevice = UiDevice.getInstance(getInstrumentation());
    }

    @Test
    public void snackBarTest() throws UiObjectNotFoundException {
        mDevice.findObject(new UiSelector().childSelector(new UiSelector().text("SHOW SNACKBAR"))).click();
        onView(withChild(withText("YES"))).perform(click());

    }

    @Test
    public void dialogTest() throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().childSelector(new UiSelector().text("SHOW DIALOG"))).click();
        mDevice.findObject(new UiSelector().childSelector(new UiSelector().text("ACCEPT"))).click();
        onView(withText("Click accept button")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
    @Test
    public void colorSelectorTest() throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().childSelector(new UiSelector().text("SHOW COLOR SELECTOR"))).click();
        mDevice.wait(Until.hasObject(By.res("contentSelector")),5000);
        String s = mDevice.findObject(new UiSelector().childSelector(new UiSelector().text("R"))).getText();
        Assert.assertEquals(s,"R");

        mDevice.findObject(new UiSelector().childSelector(new UiSelector().resourceId("green"))).longClick();
        // lost from here....
    }


}

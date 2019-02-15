package com.gc.materialdesigndemo;
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

import com.gc.materialdesigndemo.temp.AppiumException;
import com.gc.materialdesigndemo.temp.AutomationCore;
import com.gc.materialdesigndemo.temp.ViewGetter;
import com.gc.materialdesigndemo.temp.WithXPath;
import com.gc.materialdesigndemo.ui.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.uiautomator.By.text;
import static android.support.test.uiautomator.Until.findObject;
import static com.gc.materialdesigndemo.temp.WithXPath.withXPath;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    public UiDevice mDevice;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initializeMe() {
        mDevice = UiDevice.getInstance(getInstrumentation());
    }

    public void checkStatus(String str1,String str2){
        try {
            mDevice.findObject(new UiSelector().childSelector(new UiSelector().text(str1))).click();
            onView(withChild(withText(str2))).check(matches(isDisplayed()));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Just Click on the Buttons Tab and see if it is transferred to new layout
    @Test
    public void openButtonsTabTest() throws UiObjectNotFoundException {
        try {
            AutomationCore.getInstance().getUiDevice().findObject(new UiSelector().childSelector(new UiSelector().text("Buttons"))).click();
//            onView(withChild(withText("Flat Button"))).check(matches(isDisplayed()));

            try {
                onView((withXPath(new ViewGetter().getRootView(),"//android.widget.TextView[@text='Flat Button']"))).check(matches(isDisplayed()));
                onView(withXPath(new ViewGetter().getRootView(),"//android.widget.RelativeLayout[@id='com.gc.materialdesigndemo:id/buttonflat']")).check(matches(isDisplayed()));
            } catch (AppiumException e) {
                e.printStackTrace();
            }

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openWidgetsTabTest() throws UiObjectNotFoundException, AppiumException {
        AutomationCore.getInstance().getUiDevice().findObject(new UiSelector().childSelector(new UiSelector().text("Widgets"))).click();
//        onView(withChild(withText("SnackBar"))).check(matches(isDisplayed()));
        onView(withChild(withXPath(new ViewGetter().getRootView(),"//android.widget.TextView[@text='SnackBar']"))).check(matches(isDisplayed()));
//        AutomationCore.getInstance().getUiDevice().findObject(new UiSelector().childSelector(new UiSelector().text("SHOW SNACKBAR"))).click();onView(withChild(withText("YES"))).perform(click());
        AutomationCore.getInstance().getUiDevice().findObject(new UiSelector().childSelector(new UiSelector().text("SHOW SNACKBAR"))).click();
        onView(withChild(withXPath(new ViewGetter().getRootView(),"//android.widget.TextView[@text='YES']"))).perform(click());
//        AutomationCore.getInstance().getUiDevice().findObject(new UiSelector().childSelector(new UiSelector().text("SHOW DIALOG"))).click();
//        AutomationCore.getInstance().getUiDevice().findObject(new UiSelector().childSelector(new UiSelector().text("ACCEPT"))).click();

    }


}

package com.example.slideshowcaster;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.viewpager.widget.ViewPager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity mActivity;

    /**
     * Usd to start MainActivity.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Will start a MainActivity.
     */
    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertNotNull(mActivity);
    }

    @Test
    public void testSlideshowFragmentIsVisibleFromStart() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
        checkCurrentPageTitle(appContext.getResources().getString(R.string.tab_slideshow));
    }

    @Test
    public void testSwipeToMediaFragment() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));

        onView(withId(R.id.view_pager))
                .perform(swipeLeft());

        checkCurrentPageTitle(appContext.getResources().getString(R.string.tab_media));
    }

    @Test
    public void testClickToMediaFragment() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));

        onView(withText(R.string.tab_media))
                .perform(click());

        checkCurrentPageTitle(appContext.getResources().getString(R.string.tab_media));
    }

    /**
     * Will check if current page has the given title.
     */
    private void checkCurrentPageTitle(CharSequence expected) {
        ViewPager viewPager = mActivity.findViewById(R.id.view_pager);
        int fragmentIndex = viewPager.getCurrentItem();
        CharSequence fragmentTitle = viewPager.getAdapter().getPageTitle(fragmentIndex);

        assertEquals(expected, fragmentTitle);
    }
}

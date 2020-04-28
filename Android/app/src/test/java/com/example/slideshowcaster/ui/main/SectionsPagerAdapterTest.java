package com.example.slideshowcaster.ui.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.res.Resources;
import androidx.fragment.app.Fragment;

import com.example.slideshowcaster.R;

import org.junit.Before;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SectionsPagerAdapterTest {

    SectionsPagerAdapter mAdapter;

    @Before
    public void setUp() throws Exception {
        mAdapter = new SectionsPagerAdapter(null, null);
    }

    @Test
    public void testGetItemSlideshow() {
        Fragment actual = mAdapter.getItem(0);
        assertTrue(actual.getClass().equals(SlideshowFragment.class));
    }

    @Test
    public void testGetItemMedia() {
        Fragment actual = mAdapter.getItem(1);
        assertTrue(actual.getClass().equals(MediaFragment.class));
    }

    @Test
    public void testGetNumberOfItems() {
        assertEquals(2, mAdapter.getCount());
    }

    @Test
    public void testGetPageTitle() {
        // Setup
        String expectedSlideShow = "EXPECTED_SS";
        String expectedMedia = "EXPECTED_M";
        Context mockContext = mock(Context.class);
        Resources mockRes = mock(Resources.class);
        when(mockContext.getResources()).thenReturn(mockRes);
        when(mockRes.getString(R.string.tab_slideshow)).thenReturn(expectedSlideShow);
        when(mockRes.getString(R.string.tab_media)).thenReturn(expectedMedia);

        // Execute
        mAdapter = new SectionsPagerAdapter(mockContext, null);
        CharSequence actualSlideshow = mAdapter.getPageTitle(SectionsPagerAdapter.SLIDESHOW_INDEX);
        CharSequence actualMedia = mAdapter.getPageTitle(SectionsPagerAdapter.MEDIA_INDEX);

        // Test
        assertEquals(expectedSlideShow, actualSlideshow);
        assertEquals(expectedMedia, actualMedia);
    }
}
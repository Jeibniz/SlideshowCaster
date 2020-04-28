package com.example.slideshowcaster.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.slideshowcaster.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_FRAGMENTS = 2;
    public static final int SLIDESHOW_INDEX = 0;
    public static final int MEDIA_INDEX = 1;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_slideshow, R.string.tab_media};
    private final Context mContext;

    /**
     * Initiates object.
     * @param context App context.
     * @param fm Will be used to switch between fragments.
     */
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    /**
     * Will create the fragment with the given index.
     * @param position index of fragment to create.
     * @return A new instance of a fragment
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case SLIDESHOW_INDEX:
                return new SlideshowFragment();
            case MEDIA_INDEX:
                return new MediaFragment();
            default:
                throw new IllegalArgumentException(
                        "Position must be larger than 0 and smaller than getCount().");
        }
    }

    /**
     * Returns the title of fragment in the given position.
     * @param position A position larger than 0 and smaller than {@link #getCount()}
     * @return The title for the fragment at {@code position}.
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    /**
     * Returns number of fragments the adapter supports.
     * @return The number of fragments the adapter supports.
     */
    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }
}
package com.treatmentangel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.treatmentangel.fragments.ProfileOverviewFragment;
import com.treatmentangel.fragments.ProfileReviewFragment;
import com.treatmentangel.fragments.ProfileTeamFragment;

/**
 * Created by Vikesh on 10-Feb-18.
 */

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;

    public ProfilePagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        this.NUM_ITEMS = tabCount;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return ProfileOverviewFragment.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return ProfileTeamFragment.newInstance();
            case 2: // Fragment # 1 - This will show SecondFragment
                return ProfileReviewFragment.newInstance();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}


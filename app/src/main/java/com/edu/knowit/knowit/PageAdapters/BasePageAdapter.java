package com.edu.knowit.knowit.PageAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.knowit.knowit.FeedsFragment;
import com.edu.knowit.knowit.HomeFragment;
import com.edu.knowit.knowit.LoginFragment;
import com.edu.knowit.knowit.PostFragment;
import com.edu.knowit.knowit.ProfileFragment;
import com.edu.knowit.knowit.SearchFragment;


/**
 * Created by Achala Kavinda on 4/30/2018.
 */

public class BasePageAdapter extends FragmentPagerAdapter {

    int Number_Of_tabs;

    public BasePageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.Number_Of_tabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FeedsFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new HomeFragment();
            case 3:
                return new PostFragment();
            case 4:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Number_Of_tabs;
    }
}

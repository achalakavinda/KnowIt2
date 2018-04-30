package com.edu.knowit.knowit.PageAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.knowit.knowit.LoginFragment;
import com.edu.knowit.knowit.RegisterFragment;

/**
 * Created by Achala Kavinda on 4/30/2018.
 */

public class UserLoginPageAdapter extends FragmentPagerAdapter {

    int Number_Of_tabs;

    public UserLoginPageAdapter(FragmentManager fm,int numOfTabs){
        super(fm);
        this.Number_Of_tabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LoginFragment tab1 = new LoginFragment();
                return tab1;
            case 1:
                RegisterFragment tab2 = new RegisterFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Number_Of_tabs;
    }
}

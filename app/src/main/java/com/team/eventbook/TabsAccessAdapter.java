package com.team.eventbook;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAccessAdapter extends FragmentPagerAdapter {
    public TabsAccessAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                User_PostFragment postFragment=new User_PostFragment();
                return postFragment;
            case 1:
                User_activityFragment user_activityFragment=new User_activityFragment();
                return user_activityFragment;
            case 2:
                User_chat_Fragment user_chat_fragment=new User_chat_Fragment();
                return user_chat_fragment;
            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}

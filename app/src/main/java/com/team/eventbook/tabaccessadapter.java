package com.team.eventbook;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class tabaccessadapter extends FragmentPagerAdapter {
    public tabaccessadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                post_date_fragment post_date_fragment=new post_date_fragment();
                return post_date_fragment;
            case 1:
                CategoriesFragment categoriesFragment=new CategoriesFragment();
                return categoriesFragment;
            case 2:
                locationFragment locationFragment=new locationFragment();
                return locationFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Post Time/Date";
            case 1:
                return "Categories";
            case 2:
                return "Location";
                default:
                    return null ;
        }
    }
}

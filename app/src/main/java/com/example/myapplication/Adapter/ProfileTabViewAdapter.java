package com.example.myapplication.Adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProfileTabViewAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    private final List <Fragment> fragmentList = new ArrayList <>(  );
    private final List<String> FragmentListTitle = new ArrayList <>(  );

    public ProfileTabViewAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get( position );
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitle.get( position );
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

    public void  AddFragment(Fragment fragment,String Title){
        fragmentList.add( fragment );
        FragmentListTitle.add( Title );
    }
}

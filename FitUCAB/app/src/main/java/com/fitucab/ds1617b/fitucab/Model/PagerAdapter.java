package com.fitucab.ds1617b.fitucab.Model;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.M03FragmentAmigos;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.M03FragmentNearMe;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.M03FragmentLibreta;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                M03FragmentAmigos tabAmigos = new M03FragmentAmigos();
                return tabAmigos;
            case 1:
                M03FragmentLibreta tabLibreta = new M03FragmentLibreta();
                return tabLibreta;
            case 2:
                M03FragmentNearMe tabCercaDeMi = new M03FragmentNearMe();
                return tabCercaDeMi;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
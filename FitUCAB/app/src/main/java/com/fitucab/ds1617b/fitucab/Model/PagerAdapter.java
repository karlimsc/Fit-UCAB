package com.fitucab.ds1617b.fitucab.Model;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.Fragment_Amigos;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.Fragment_Cerca_De_Mi;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.Fragment_Libreta;

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
                Fragment_Amigos tabAmigos = new Fragment_Amigos();
                return tabAmigos;
            case 1:
                Fragment_Libreta tabLibreta = new Fragment_Libreta();
                return tabLibreta;
            case 2:
                Fragment_Cerca_De_Mi tabCercaDeMi = new Fragment_Cerca_De_Mi();
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
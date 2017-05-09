package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

public class M10HomeActivity extends AppCompatActivity implements OnFragmentSwap{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m10_home);
    }

    @Override
    public void onSwap(String fragmentName, Bundle bundle) {

    }

    @Override
    public void onSwapActivity(String activityName, Bundle bundle) {

    }
}

package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fitucab.ds1617b.fitucab.R;

public class Prueba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        Intent newActivity = new Intent(this, M11Food.class);
        startActivity(newActivity);
    }
}

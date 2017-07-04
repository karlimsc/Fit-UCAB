package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.R;
import java.util.ArrayList;

import static android.R.attr.country;
import static android.support.test.InstrumentationRegistry.getContext;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.showToast;


public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder> {

    private ArrayList<Activit> _activitiesList;
    private CheckBox _checkbox;
    static public String activities = "";


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView activitieName, activitieHour;
        public LinearLayout layout;


        public ViewHolder(View view) {
            super(view);
            activitieName = (TextView) view.findViewById(R.id.tv_m06_activities_name);
            layout = (LinearLayout) view.findViewById(R.id.m06_layout1);

        }
    }


    public ActivitiesAdapter(ArrayList<Activit> trainingList) {

        this._activitiesList = trainingList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_activities, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /* obtener la instancia del objeto del click!*/

       final  Activit act = _activitiesList.get(position);
        String  n = act.get_name();
        holder.activitieName.setText(n);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!activities.contains(act.get_name())) {
                    activities += act.get_name() + ";";
                }
                else{

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return _activitiesList.size();
    }




}
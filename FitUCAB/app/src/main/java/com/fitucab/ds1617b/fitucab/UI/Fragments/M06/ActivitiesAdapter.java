package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.R;
import java.util.ArrayList;


public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder> {

    private ArrayList<Activit> _activitiesList;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView activitieName, activitieHour;

        public ViewHolder(View view) {
            super(view);
            activitieName = (TextView) view.findViewById(R.id.tv_m06_activities_name);
            activitieHour = (TextView) view.findViewById(R.id.tv_m06_activities_hours);
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

        Activit training = _activitiesList.get(position);
        holder.activitieName.setText(training.get_name());
        holder.activitieHour.setText(training.get_hour()+" Hours");


    }

    @Override
    public int getItemCount() {
        return _activitiesList.size();
    }

}
package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.R;


import java.util.ArrayList;


public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder>  {

    private ArrayList<Training> _trainingList;
    private OnFragmentSwap mFragment;
    static public Training t;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView trainingName;
        public LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            trainingName = (TextView) view.findViewById(R.id.tv_m06_training_name);
            layout = (LinearLayout) view.findViewById(R.id.m06_layout);
        }
    }


    public TrainingAdapter(ArrayList<Training> trainingList, OnFragmentSwap instance) {
        this.mFragment = instance;
        this._trainingList = trainingList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_training, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /* obtener la instancia del objeto del click!*/


            final Training training = _trainingList.get(position);

            System.out.println("hola");


        holder.trainingName.setText(training.get_trainingName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("training",training);
                mFragment.onSwap("M06DetailsTrainingFragment",bundle);

            }
        });


    }

    @Override
    public int getItemCount() {
        return _trainingList.size();
    }

   }
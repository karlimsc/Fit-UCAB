package com.fitucab.ds1617b.fitucab.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.ArrayAuxiliarTraining;
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;

/**
 * Created by Alejandro Fernandez on 23/5/2017.
 */

public class TrainingAdapter extends ArrayAdapter<ArrayAuxiliarTraining>{
    public TrainingAdapter(Context context, ArrayList<ArrayAuxiliarTraining> items) {
        super(context , 0, items);
    }

    @Override
    public int getViewTypeCount(){
        return 4;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ArrayAuxiliarTraining item = getItem(position);
        int type = getItemViewType(position);
        if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_m06_list_adapter, parent, false);
                TextView textViewNameTraining = (TextView) convertView.findViewById(R.id.m06_nombre_entrenamiento_adapter);
                textViewNameTraining.setText(item.get_nombreEntrenamiento());
            }

        return convertView;
    }
}

package com.fitucab.ds1617b.fitucab.Model;


import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.Fragment_Amigos;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.Fragment_Cerca_De_Mi;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M03.Fragment_Libreta;

import java.util.ArrayList;

import static android.R.attr.resource;

public class UsersAdapter extends ArrayAdapter<ArrayAuxiliar> {
    public UsersAdapter(Context context, ArrayList<ArrayAuxiliar> items) {
        super(context, 0, items);
    }

    @Override
    public int getViewTypeCount(){
        return 4;
    }

    @Override
    public int getItemViewType(int position){
        if (getItem(position).get_type() == 0){
            return 0;
        }
        else if (getItem(position).get_type() == 1){
            return 1;
        }
        else if (getItem(position).get_type() == 2){
            return 2;
        }
        else if (getItem(position).get_type() == 3){
            return 3;
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ArrayAuxiliar item = getItem(position);
        int type = getItemViewType(position);
        if (convertView == null) {
            if(type == 0){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_with_m03, parent, false);
            }
            else if(type == 1){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.contacts_without_m03, parent, false);
            }
            else if(type == 2){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.separator_with_m03, parent, false);
            }
            else if(type == 3){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.separator_without_m03, parent, false);
            }
        }

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.nombre);
        TextView tvHome = (TextView) convertView.findViewById(R.id.puntaje);
        // Populate the data into the template view using the data object
        tvName.setText(item.get_nombre());
        tvHome.setText(Integer.toString(item.get_puntaje()));
        // Return the completed view to render on screen
        return convertView;
    }
}
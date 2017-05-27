package com.fitucab.ds1617b.fitucab.Model;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<UserAuxiliar> {
    public UsersAdapter(Context context, ArrayList<UserAuxiliar> items) {
        super(context, 0, items);
    }

    @Override
    public int getViewTypeCount(){
        return 8;
    }

    @Override
    public int getItemViewType(int position){
        if (getItem(position).get_type() == 0){         //CONTACTO CON APLICACION
            return 0;
        }
        else if (getItem(position).get_type() == 1){    //CONTACTO SIN APLICACION
            return 1;
        }
        else if (getItem(position).get_type() == 2){    //SEPARADOR CONTACTO CON APLICACION
            return 2;
        }
        else if (getItem(position).get_type() == 3){    //SEPARADOR CONTACTO SIN APLICACION
            return 3;
        }
        else if (getItem(position).get_type() == 4){    //PETICION DE AMIGO
            return 4;
        }
        else if (getItem(position).get_type() == 7){    //AMIGO
            return 7;
        }
        else if (getItem(position).get_type() == 5){    //SEPARADOR PETICION DE AMIGO
            return 5;
        }
        else if (getItem(position).get_type() == 6){    //SEPARADOR AMIGO
            return 6;
        }


        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UserAuxiliar item = getItem(position);
        int type = getItemViewType(position);
        if (convertView == null) {
            if(type == 0){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_with_m03, parent, false);
                TextView tvName = (TextView) convertView.findViewById(R.id.nombre);
                TextView tvHome = (TextView) convertView.findViewById(R.id.puntaje);
                tvName.setText(item.get_username());
                tvHome.setText(Integer.toString(item.get_point()));

            }
            else if(type == 1){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.contacts_without_m03, parent, false);
                TextView tvName = (TextView) convertView.findViewById(R.id.nombre);
                tvName.setText(item.get_username());
            }
            else if(type == 2){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.separator_with_m03, parent, false);
            }
            else if(type == 3){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.separator_without_m03, parent, false);
            }
            else if(type == 4){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_list_m03, parent, false);
                TextView tvName = (TextView) convertView.findViewById(R.id.nombre);
                tvName.setText(item.get_username());
                TextView tvHome = (TextView) convertView.findViewById(R.id.puntaje);
                tvHome.setText(Integer.toString(item.get_point()));
            }
            else if(type == 7){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_list_m03, parent, false);
                TextView tvName = (TextView) convertView.findViewById(R.id.nombre);
                tvName.setText(item.get_username());
                TextView tvHome = (TextView) convertView.findViewById(R.id.puntaje);
                tvHome.setText(Integer.toString(item.get_point()));
            }
            else if(type == 5){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.separator_request_m03, parent, false);
            }
            else if(type == 6){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.separator_friends_m03, parent, false);
            }
        }



        return convertView;
    }
}
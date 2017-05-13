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

import java.util.ArrayList;

import static android.R.attr.resource;

public class UsersAdapter extends ArrayAdapter<User> {

    ArrayList<User> usersList;
    LayoutInflater vi;
    int Resource1;
    int Resource2;
    ViewHolder holder;

    public UsersAdapter(Context context, ArrayList<User> users) {

        super(context, 0, users);

        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource1 = resource;
        Resource2 = resource;
        usersList = users;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            holder = new ViewHolder();
            if (position == 0){
                v = vi.inflate(R.layout.Separador_M03_con, null);
            }
            else{
                v = vi.inflate(R.layout.list_item, null);
                holder.imageview = (ImageView) v.findViewById(R.id.thumb);
                holder.tvName = (TextView) v.findViewById(R.id.title);

            }

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.ic_launcher);
        Picasso.with(getContext()).load(actorList.get(position).getImage()).into(holder.imageview);
        String postTitle = actorList.get(position).getName();
        Spanned deTitle = Html.fromHtml(Html.fromHtml((String) postTitle).toString());
        holder.tvName.setText(String.valueOf(deTitle));

        return v;

    }


/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_M03, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.nombre);
        TextView tvHome = (TextView) convertView.findViewById(R.id.puntaje);
        // Populate the data into the template view using the data object
        tvName.setText(user.getNombre());
        tvHome.setText(Integer.toString(user.getPuntaje()));
        // Return the completed view to render on screen
        return convertView;
    }*/
}
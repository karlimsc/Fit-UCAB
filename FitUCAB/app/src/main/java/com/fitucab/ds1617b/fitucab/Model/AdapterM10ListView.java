package com.fitucab.ds1617b.fitucab.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;

import java.util.List;


/**
 * Created by JoseA2R on 23/5/17.
 */

public class AdapterM10ListView extends ArrayAdapter<Water> {

    public AdapterM10ListView(Context context, List<Water> objects){
        super (context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Water item = getItem(position);
        convertView = LayoutInflater.from(getContext()).
                inflate(R.layout.fragment_m10_listview_item,parent,false);
        //TextView textid = (TextView) convertView.findViewById(R.id.text_id);
        TextView texthour = (TextView) convertView.findViewById(R.id.lv_Time);
        TextView textml   = (TextView) convertView.findViewById(R.id.lv_ml);

      //  textid.setText(Integer.toString(item.get_id()));
        texthour.setText(item.get_time());
        textml.setText(Integer.toString(item.get_glasstype()));

        return convertView;
    }


}

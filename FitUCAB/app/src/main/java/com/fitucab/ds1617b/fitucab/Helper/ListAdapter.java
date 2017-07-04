package com.fitucab.ds1617b.fitucab.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;

/**
 * Created by Ubiipagos on 2/7/2017.
 */

public class ListAdapter extends BaseAdapter {

    private ArrayList<ItemList> arrayListItem;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListAdapter(ArrayList<ItemList> arrayListItem, Context context) {
        this.arrayListItem = arrayListItem;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arrayListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View _listViewItem = layoutInflater.inflate(R.layout.content_m09, null, true);

        //INSTANCIAR TEXTVIEWS DE CONTENIDO
        TextView _textViewName = (TextView) _listViewItem.findViewById(R.id._achievementTV);
        TextView _textViewDescription = (TextView) _listViewItem.findViewById(R.id._descriptionTV);
        TextView textViewAward = (TextView) _listViewItem.findViewById(R.id._awardTV);
        //DARLES VALOR A LOS TEXTVIEW
        _textViewName.setText(arrayListItem.get(position).getArchievements());
        _textViewDescription.setText(arrayListItem.get(position).getDescription());
        textViewAward.setText(arrayListItem.get(position).getScore());
        //DEVOLVER LISTA
        return _listViewItem;
    }
}

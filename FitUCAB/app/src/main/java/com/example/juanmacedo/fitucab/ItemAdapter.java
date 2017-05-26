package com.example.juanmacedo.fitucab;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by juanmacedo on 25/04/17.
 */

public class ItemAdapter extends BaseAdapter{

    private int cant = 0;
    private String[] ids;
    private String[] name;

    public ItemAdapter(int cantidad, String[] id, String[] nombre){
        cant = cantidad;
        name = nombre;
        ids = id;
    }

    @Override
    public int getCount() {
        return cant;
    }

    public Object getItem(int _position) {
        return null;

    }

    @Override
    public long getItemId(int _position) {
        return 0;
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        return null;
    }
}

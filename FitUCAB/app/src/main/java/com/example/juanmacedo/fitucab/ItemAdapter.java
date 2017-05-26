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

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

package com.example.elberg.listarender;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adaptador de los items de las actividades para meterlos dentro del listView, es decir va  visualizar los elementos
 * de la lista
 */

public class AdapterItem extends BaseAdapter {

    protected Activity _activity;
    protected ArrayList<Activ> _items;

    /**
     * Constructor del AdapterItem
     * @param activity Actiidad que se le va a dartar el listView
     * @param items  Lista de intems de la clase actividad
     */
    public AdapterItem (Activity activity, ArrayList<Activ> items) {
        this._activity = activity;
        this. _items = items;
    }

    /**
     *
     * @return  Numero de elementos en los que consta la lista
     */

    @Override
    public int getCount() {
        return _items.size();
    }

   // Metodo para Limpiar el List ArrayList

    public void clear() {
        _items.clear();
    }

    /**
     *
     * @param activitys
     */
    public void addAll(ArrayList<Activ> activitys) {
        for (int i = 0; i < activitys.size(); i++) {
            _items.add(activitys.get(i));
        }
    }

    /**
     * Para interactuar con los elementos de la lista
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return _items.get(position);
    }

    /**
     * Para interactuar con los elemnetos de la lista
     * @param position
     * @return
     */

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Es el encargado de inflar o personalizar la lista
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item, null);
        }

        Activ dir = _items.get(position);

        // Se llenan los TextView con los datos de para visualizar los items

        TextView date = (TextView) v.findViewById(R.id.tv_m05_date);
        date.setText(dir.getDateActivity().toString());

        TextView distance = (TextView) v.findViewById(R.id.tv_m05_distance);
        distance.setText(String.valueOf(dir.getMetersActivity()));

       /* ImageView imagen = (ImageView) v.findViewById(R.id.iv_m05_imagereference);
        imagen.setImageDrawable(dir.getImege());
*/
        TextView speed = (TextView) v.findViewById(R.id.tv_m05_speed);
        speed.setText(String.valueOf(dir.getSpeedActivity()));

        TextView name = (TextView) v.findViewById(R.id.tv_m05_sportname);
        name.setText(dir.getSportName());

        TextView uptime = (TextView) v.findViewById(R.id.tv_m05_uptime);
        uptime.setText(String.valueOf(dir.getUptime()));
        return v;
    }
}

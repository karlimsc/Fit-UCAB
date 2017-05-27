package com.example.juanmacedo.fitucab;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by juanmacedo on 18/05/17.
 */

public class CustomList extends ArrayAdapter<String> {
    private String[] ids;
    private String[] names;
    private String[] description;
    private String[] puntos;
    private Activity context;

    public CustomList(Activity context, String[] ids, String[] names, String[] description, String[] puntos) {
        super(context, R.layout.contenido, ids);
        this.context = context;
        this.names = names;
        this.description = description;
        this.ids = ids;
        this.puntos = puntos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.contenido, null, true);

        //INSTANCIAR TEXTVIEWS DE CONTENIDO
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_logro);
        TextView textViewDescripcion = (TextView) listViewItem.findViewById(R.id.tv_descripcion);
        TextView textViewRecompensa = (TextView) listViewItem.findViewById(R.id.tv_recompensa);
        //DARLES VALOR A LOS TEXTVIEW
        textViewName.setText(names[position]);
        textViewDescripcion.setText(description[position]);
        textViewRecompensa.setText(puntos[position]);

        //DEVOLVER LISTA
        return listViewItem;
    }


}



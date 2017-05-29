package com.fitucab.ds1617b.fitucab.Helper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;

/**
 * Clase para el manejo de la lista de Logros de M09GamificationActivity.class
 *
 * @AUTHORS: Juan Macedo, Cesar Boza, Bryan Teixeira
 * @VERSION: 1.0
 */
public class CustomList extends ArrayAdapter<String> {

    /**
     * Array de Strings para el manejo de los IDs de los logros
     */
    private String[] _ids;
    /**
     * Array de Strings para el manejo de los nombres de los logros
     */
    private String[] _names;
    /**
     * Array de Strings para el manejo de la descripcion de los logros
     */
    private String[] _description;
    /**
     * Array de Strings para el manejo de los puntos de los logros
     */
    private String[] _points;
    /**
     * Variable para el manejo del contexto de la Actividad M09GamificationActivity
     */
    private Activity _context;

    /**
     * Constructor de la clase CustomList con todos los parametros de la misma
     * @param _context Variable para el manejo del contexto de la Actividad M09GamificationActivity
     * @param _ids Array de Strings para el manejo de los IDs de los logros
     * @param _names Array de Strings para el manejo de los nombres de los logros
     * @param _description Array de Strings para el manejo de la descripcion de los logros
     * @param _points Array de Strings para el manejo de los puntos de los logros
     */
    public CustomList(Activity _context, String[] _ids, String[] _names, String[] _description,
                      String[] _points) {
        super(_context, R.layout.content_m09 , _ids);
        this._context = _context;
        this._names = _names;
        this._description = _description;
        this._ids = _ids;
        this._points = _points;
    }

    /**
     * Metodo para obtener la vista donde se cargaran los logros
     *
     * @param _position int para el manejo de los ciclos dentro de la lista
     * @param _convertView
     * @param parent
     * @return _listViewItem Lista con los items de logros de la vista
     */
    @Override
    public View getView(int _position, View _convertView, ViewGroup parent) {
        LayoutInflater _inflater = _context.getLayoutInflater();
        View _listViewItem = _inflater.inflate(R.layout.content_m09, null, true);

        //INSTANCIAR TEXTVIEWS DE CONTENIDO
        TextView _textViewName = (TextView) _listViewItem.findViewById(R.id._achievementTV);
        TextView _textViewDescription = (TextView) _listViewItem.findViewById(R.id._descriptionTV);
        TextView textViewAward = (TextView) _listViewItem.findViewById(R.id._awardTV);
        //DARLES VALOR A LOS TEXTVIEW
        _textViewName.setText(_names[_position]);
        _textViewDescription.setText(_description[_position]);
        textViewAward.setText(_points[_position]);
        //DEVOLVER LISTA
        return _listViewItem;
    }


}



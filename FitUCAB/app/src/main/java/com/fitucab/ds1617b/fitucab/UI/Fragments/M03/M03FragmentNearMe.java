package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.GPSTracker;
import com.fitucab.ds1617b.fitucab.R;
/**
Aqui falta lo que hace la clase como tal y ya

*/
public class M03FragmentNearMe extends Fragment{

    TextView latitud;
    TextView longitud;

    View rootView;

    /**
     Es llamado cuando el fragmento es creado por primera vez ,devuelve una View (rootView)desde
     este método que será la raíz del diseño de nuestro fragmento

     @param savedInstanceState es un Bundle que proporciona datos acerca de la instancia previa del fragmento.
     @param container Es el ViewGroup principal en el cual se insertará el diseño de nuestro fragmento.
     @param inflater este parametro sirve para 3 argumentos pero aqui solo lo utilizamos para el
     Viewgroup. Para que el sistema aplique parámetros de diseño a la vista de raíz
     del diseño agrandado, especificada por la vista principal a la que se integra.
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //se encarga de poner los atributos de diseño del ViewGroup padre
        rootView = inflater.inflate(R.layout.fragment_m03_near_me, container, false);

        GPSTracker gps = new GPSTracker(rootView.getContext());
        if(gps.canGetLocation()){


            double dlat = gps.getLatitude();
            double dlong = gps.getLongitude();

            latitud = (TextView) rootView.findViewById(R.id.nearMeName);
            longitud = (TextView) rootView.findViewById(R.id.nearMePoints);
            latitud.setText(String.valueOf(dlat));
            longitud.setText(String.valueOf(dlong));
        }

        return rootView;
    }


}
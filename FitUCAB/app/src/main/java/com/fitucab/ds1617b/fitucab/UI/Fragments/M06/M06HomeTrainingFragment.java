package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by Alejandro Fernandez on 23/4/2017.
 */

public class M06HomeTrainingFragment extends Fragment implements ListView.OnItemClickListener{

    private View _view;
    private OnFragmentSwap _callBack;
    private ListView _listView;
    private ArrayAdapter<String> _adaptador;
    private String[] entrenamientos = {"Entrenamiento CAF 2018", "Entrenamiento lento","Caminata", "Trote"};
    //  Se debe crear Objeto del tipo entrenamiento para luego pasarle lo que contenga y asi poder compartirlo
    //Esta variable es solo de prueba
    private String _posicionDeEntrenamiento;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =  inflater.inflate(R.layout.fragment_m06_home_training, container, false);
        setupViewValues();

        return _view;
    }

    /**
     * Se relacionan los atributos de este fragments a los del Fragment Home Training
     */

    private void setupViewValues() {
        /*  _toolbar=(Toolbar) _view.findViewById(R.id.m06_toolbar_training_6);
        getActivity().setTitle(R.string.M06_nombre_modulo);
        ((AppCompatActivity)getActivity()).setSupportActionBar(_toolbar);
        */
        //Llenando el list View
        _listView = (ListView) _view.findViewById ( R.id.m06_listViewEntrenamiento );
        _adaptador = new ArrayAdapter<String>( getContext() , android.R.layout.simple_list_item_1 , entrenamientos);
        _listView.setAdapter( _adaptador );
        _listView.setOnItemClickListener( this );
        registerForContextMenu( _listView );

    }

    /**
     * Metodo que se ejecuta al pulsar un item de la lista de entrenamiento
     * @param parent
     * @param view
     * @param position
     * @param id
     */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Si se pulsa simple se abre para detalles
        _callBack.onSwap("M06DetailsTrainingFragment",null);

        //Si se deja pulsado mas tiempo se abre el menu contextual con diferentes opciones
        registerForContextMenu( _listView );
    }

    /**
     * Crea el menú contextual para mostrar las diferentes opciones
     * @param menu
     * @param v
     * @param menuInfo
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_m06_contextual, menu);
    }

    /**
     * Al pulsar algun elemento dle menu contextual se ejecutan las diferentes acciones
     * @param item
     * @return
     */

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Bundle bundle = new Bundle();
        switch (item.getItemId()) {
            case R.id.m06_menu_editar:
                _callBack.onSwap("M06ModifyTrainingFragment",null);
                return true;
            case R.id.m06_menu_eliminar:
                //Aqui solo eliminas el entrenamiento y ya. Luego se debe actualizar la lista
                return true;
            case R.id.m06_menu_compartir:
                     bundle = encapsulandoInformacionEntrenamiento( item );
                    _callBack.onSwap( "M06ShareTrainingFragment" , bundle );
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Esta funcion recibe el entrenamiento a compartir con un amigo, aquí se encapsula para enviar
     * a través del bundle y así pasarlo a otro fragment, devuelve el objeto bundle
     * @param item
     * @return
     */
    public Bundle encapsulandoInformacionEntrenamiento( MenuItem item ){
        //Se supone que aqui se mete en el objeto y luego se mete en el bundle, pero
        //Mientras no hay objeto trabajaré así
        Bundle bundle= new Bundle();
        AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        _posicionDeEntrenamiento =  ((TextView) info.targetView).getText().toString();
        bundle.putString("Nombre de Entrenamiento",_posicionDeEntrenamiento);
        return bundle;
    }
}

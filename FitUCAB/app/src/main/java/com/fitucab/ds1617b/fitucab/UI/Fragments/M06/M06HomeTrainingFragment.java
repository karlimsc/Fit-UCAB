package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alejandro Fernandez on 23/4/2017.
 */

public class M06HomeTrainingFragment extends Fragment implements ListView.OnItemClickListener{

    private View _view;
    private OnFragmentSwap _callBack;
    private ListView _listView;
    private ArrayAdapter<String> _adaptador;
    //Esta variable es solo de prueba
    private String _posicionDeEntrenamiento;
    private ArrayList<Training> _entrenamientos;


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

        //Llenando el list View
        fillListView();
        registerForContextMenu( _listView );
            //Esto se borra luego, COÑO SE BORRA
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("idUser", 1);

        editor.putFloat("weight", 55);

        editor.commit();
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
        String nombreEntrenamiento = parent.getItemAtPosition(position).toString();
        Bundle bundle= new Bundle();
        Training entrenamiento = new Training();
        entrenamiento = buscarEntrenamiento( nombreEntrenamiento );
        bundle.putInt( "idEntrenamiento" , entrenamiento.getidTraining() );
        bundle.putInt( "periodicidadEntrenamiento" , entrenamiento.getTrainingPeriod() ) ;
        bundle.getInt( "caloriasEntrenamiento" , entrenamiento.getTrainingCalories() );
        bundle.putString( "nombreDeEntrenamiento" , entrenamiento.getTrainingName() );
        _callBack.onSwap("M06DetailsTrainingFragment",bundle);

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
     * @return bundle donde se encuentra alojado el nombre del entrenamiento
     */
    public Bundle encapsulandoInformacionEntrenamiento( MenuItem item ){
        Bundle bundle= new Bundle();
        Training entrenamiento = new Training();
        entrenamiento = buscarEntrenamiento( _posicionDeEntrenamiento );
        AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        _posicionDeEntrenamiento =  ( (TextView) info.targetView).getText().toString();
        bundle.putInt( "idEntrenamiento" , entrenamiento.getidTraining() );
        return bundle;

    }

    /**
     * Este metodo busca el id dentro del objeto para pasarlo al proximo fragment
     * @param posicionDeEntrenamiento
     * @return
     */
    private Training buscarEntrenamiento(String posicionDeEntrenamiento) {
        int id = 0;
        Training entrenamiento = new Training();
        for (int i=0 ; i!=_entrenamientos.size() ; i++){

            if( _entrenamientos.get(i).getTrainingName().equals( posicionDeEntrenamiento )){
                entrenamiento.setId( _entrenamientos.get(i).getidTraining() );
                entrenamiento.setTrainingName( _entrenamientos.get(i).getTrainingName());
                entrenamiento.setTrainingPeriod( _entrenamientos.get(i).getTrainingPeriod() );
                entrenamiento.setTrainingCalories( _entrenamientos.get(i).getTrainingCalories() );
                return entrenamiento;

            }

        }

        return entrenamiento;

    }

    /**
     * Metodo que rellena el list haciendo la peticion al WS.
     * La url debe ser la de la pc donde se ejecuta el servidor + la llamada del metodo que se
     * necesite.
     */
    public void fillListView(){

        //Url a la cual se va a hacer conexion
        IpStringConnection ip = new IpStringConnection();
        String url = ip.getIp() + "M06_ServicesTraining/displayTraining?userId=" + ManagePreferences.getIdUser(getContext());
        final Gson gson = new Gson();

        // Instancia RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        _listView = (ListView) _view.findViewById( R.id.m06_listViewEntrenamiento );

        //Se hace la peticion y lo devuelve en String Request
        StringRequest stringRequest = new StringRequest( Request.Method.GET , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<String> entrenamiento = new ArrayList<String>();
                        ArrayList<Training> at = new ArrayList<Training>();
                        at = gson.fromJson( response , new TypeToken< List< Training > >(){}.getType() );
                        _entrenamientos = at;
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, entrenamiento);
                        _listView.setAdapter( adaptador );


                        //Hago el for para meter todo en el entrenamiento y asi pasarlo al listview
                        for(int i = 0;i<at.size();i++){
                            entrenamiento.add( at.get(i).getTrainingName() );
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ArrayList<String> entrenamiento = new ArrayList<String>();
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, entrenamiento);
                _listView.setAdapter( adaptador );
                entrenamiento.add( "Fallo la conexión intente mas tarde");

            }
        });
        // Add the request to the RequestQueue.
        queue.add( stringRequest );
        _listView.setOnItemClickListener( this );
    }
}

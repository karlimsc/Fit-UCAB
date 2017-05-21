package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by Alejandro Fernandez on 24/4/2017.
 */

public class M06ShareTrainingFragment extends Fragment implements ListView.OnItemClickListener,
                                                                            View.OnClickListener{

    private ListView _listView;
    private View _view;
    private OnFragmentSwap _callBack;
    private ArrayAdapter<String> _adaptador;
    private Toolbar _toolbar;
    private String[] contactos = {"Dayana Colmenares", "Pedro Fernández","Luis Machado"};
    private Bundle _bundle;
    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */

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

    /**
     * Metodo que retorno la vista y realiza llamados a los metodos de los botones, que se encargan de esperar un evento de click
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =  inflater.inflate(R.layout.fragment_m06_share_training, container, false);
        setupViewValues();
        return _view;
    }

    /**
     * Prepara los componentes de la vista.
     */
    private void setupViewValues() {
        //Llenando el list View
        _listView=(ListView)_view.findViewById(R.id.m06_listViewContactos);
        _adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, contactos);
        _listView.setAdapter(_adaptador);
        _listView.setOnItemClickListener(this);

    }
    /**
     * Este metodo se ocupa de extraer lo que está dentro del bundle y poder compartir el entrenamiento
     */
    private void extraerBundle(){
        try{
            _bundle = getArguments();
            String myInt = _bundle.getString("Nombre de Entrenamiento");
        }catch (Exception e){
            //Debo cambiar esto NO PEDRO NO PUEDES DECIR QUE PELASTE BOLAS
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Pele bolas", duration);
            toast.show();
        }
    }

    /**
     * Seleccionando el amigo procede a compartirlo y le avisa al amigo (Usuario receptor) a través
     * de una notificacion(Metodo del Modulo de Notificaciones)
     * @param parent
     * @param view
     * @param position
     * @param id
     */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), R.string.M06_compartir_exitoso, Toast.LENGTH_SHORT).show();
        /*
            Aqui adentro es donde haré las peticiones
         */
        _callBack.onSwap("M06HomeTrainingFragment",null);
    }

    @Override
    public void onClick(View v) {

    }
}

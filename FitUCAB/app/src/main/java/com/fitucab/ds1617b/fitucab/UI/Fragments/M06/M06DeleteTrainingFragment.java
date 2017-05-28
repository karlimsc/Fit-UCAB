package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
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

public class M06DeleteTrainingFragment extends Fragment implements ListView.OnItemClickListener,
                                                                            View.OnClickListener{

    private ListView _listView;
    private View _view;
    private OnFragmentSwap _callBack;
    private ArrayAdapter<String> _adaptador;
    private Toolbar _toolbar;
    private String[] entrenamientos = {"Entrenamiento CAF 2018", "Entrenamiento lento","Caminata", "Trote"};


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
        _view =  inflater.inflate(R.layout.fragment_m06_delete_training, container, false);
        setupViewValues();
        return _view;
    }

    /**
     * Metodo donde se asignan los botones del layout al fragment que se esta usando
     */
    private void setupViewValues() {
        //Llenando el list View
        _listView=(ListView)_view.findViewById(R.id.m06_listViewEntrenamiento);
        _adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, entrenamientos);
        _listView.setAdapter(_adaptador);
        _listView.setOnItemClickListener(this);
    }

    /**
     * Metodo que borra el entrenamiento que se selecciona el usuario.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), R.string.M06_eliminar_exitoso, Toast.LENGTH_SHORT).show();
        _callBack.onSwap("M06HomeTrainingFragment",null);
    }

    @Override
    public void onClick(View v) {

    }
}

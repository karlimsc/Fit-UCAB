package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 * */
public class M07HomeFragment extends Fragment {
    private OnFragmentSwap _callBack;
    private Button _btn_crear;
    private View _view;

    public M07HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m07_home, container, false);
        _btn_crear = (Button) _view.findViewById(R.id.btnCrear);
        crearEvento();
        return _view;

    }


    /**
     * Metodo que se llama automaticamente cuando la la actividad anfitriona usa el fragmento.
     * @param activity Recibe la actividad anfitriona en la que va a mostrarse.
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

    private void  crearEvento(){

        _btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoEvento();
            }

        });

    }

    private void dialogoEvento() {

        final CharSequence[] items = {"Entrenamiento", "Actividad"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Evento a crear");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Toast toast = Toast.makeText(getContext(), "Haz elegido la opcion: " + items[item] , Toast.LENGTH_SHORT);
                toast.show();
                dialog.cancel();

                    if (items[item] == "Entrenamiento") {
                        _callBack.onSwap("M07TrainingFragment", null);
                    }
                else {
                        _callBack.onSwap("M07ActivityFragment", null);
                    }

            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }





}

package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class M07PlanificationFragment extends DialogFragment {
    private TextView _tv_m07_fechaInicio;
    private TextView _tv_m07_fechaI;
    private TextView _tv_m07_fechaFin;
    private TextView _tv_m07_fechaF;
    private TextView _tv_m07_horaInicio;
    private TextView _tv_m07_horaI;
    private TextView _tv_m07_horaFin;
    private TextView _tv_m07_horaF;
    private TextView _tv_m07_frecuencia;
    private TextView _tv_m07_dias;
    private View _view;
    private OnFragmentSwap _callBack;
    private Planification planification;

    public M07PlanificationFragment() {
        // Required empty public constructor
    }
    public M07PlanificationFragment( Planification planification ){
        this.planification = planification;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Infla el fragmento
        final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _view = inflater.inflate(R.layout.fragment_m07_planification, null);
        _tv_m07_fechaInicio = ( TextView ) _view.findViewById(R.id.tv_m07_fechaInicio);
        _tv_m07_fechaI = ( TextView ) _view.findViewById(R.id.tv_m07_fechaI);
        _tv_m07_fechaFin = ( TextView ) _view.findViewById(R.id.tv_m07_fechaFin);
        _tv_m07_fechaF = ( TextView ) _view.findViewById(R.id.tv_m07_fechaF);
        _tv_m07_horaInicio = ( TextView ) _view.findViewById(R.id.tv_m07_horaInicio);
        _tv_m07_horaI = ( TextView ) _view.findViewById(R.id.tv_m07_horaI);
        _tv_m07_horaFin = ( TextView ) _view.findViewById(R.id.tv_m07_horaFin);
        _tv_m07_horaF = ( TextView ) _view.findViewById(R.id.tv_m07_horaF);
        _tv_m07_frecuencia = ( TextView ) _view.findViewById(R.id.tv_m07_frecuencia);
        _tv_m07_dias = ( TextView ) _view.findViewById(R.id.tv_m07_dias);
        inicializarDatos();
        builder.setView(_view);
        //Conecta con el XML que contiene la personalizacion del Dialog
        builder.setTitle("Ver detalle");
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
    return builder.create();
    }

    public void inicializarDatos(){
        int cantidadDias = 0;
        _tv_m07_fechaI.setText( String.valueOf(planification.get_startDate()) );
        _tv_m07_fechaF.setText( String.valueOf(planification.get_endDate()) );
        _tv_m07_horaI.setText( String.valueOf(planification.get_startTime()) );
        _tv_m07_horaF.setText( String.valueOf(planification.get_duration()) );
        for (boolean day : planification.get_days()) {
            if ( day == true )
                cantidadDias++;
        }
        _tv_m07_dias.setText( String.valueOf( cantidadDias )+ " dias" );
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


}

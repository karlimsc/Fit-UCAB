package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 **/
public class M07ActivityFragment extends Fragment {
    private ImageButton _btn_m07_fechaInicio;
    private EditText _tv_m07_fechaInicio;
    private ImageButton _btn_m07_fechaFin;
    private EditText _tv_m07_fechaFin;
    private ImageButton _btn_m07_horaInicio;
    private EditText _tv_m07_horaInicio;
    private ImageButton _btn_m07_horaFin;
    private EditText _tv_m07_horaFin;
    private View _view;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int min;


    public M07ActivityFragment() {
        // Required empty public constructor
    }


    public static M07ActivityFragment newInstance(String param1, String param2) {

        return null;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m07_activity, container, false);

        _btn_m07_fechaInicio = (ImageButton)_view.findViewById(R.id.btn_m07_fechaInicio);
        _tv_m07_fechaInicio = (EditText)_view.findViewById(R.id.tv_m07_fechaInicio);
        _btn_m07_fechaFin = (ImageButton)_view.findViewById(R.id.btn_m07_fechaFin);
        _tv_m07_fechaFin = (EditText)_view.findViewById(R.id.tv_m07_fechaFin);
        _btn_m07_horaInicio = (ImageButton)_view.findViewById(R.id.btn_m07_horaInicio);
        _tv_m07_horaInicio = (EditText)_view.findViewById(R.id.tv_m07_horaInicio);
        _btn_m07_horaFin = (ImageButton)_view.findViewById(R.id.btn_m07_horaFin);
        _tv_m07_horaFin = (EditText)_view.findViewById(R.id.tv_m07_horaFin);

        _btn_m07_fechaInicio.setOnClickListener((View.OnClickListener) this);
        _btn_m07_fechaInicio.setOnClickListener((View.OnClickListener) this);
        _btn_m07_horaInicio.setOnClickListener((View.OnClickListener) this);
        _btn_m07_horaFin.setOnClickListener((View.OnClickListener) this);


        return _view;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    /**@Override**/
    public void onClick(View v) {
        if (v == _btn_m07_fechaInicio){

            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    _tv_m07_fechaInicio.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();

        }

        if (v == _btn_m07_fechaFin) {

        }
        if (v == _btn_m07_horaInicio) {

        }
        if (v == _btn_m07_horaFin) {

        }
    }
}
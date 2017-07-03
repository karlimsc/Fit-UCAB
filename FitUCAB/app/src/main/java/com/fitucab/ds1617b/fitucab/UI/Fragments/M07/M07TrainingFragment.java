package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.fitucab.ds1617b.fitucab.R;

import java.text.StringCharacterIterator;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 **/
public class M07TrainingFragment extends Fragment {

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

    public M07TrainingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m07_training, container, false);

        _btn_m07_fechaInicio = (ImageButton)_view.findViewById(R.id.btn_m07_fechaInicio);
        _tv_m07_fechaInicio = (EditText)_view.findViewById(R.id.tv_m07_fechaInicio);
        _btn_m07_fechaFin = (ImageButton)_view.findViewById(R.id.btn_m07_fechaFin);
        _tv_m07_fechaFin = (EditText)_view.findViewById(R.id.tv_m07_fechaFin);
        _btn_m07_horaInicio = (ImageButton)_view.findViewById(R.id.btn_m07_horaInicio);
        _tv_m07_horaInicio = (EditText)_view.findViewById(R.id.tv_m07_horaInicio);
        _btn_m07_horaFin = (ImageButton)_view.findViewById(R.id.btn_m07_horaFin);
        _tv_m07_horaFin = (EditText)_view.findViewById(R.id.tv_m07_horaFin);


        return _view;
    }

    public void agregarFechaInicio(){
        //_btn_m07_fechaInicio = ( ImageButton ) _view.findViewById( R.id.btn_m07_fechaInicio );
        _btn_m07_fechaInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
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
        });
    }
    public void agregarFechaFin(){
        _btn_m07_fechaFin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        _tv_m07_fechaFin.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,dia,mes,ano);
                datePickerDialog.show();
            }
        });
    }
    public void agregarHoraInicio(){
        _btn_m07_horaInicio.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                min = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                _tv_m07_horaInicio.setText(hourOfDay + ":" + minute);
                            }
                        }, hora, min, false);
                timePickerDialog.show();
            }
        });
    }
    public void agregarHoraFin(){
        _btn_m07_horaFin.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                min = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                _tv_m07_horaFin.setText(hourOfDay + ":" + minute);
                            }
                        }, hora, min, false);
                timePickerDialog.show();
            }
        });
    }



}

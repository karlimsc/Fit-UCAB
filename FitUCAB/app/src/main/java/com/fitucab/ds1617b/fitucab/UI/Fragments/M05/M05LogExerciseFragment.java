package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Clase que manja el fragmento de Log Activity
 */

public class M05LogExerciseFragment extends Fragment implements
        View.OnClickListener
{
     //Formato de la fecha
    final SimpleDateFormat format = new SimpleDateFormat("E MMM d yyyy");

    private OnFragmentSwap _callBack;

    View _view;
    TextView _tv_date, _tv_time;

    // Valores para los parametros  de fecha
    int _year, _month, _day;
    //Valorea para la hora
    int _hour, _min;

    public M05LogExerciseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m05_log_activity, container, false);
        setupViewValues();
        return _view;
    }

       // Para innstanciar los componentes OJO: hAY QUE PONER EL _view
    private void setupViewValues() {
        _tv_date = (TextView) _view.findViewById(R.id.tv_m05_date);
        _tv_time = (TextView) _view.findViewById(R.id.tv_m05_time);

        _tv_date.setOnClickListener(this);
        _tv_time.setOnClickListener(this);

    }


    /**
     * Metodo sobreescrito para que muestre dialos de fecha  y hora
     * @param v View donde lanza el dialogo
     */
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == _tv_date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            _year = c.get(Calendar.YEAR);
            _month = c.get(Calendar.MONTH);
            _day = c.get(Calendar.DAY_OF_MONTH);
            final SimpleDateFormat format = new SimpleDateFormat("E MMM d yyyy");

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            //_tvdisplaydate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, monthOfYear);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            _tv_date.setText(format.format(calendar.getTime()));

                        }
                    }, _year, _month, _day);
            datePickerDialog.show();
        }
        if (v == _tv_time) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            _hour = c.get(Calendar.HOUR_OF_DAY);
            _min = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            _tv_time.setText(hourOfDay + ":" + minute);
                        }
                    }, _hour, _min, false);
            timePickerDialog.show();
        }
    }


}

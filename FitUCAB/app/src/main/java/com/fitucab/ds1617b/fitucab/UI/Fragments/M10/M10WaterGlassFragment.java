package com.fitucab.ds1617b.fitucab.UI.Fragments.M10;



import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;


import com.fitucab.ds1617b.fitucab.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link M10WaterGlassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link M10WaterGlassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class M10WaterGlassFragment extends Fragment  {


    private OnFragmentInteractionListener mListener;
    private ImageButton _btnAdd;
    private ImageButton _btnLess;
    private EditText _EtnDate;
    private View _view;
    private Calendar _cal;
    private String _date;
    private SimpleDateFormat _sdf;


    public M10WaterGlassFragment() {
        // Required empty public constructor

    }


    // TODO: Rename and change types and number of parameters

    public static M10WaterGlassFragment newInstance(String param1, String param2) {

        M10WaterGlassFragment fragment = new M10WaterGlassFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =inflater.inflate(R.layout.fragment_m10_water_glass, container, false);

        setupViewValues();
        activarCalendario();
        addDate();
        lessDate();





        return _view;

    }

    private void setupViewValues()
    {
        _btnLess = (ImageButton) _view.findViewById(R.id.btn_m10_lessDate);
        _btnAdd = (ImageButton) _view.findViewById(R.id.btn_m10_AddDate);
        _EtnDate= (EditText) _view.findViewById(R.id.et_m10_date);
        _sdf = new SimpleDateFormat("dd/MM/yyyy");



    }


    private void addDate()
    {
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                 _date =  _EtnDate.getText().toString();


                    _cal.setTime(_sdf.parse(_date));
                    _cal.add(Calendar.DATE,1);
                    _date = _sdf.format(_cal.getTime());
                    _EtnDate.setText(_date.toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void lessDate()
    {
        _btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     _date =  _EtnDate.getText().toString();

                    _cal.setTime(_sdf.parse(_date));
                    _cal.add(Calendar.DATE,-1);
                    _date = _sdf.format(_cal.getTime());
                    _EtnDate.setText(_date.toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });
    }


    private void activarCalendario() {
        _EtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    _cal = Calendar.getInstance(TimeZone.getDefault());
                    instanciarCalendario();
                }
                catch (Exception e){
                    System.out.print(e);


                }

                //
            }
        }
        );
    }


    public void instanciarCalendario(){
        // Create the DatePickerDialog instance
        DatePickerDialog datePicker = new DatePickerDialog(_view.getContext(), R.style.AppTheme,
                datePickerListener,_cal.get(Calendar.YEAR), _cal.get(Calendar.DAY_OF_MONTH),
                _cal.get(Calendar.MONTH));

        datePicker.setCancelable(false);
        datePicker.setTitle("Select the date");
        datePicker.show();
    }

    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            _EtnDate.setText(day1 + "/" + month1 + "/" + year1);

        }

    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

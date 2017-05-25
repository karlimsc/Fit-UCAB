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
    private SimpleDateFormat _sdf =new SimpleDateFormat("dd/MM/yyyy");


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


        return _view;

    }

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

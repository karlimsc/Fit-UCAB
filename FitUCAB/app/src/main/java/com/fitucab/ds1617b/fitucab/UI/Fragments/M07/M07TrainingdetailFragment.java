package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 **/
public class M07TrainingdetailFragment extends Fragment {

    private ListView _lt_trainingdetail;
    private Button _btn_m07_declinar;

    private View _view;
    private OnFragmentSwap _callBack;

    private Planification planification;

    public M07TrainingdetailFragment() {
        // Required empty public constructor
    }

    public M07TrainingdetailFragment(Planification planification) {
        this.planification = planification;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m07_training, container, false);
        _btn_m07_declinar = (Button)_view.findViewById(R.id.btn_m07_Decline);
        _lt_trainingdetail = ( ListView ) _view.findViewById(R.id.lv_m07_trainingdetail);

        buttonDeclinar();
        return _view;
    }

    private void  buttonDeclinar(){

        _btn_m07_declinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M07HomeFragment", null);
            }

        });

    }

}

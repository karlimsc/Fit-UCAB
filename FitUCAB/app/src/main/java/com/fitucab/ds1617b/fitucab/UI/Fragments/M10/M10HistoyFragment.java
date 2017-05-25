package com.fitucab.ds1617b.fitucab.UI.Fragments.M10;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Model.AdapterM10ListView;
import com.fitucab.ds1617b.fitucab.Model.InfoGlass;
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link M10HistoyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class M10HistoyFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    ListView _waterlist;
    String[] values = new String[]{"justin","ven","y","mamalo","fuego?"};

    public M10HistoyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*View rootView = inflater.inflate(R.layout.fragment_m10_histoy,container,false);
        //ArrayList<InfoGlass> FillWater = new ArrayList<InfoGlass>();
        //cambien getcontext, lo tienes que agarrar del rootview que creaste jajaja
        ArrayAdapter<String> adapter = new ArrayAdapter<>(rootView.getContext(),android.R.layout.simple_expandable_list_item_1,values);
        _waterlist = (ListView) rootView.findViewById(R.id.list_water);
        _waterlist.setAdapter(adapter);*/

        View rootView = inflater.inflate(R.layout.fragment_m10_histoy,container,false);
        _waterlist = (ListView) rootView.findViewById(R.id.list_water);
        ArrayList<InfoGlass> FillWater = new ArrayList<InfoGlass>();
        AdapterM10ListView adapter = new AdapterM10ListView(rootView.getContext(),FillWater);
        _waterlist.setAdapter(adapter);

        ArrayList<InfoGlass> prueba = new ArrayList<>();

        prueba.add(new InfoGlass(1,"3:00",100));
        prueba.add(new InfoGlass(2,"4:00",200));
        prueba.add(new InfoGlass(3,"5:15",500));
        prueba.add(new InfoGlass(4,"6:00",100));
        prueba.add(new InfoGlass(5,"3:00",100));
        prueba.add(new InfoGlass(6,"4:00",200));
        prueba.add(new InfoGlass(7,"5:15",500));
        prueba.add(new InfoGlass(8,"6:00",100));


        adapter.addAll(prueba);

        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

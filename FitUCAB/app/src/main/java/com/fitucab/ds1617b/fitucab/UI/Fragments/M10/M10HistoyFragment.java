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
import com.fitucab.ds1617b.fitucab.Model.Water;
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
    private static ListView _waterlist;
    static ArrayList<Water> FillWater = new ArrayList<>();
    static AdapterM10ListView adapter;
    private static ArrayList<Water>  List = new ArrayList<>();

    public M10HistoyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_m10_histoy,container,false);
        _waterlist = (ListView) rootView.findViewById(R.id.list_water);

         adapter = new AdapterM10ListView(rootView.getContext(),FillWater);
        _waterlist.setAdapter(adapter);








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



    public void setElementList (Water water)
    {
         List.add(water);

    }

    public void ViewList()
    {
        try {
            if (!adapter.isEmpty()) {
                adapter.clear();
            }
            adapter.addAll(List);
        }catch (Exception e)
        {}
    }
}

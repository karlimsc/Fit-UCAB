package com.fitucab.ds1617b.fitucab.UI.Fragments.M10;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Model.AdapterM10ListView;
import com.fitucab.ds1617b.fitucab.Model.Water;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M10WaterGlassActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.fitucab.ds1617b.fitucab.UI.Fragments.M10.M10WaterGlassFragment.user;

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
    private static ArrayList<Water>  list = new ArrayList<>();
    private Context contexto;
    private IpStringConnection Url = new IpStringConnection();
    static int idusuario =1;
    M10WaterGlassActivity m10 = new M10WaterGlassActivity();
    static M10WaterGlassFragment m10w;
     Gson gson = new Gson();
    Water water = new Water();


    public M10HistoyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_m10_histoy,container,false);
        try {
            idusuario = user.getIdUser(contexto);

        }catch (Exception e)
        {

        }
        _waterlist = (ListView) rootView.findViewById(R.id.list_water);
        contexto=rootView.getContext();
         adapter = new AdapterM10ListView(rootView.getContext(),FillWater);
        _waterlist.setAdapter(adapter);
        m10w =new M10WaterGlassFragment();








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


    public void set_list(Context contexto)
    {
        String url1 = "M10_WaterGlass/GetList?time="+m10._EtnDate.getText()+"&fkp="+idusuario;
        String aux = Url.getIp()+url1;
        RequestQueue queue = Volley.newRequestQueue(contexto);
        // Request a string response from the provided URL.
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, aux,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.}
                            try {
                                list = gson.fromJson(response, new TypeToken<List<Water>>(){}.getType());

                                ViewList();



                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            finally {
                                m10.unlockbtnm();
                                m10w.unlockbtn();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    m10.unlockbtnm();
                    m10w.unlockbtn();
                }
            });

            queue.add(stringRequest);



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setElementList (Water water)
    {
         list.add(water);

    }


    public void ViewList()
    {
        try {
            if (!adapter.isEmpty()) {
                adapter.clear();
            }
            adapter.addAll(list);
        }catch (Exception e)
        {}
    }
}

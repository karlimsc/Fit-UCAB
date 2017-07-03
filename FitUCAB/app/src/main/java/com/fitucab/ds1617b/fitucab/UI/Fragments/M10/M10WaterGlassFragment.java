package com.fitucab.ds1617b.fitucab.UI.Fragments.M10;



import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Model.Water;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M10WaterGlassActivity;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link M10WaterGlassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link M10WaterGlassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class M10WaterGlassFragment extends Fragment {

    M10WaterGlassActivity m10 = new M10WaterGlassActivity();
    M10HistoyFragment m10h = new M10HistoyFragment();
    private OnFragmentInteractionListener mListener;

    public  static ImageButton _btnAddWater;
    public static ImageButton _btnLessWater;
    public EditText _EtnWater;
    private Gson gson;
    private  String fecha;
    private Context contexto;
    private  Water water;
    private View _view;
    static int idusuario =1;
    private IpStringConnection Url = new IpStringConnection();
    public  static EditText _Etml;
    private static ImageButton addwater ;
    private static ImageButton lesswater;
     static ManagePreferences user = new ManagePreferences();



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

        _view = inflater.inflate(R.layout.fragment_m10_water_glass, container, false);
        contexto = _view.getContext();
        try {
            idusuario = user.getIdUser(contexto);

        }catch (Exception e)
        {}
            Setup();
        addWater();
        deletWater();



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

    public void Setup()
    {
        try {


            _btnAddWater = (ImageButton) _view.findViewById(R.id.btn_m10_AddWater);
            _btnLessWater = (ImageButton) _view.findViewById(R.id.btn_m10_lessWater);
            _EtnWater = (EditText) _view.findViewById(R.id.Et_m10_water);
            addwater = (ImageButton) _view.findViewById(R.id.btn_m10_addwaterMain);
            lesswater = (ImageButton) _view.findViewById(R.id.btn_m10_lessWaterMain);
            _Etml = (EditText) _view.findViewById(R.id.tv_m10_ml);



            lesswatermain();
            addwatermain();
            gson = new Gson();
        }catch (Exception e){

        }
        }


    public void addWater()
    {

        _btnAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addwateraux();
            }
        });
    }

    public void deletWater()
    {
        _btnLessWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blockbtn();
                String url1 = "M10_WaterGlass/DeletLast?time=" + m10._EtnDate.getText() + "&fkp="+idusuario;
                String aux = Url.getIp() + url1;
                RequestQueue queue = Volley.newRequestQueue(contexto);
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, aux,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.}
                                try {
                                    water = gson.fromJson(response, Water.class);
                                    _EtnWater.setText(water.get_cantidad().toString());
                                     m10h.set_list(_view.getContext());


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    unlockbtn();
                        m10.unlockbtnm();
                    }
                });
                // Add the request to the RequestQueue.
                try {

                    queue.add(stringRequest);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addwateraux()
    {
        blockbtn();
        String url1 = "M10_WaterGlass/addWater?time="+m10._EtnDate.getText()+"&glasstype="+_Etml.getText()+"&fkp="+idusuario;
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
                                water = gson.fromJson(response, Water.class);
                                _EtnWater.setText(water.get_cantidad().toString());
                                m10h.set_list(_view.getContext());




                            } catch (Exception e) {
                                unlockbtn();
                                m10.unlockbtnm();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    unlockbtn();
                    m10.unlockbtnm();
                }
            });

            queue.add(stringRequest);



        }catch (Exception e){
            e.printStackTrace();
        }

    }
        public void blockbtn()
    {
        addwater.setEnabled(false);
        lesswater.setEnabled(false);
        _btnAddWater.setEnabled(false);
        _btnLessWater.setEnabled(false);

    }
        public void unlockbtn() {
            addwater.setEnabled(true);
            lesswater.setEnabled(true);
        _btnAddWater.setEnabled(true);
        _btnLessWater.setEnabled(true);
        }


    public void setCant (String cant)
    {
        _EtnWater.setText(cant);
    }

    public void addwatermain()
    {
        addwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int ml = Integer.parseInt(_Etml.getText().toString());
                    if (ml+100<=2500) {
                        ml += 100;

                        _Etml.setText(Integer.toString(ml));
                    }
                }catch (Exception e){}
            }
        });
    }


    public void lesswatermain()
    {
        lesswater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try{
                int m2 =Integer.parseInt(_Etml.getText().toString());
                if (m2-100 >0)
                {    m2 -= 100;
                    _Etml.setText(Integer.toString(m2));
                }

            }catch (Exception e){}

            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package com.fitucab.ds1617b.fitucab.UI.Fragments.M02;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.M02Exception;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.fitucab.ds1617b.fitucab.Helper.ManagePreferences.getIdUser;

/**
 * Clase M02AccountFragment que maneja el fragmeto de perfil
 *
 * @author  Mario Salazar, Juan Mendez, David Garcia
 * @version  1.0
 */
public class M02AccountFragment extends Fragment {

    private View _view;
    private EditText _et_m02_username;
    private EditText _et_m02_email;
    private EditText _et_m02_phone;
    private EditText _et_m02_sexo;
    private String TAG= "FitUCAB";
    private User user =new User();
    private IpStringConnection ip= new IpStringConnection();
    private String identi, email, name, phone;
    ManagePreferences manageId = new ManagePreferences();
    View rootView;


    /**
     * Constructor para crear el fragmento
     *
     */
    public M02AccountFragment() {
        // Required empty public constructor
    }


    /**
     * Void onCreateView que genera la vista Fragment_m02_account
     * @param inflater layout inflater para instaciar la vista
     * @param  container container donde esta todo el grupo de la vista
     * @param  savedInstanceState el estado de la instancia
     *  @return view retorna una vista
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view= inflater.inflate(R.layout.fragment_m02_account, container, false);

        initComponentes(_view);
        return _view;

    }



    /**
     * Void initcomponentes donde se inicializan todos los componentes
     * @param view Vista donde se encuentran los bontones y componentes de la vista
     */
    private void initComponentes(View view) {
        _et_m02_email = (EditText) view.findViewById(R.id.et_m02_email);
        _et_m02_phone = (EditText) view.findViewById(R.id.et_m02_phone);
        _et_m02_username = (EditText) view.findViewById(R.id.et_m02_username);
        _et_m02_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPutWebService(user);
            }
        });
        _et_m02_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(_et_m02_email.getWindowToken(), 0);
                   user.set_email( _et_m02_email.getText().toString());
                    toPutWebService(user);

                }
                if (keyCode == KeyEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(_et_m02_email.getWindowToken(), 0);
                    user.set_email( _et_m02_email.getText().toString());
                    toPutWebService(user);
                }
                return false;
            }
        });
        _et_m02_username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(_et_m02_username.getWindowToken(), 0);
                    user.set_username( _et_m02_username.getText().toString());
                    toPutWebService(user);
                }
                if (keyCode == KeyEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(_et_m02_username.getWindowToken(), 0);
                    user.set_username( _et_m02_username.getText().toString());
                    toPutWebService(user);
                }
                return false;
            }
        });
        _et_m02_phone.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(_et_m02_phone.getWindowToken(), 0);
                    user.set_phone( _et_m02_phone.getText().toString());
                    toPutWebService(user);
                }
                if (keyCode == KeyEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(_et_m02_phone.getWindowToken(), 0);
                    user.set_phone( _et_m02_phone.getText().toString());
                    toPutWebService(user);

                }
                return false;
            }
        });
        toAskWebService();
    }

    private void toPutWebService(User user) {
        try {
            identi = String.valueOf(user.get_idUser());

            if (user.get_email() != null)
                email =  user.get_email();
            if (user.get_username() != null)
                name = user.get_username();
            if (user.get_phone() != null)
                phone = user.get_phone();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            int id= preferences.getInt("idUser",0);
            RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
            String webUrl= ip.getIp()+"M02Users/userId?id="+id+"&username="+name
                    +"&email="+email+"&phone="+phone;
            Log.i(TAG, "toAskWebService: "+webUrl);
            JsonObjectRequest jsonrequest= new  JsonObjectRequest(Request.Method.GET, webUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponse: "+response.toString());
                    //setJsonView(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, " ERROR"+ error.toString());
                }
            });

            requestQueue.add(jsonrequest);
            throw new M02Exception();
        }catch (M02Exception e){
            e.toString();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * VOID toAskWebService que realiza las peticiones al webservice
     *
     */
    private void toAskWebService() {
        try {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            int id= preferences.getInt("idUser",0);
            RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
            String webUrl= ip.getIp()+"M02Users/"+id;
            Log.i(TAG, "toAskWebService: "+webUrl);
            JsonObjectRequest jsonrequest= new  JsonObjectRequest(Request.Method.GET, webUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponse: "+response.toString());
                    setJsonView(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, " ERROR"+ error.toString());
                }
            });
            requestQueue.add(jsonrequest);
            throw new M02Exception();
        }catch (M02Exception e){
            e.toString();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * VOID setJsonView que setea todos los componentes de la vista con los valores
     * @param response Objeto Json que viene del webservice
     */
    private void setJsonView(JSONObject response) {
        try {

            String username= response.getString("user");
            String email= response.getString("email");
            String phone = response.getString("phone");
            _et_m02_username.setText(username);
            _et_m02_phone.setText(phone);
            _et_m02_email.setText(email);
          user.set_username(username);
            user.set_email(email);
            user.set_phone(phone);

            throw new M02Exception();
        }catch (M02Exception e){
            e.toString();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){

            e.printStackTrace();
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}

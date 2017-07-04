
package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M04NotificationActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fitucab.ds1617b.fitucab.Helper.M01Util.checkInsertResponse;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.getInstaceDialog;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.showToast;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.validateExceptionMessage;
import static com.fitucab.ds1617b.fitucab.Helper.ManagePreferences.getIdUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class M01SignUpFragment extends Fragment {

    private OnFragmentSwap _callBack;
    private Button _btnRegistrar;
    private EditText _etBirthdate;
    private EditText _etUsernameRegistry;
    private EditText _etPasswordRegistry;
    private EditText _etEmailRegistry;
    private EditText _etPhone;
    private EditText _etHeight;
    private EditText _etWeight;
    private RadioButton _rbSexFem;
    private RadioButton _rbSexMale;
    private View _view;

    /**
     * Constructor vacio
     */
    public M01SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {

            _callBack = (OnFragmentSwap) activity;

        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    /**
     * Metodo encargado de instanciar la vista, hacer los llamados a los metodos de listener de los componentes de la vista.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =  inflater.inflate(R.layout.fragment_m01_sign_up, container, false);
        setupViewValues();
        instantiateComponents();
        manageBtnRegistrar();
        activateCalendar();
        Context context=getContext();

        return _view;
    }
    /**
     * Metodo encargado de cambiar de actividad al realizar la accion de seleccionar
     * el boton Registar.
     */
    private void manageBtnRegistrar() {

        _btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String sex="";
               String username = _etUsernameRegistry.getText().toString();
               String password = _etPasswordRegistry.getText().toString();
               String email = _etEmailRegistry.getText().toString();
               String phone = _etPhone.getText().toString();
               String birthdate = _etBirthdate.getText().toString();
               String weight = _etWeight.getText().toString();
               String height = _etHeight.getText().toString();

                if(_rbSexFem.isChecked()) {
                    sex = "f";
                }
                else{
                    sex="m";
                }

                getRetrofit(username,password,email,sex,phone,birthdate,weight,height);
               // makeInsertUser(username,password,email,sex,phone,birthdate,weight,height);

            }
        });
    }

    /**
     * Metodo que activa el calendario.
     */
    private void activateCalendar(){

        _etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instantiateCalendar();
            }
        });
    }

    /**
     * Metodo que prepara los componentes de la vista
     */
    private void setupViewValues() {

        _btnRegistrar = (Button) _view.findViewById(R.id.btn_m01_entrar);
        _etBirthdate = (EditText) _view.findViewById(R.id.et_m01_fechanac);

    }

    Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date

    /**
     * Metodo encargado de instanciar el calendario.
     */
    public void instantiateCalendar(){
        // Create the DatePickerDialog instance
        DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.AppTheme,
        datePickerListener,cal.get(Calendar.YEAR), cal.get(Calendar.DAY_OF_MONTH),
        cal.get(Calendar.MONTH));
        cal.add(Calendar.YEAR,0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Calendar calmax = Calendar.getInstance();
        calmax.set(year-15, 11, 31);
        Calendar calmin = Calendar.getInstance();
        calmin.set(year-80, 0, 1);
        datePicker.getDatePicker().setMaxDate(calmax.getTimeInMillis());
        datePicker.getDatePicker().setMinDate(calmin.getTimeInMillis());
        datePicker.setCancelable(false);
        datePicker.setTitle("Select the date");
        datePicker.show();
    }

    /**
     * Metodo para instanciar los componentes del fragmento
     */
    private void instantiateComponents(){

        _etUsernameRegistry= (EditText) _view.findViewById(R.id.et_m01_userRegistry);
        _etPasswordRegistry= (EditText) _view.findViewById(R.id.et_m01_passwordRegistry);
        _etEmailRegistry= (EditText) _view.findViewById(R.id.et_m01_emailRegistro);
        _etPhone = (EditText) _view.findViewById(R.id.et_m01_phone);
        _rbSexFem = (RadioButton) _view.findViewById(R.id.rb_m01_female);
        _rbSexMale= (RadioButton) _view.findViewById(R.id.rb_m01_male);
        _etHeight= (EditText) _view.findViewById(R.id.et_m01_height);
        _etWeight= (EditText) _view.findViewById(R.id.et_m01_weight);

    }

    /**
     * metodo con el que hago la validacion de mis componentes
     * @param username
     * @param email
     * @param phone
     * @param password
     * @param birthdate
     * @param weight
     * @param height
     * @return el error que se genero
     */
    private String validateComponents(String username, String email, String phone,
                                       String password, String birthdate, String weight, String height ){
        String response = "ok";
        double _weight = 0;
        double _height = 0;

        try{

            _weight = Double.parseDouble(weight);
            _height = Double.parseDouble(height);

        }catch (Exception ex){

        }
        Pattern pat = Pattern.compile("[\\w-]+");
        Matcher mat = pat.matcher(username);
        if ((!username.equals("")) && (!password.equals("")) && (!email.equals("")) && (!phone.equals("")) && (!birthdate.equals("")) && (!weight.equals("")) && (!height.equals(""))) {
            if (mat.matches()) {
                if (username.length() >= 4) {
                    if (password.length() >= 6) {
                        pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                        mat = pat.matcher(email);
                        if (mat.find()) {
                            if ((phone.length() >= 11)) {
                                if ((_weight >= 30) && (_weight <= 300)) {
                                    if ((_height >= 1) && (_height <= 3)) {
                                        return response;
                                    } else {
                                        return getString(R.string.m01_errorHeightOutOfRange);
                                    }
                                } else {
                                    return getString(R.string.m01_errorWeightOutOfRange);
                                }
                            } else {
                                return getString(R.string.m01_errorPhoneTooShort);
                            }
                        } else {
                            return getString(R.string.m01_errorInvalidEmail);
                        }
                    } else {
                        return getString(R.string.m01_errorPasswordTooShort);
                    }
                } else {
                    return getString(R.string.m01_errorUsernameTooShort);
                }
            } else {
                return getString(R.string.m01_errorUsernameSpecialChar);
            }
        }else{
           return getString(R.string.m01_errorNullFields);
        }

    }


    /**
     *Le asigno al editText lo seleccionado en el calendario
     */
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);

            _etBirthdate.setText(day1 + "/" + month1 + "/" + year1);
        }
    };


    /**
     * metodo con el que hago la peticion al servicio web
     * @param username
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
     * @param weight
     * @param height
     */
    public void getRetrofit(String username, String password,String email,String sex,
                            String phone, String birthdate, String weight, String height){

        if (validateComponents(username,email,phone,password, birthdate, weight, height).equals("ok")) {

            ApiEndPointInterface apiService = ApiClient.getClient().create(ApiEndPointInterface.class);
            Call<User> call = apiService.insertRegistry(username, password, email, sex, phone, birthdate, weight, height);

            final MaterialDialog dialog =getInstaceDialog(getContext());

            call.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    dialog.dismiss();

                    try {

                        User user = response.body();
                        if (checkInsertResponse(user,getContext()) ){
                            onCompleted(user);
                            int id = getIdUser(getContext());
                            M04NotificationActivity settings = new M04NotificationActivity();
                            settings.insert(true,true,true,true,true,true,true, "es", "km", 5, id);
                            System.out.println(id);
                            _callBack.onSwapActivity("M02HomeActivity", null);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("No es problema de bd ni internet");

                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    dialog.dismiss();

                    String error=t.getMessage();
                    String errorResult= validateExceptionMessage(error,getContext());
                    showToast(getContext(),errorResult);
                    System.out.println("ERROR"+errorResult);
                    System.out.println("ERROR"+t.getMessage());
                    //Toast.makeText(getContext(), errorResult, Toast.LENGTH_SHORT).show();
                }

            });
        }
        else {
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorUsernameSpecialChar))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorUsernameSpecialChar),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorUsernameTooShort))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorUsernameTooShort),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorInvalidEmail))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorInvalidEmail),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorPhoneTooShort))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorPhoneTooShort),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorPasswordTooShort))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorPasswordTooShort),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorNullFields))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorNullFields),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorWeightOutOfRange))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorWeightOutOfRange),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(username,email,phone,password, birthdate, weight, height).equals(getString
                    (R.string.m01_errorHeightOutOfRange))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorHeightOutOfRange),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Metodo con el que escribo en memoria interna del telefono
     * @param user
     */
    public void onCompleted(User user){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("idUser", user.get_idUser());
        editor.putString("username", user.get_username());
        editor.putString("password", user.get_password());
        editor.putString("email", user.get_email());
        editor.putString("sex", String.valueOf(user.get_sex()));
        editor.putString("phone", user.get_phone());
        editor.putString("birthdate", user.get_birthdate());
        editor.putFloat("height", user.get_height());
        editor.putFloat("weight", user.get_weight());

        editor.commit();

    }

   /* public void makeInsertUser(String username,String password,String email,String sex,
                               String phone,String birthdate,String weight,
                               String height)
    {                                     // Aqui va ManagePreferences.getIdUser()

        IpStringConnection IP= new IpStringConnection();
        final Gson gson = new Gson();
        
        String consult = IP.getIp()+"M01_ServicesUser/insertRegistry?username="+username+"&" +
                "password="+password+"&email="+email+"&sex="+sex+"&phone="+phone+"&birthdate="+birthdate+"&" +
                "weight="+weight+"&height="+height;

        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int id_us = 0;

                                ArrayList<User> at = new ArrayList<User>();

                                ArrayList<String> tab = new ArrayList<String>();

                                at = gson.fromJson(response,
                                        new TypeToken<List<User>>(){}.getType());
                                ArrayList<User> _User = at;
                                
                                for (int i = 0; i < at.size(); i++ ){
                                    id_us = Integer.valueOf(at.get(i).get_idUser());
                                }

                                Toast.makeText(getContext(), String.valueOf(id_us),
                                        Toast.LENGTH_SHORT).show();

                            }
                        }, new com.android.volley.Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(), R.string._tst_m05_messagereload,
                                Toast.LENGTH_SHORT).show();

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }*/


}


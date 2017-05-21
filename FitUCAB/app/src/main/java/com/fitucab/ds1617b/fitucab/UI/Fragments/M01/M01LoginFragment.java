package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class  M01LoginFragment extends Fragment {

    private TextView _tvOlvidoClave;
    private Button _btnEntrarLogin;
    private View _view;
    private OnFragmentSwap _callBack;
    private EditText _etUserLogin;
    private EditText _etPasswordLogin;

    public M01LoginFragment() {
    }

    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
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
     * Metodo encargado de instanciar la vista
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return retorna la vista del Login
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        _view = inflater.inflate(R.layout.fragment_m01_login, container, false);
        instanciarComponentes();
        manageChangeFragmentRecovery();
        manageButtonEntrar();
        return _view;
    }
    /**
     * metodo que realiza el cambio a la vista recuperar contraseña.
     */
    private void manageChangeFragmentRecovery() {

        _tvOlvidoClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M01RecoveryFragment",null);
            }
        });

    }

    /**
     * metodo de listener del boton entrar, para realizar el cambio de actividad.
     */
    private void manageButtonEntrar(){
        _btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameLogin= _etUserLogin.getText().toString();
                String passwordLogin= _etPasswordLogin.getText().toString();
                 getRetrofit(usernameLogin,passwordLogin);
                //_callBack.onSwapActivity("M02HomeActivity",null);
            }
        });
    }

    /**
     * Metodo encargado para instanciar los componentes de esta vista
     */
    private void instanciarComponentes (){

        _tvOlvidoClave=(TextView) _view.findViewById(R.id.tv_m01_olvidoClave);
        _btnEntrarLogin=(Button) _view.findViewById(R.id.btn_m01_aceptarLogin);
        _etUserLogin=(EditText) _view.findViewById(R.id.et_m01_usuarioCorreo);
        _etPasswordLogin=(EditText) _view.findViewById(R.id.et_m01_passwordLogin);

    }

    /**
     * Metodo para hacer las llamadas a los SW y hacer el login
     * @param usernameLogin
     * @param passwordLogin
     */
     public void getRetrofit(String usernameLogin, String passwordLogin){

         ApiEndPointInterface apiService= ApiClient.getClient().create(ApiEndPointInterface.class);
         Call<User> call= apiService.loginUser(usernameLogin,passwordLogin);
         call.enqueue(new Callback<User>() {

             @Override
             public void onResponse(Call<User> call, Response<User> response) {

                 try{

                     User user = response.body();
                     onCompleted(user);
                     System.out.println("Hice bien la consulta");
                 }
                 catch (Exception e){
                     e.printStackTrace();
                     System.out.println("Hice MAL la consulta");

                 }

             }

             @Override
             public void onFailure(Call<User> call, Throwable t) {

                 System.out.println("FALLO TODO");

             }
         });
     }

    /**
     * Metodo para guardar en la memoria interna del telefono
     * @param user recibe el usuario que se obtuvo del SW
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
}

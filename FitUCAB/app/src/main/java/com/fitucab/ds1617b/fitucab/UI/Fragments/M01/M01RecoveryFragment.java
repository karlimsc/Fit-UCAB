
package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

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
public class  M01RecoveryFragment extends Fragment {

    private TextView _printTextTV;
    private Button _btnChangeActivity;
    private View _view;
    private OnFragmentSwap _callBack;
    private EditText _etEmailRecovery;

    /**
     * constructor vacio
     */
    public M01RecoveryFragment() {
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
     * Metodo encargado de instanciar la vista Recuperación de contraseña.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m01_recovery, container, false);
        instantiateComponents();
        manageButtonRecovery();
        Context context=getContext();
       /* setupViewValues();*/
        return _view;
    }

    /**
     * Metodo encargado para instanciar los componentes de esta vista
     */
    private void instantiateComponents(){

        _printTextTV=(TextView) _view.findViewById(R.id.tv_m01_recuperacionContra);
        _btnChangeActivity=(Button) _view.findViewById(R.id.btn_m01_enviarCorreo);
        _etEmailRecovery=(EditText) _view.findViewById(R.id.et_m01_correoRecovery);

    }

    /**
     * metodo de listener del boton entrar, para realizar el cambio de actividad.
     */
    private void manageButtonRecovery(){
        _btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailRecovery= _etEmailRecovery.getText().toString();
                getRetrofit(emailRecovery);
            }
        });
    }

    /**
     * Metodo con el que valido los componentes del fragmento
     * @param email
     * @return
     */
    private String validateComponents(String email){
        String response = "ok";
        Pattern pat;
        Matcher mat;
        if ((!email.equals(""))) {
            pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            mat = pat.matcher(email);
            if (mat.find()) {
                return response;
            } else {
                return getString(R.string.m01_errorInvalidEmail);
            }
        }else{
            return getString(R.string.m01_errorNullFields);
        }

    }

    /**
     * Metodo para hacer las llamadas a los SW y hacer la recuperación de contraseña
     * @param emailRecovery
     */
    public void getRetrofit(String emailRecovery){

        if (validateComponents(emailRecovery).equals("ok")) {

            ApiEndPointInterface apiService= ApiClient.getClient().create(ApiEndPointInterface.class);
            Call<User> call= apiService.restorePassword(emailRecovery);
            //Call<User> call= apiService.restorePassword(emailRecovery);
            final MaterialDialog dialog =getInstaceDialog(getContext());

            call.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    dialog.dismiss();
                    try {

                        User user = response.body();
                        if (checkInsertResponse(user, getContext())) {

                            Toast.makeText(getContext(),"El correo con sus datos fue enviado", Toast.LENGTH_SHORT);
                            _callBack.onSwap("M01LoginFragment", null);
                        }else
                        {
                            Toast.makeText(getContext(),"El email no se encuentra registrado", Toast.LENGTH_SHORT);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Hice mal la consulta");

                        //Aqui habria que poner que se hace luego de que se envie el correo
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    dialog.dismiss();
                    String error=t.getMessage();
                    String errorResult= validateExceptionMessage(error,getContext());
                    showToast(getContext(),errorResult);
                }
            });

        }
        else{
            if (validateComponents(emailRecovery).equals(getString
                    (R.string.m01_errorInvalidEmail))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorInvalidEmail),
                        Toast.LENGTH_LONG).show();
            }
            if (validateComponents(emailRecovery).equals(getString
                    (R.string.m01_errorNullFields))) {

                Toast.makeText(getContext(),
                        getString(R.string.m01_errorNullFields),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /*private void setupViewValues() {
        _printTextTV = (TextView) _view.findViewById(R.id.tv_m01_prueba);
        Bundle incomingData = getArguments();
        String textFromView = incomingData.getString("text");
        System.out.println(textFromView);
        _printTextTV.setText(textFromView);
        _btnChangeActivity = (Button) _view.findViewById(R.id.btn_m01_change_activity);

    }*/

}

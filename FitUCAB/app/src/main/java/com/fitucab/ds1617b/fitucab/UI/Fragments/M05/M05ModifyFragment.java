package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M05PrincipalActivity;

/**
 * Fragment del tipo dialogo que permite eliminar y modicicar actividad
 */

public class M05ModifyFragment extends Fragment {
    TextView _tv_date, _tv_time,_tv_sport;


    EditText _et_Calories,_et_km;

    Button _modify;
    ImageView _delete, _close;

    private Activit _activit;


    View _view;
    private OnFragmentSwap _callBack;

    String _idActivity;


    public M05ModifyFragment() {
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_m05_modify, container, false);
        setupViewValues();
        inflateData();
        return _view;
    }

    private void setupViewValues() {
         _delete = (ImageView) _view.findViewById(R.id.iv_m05_deleteactivity);
         _modify = (Button) _view.findViewById(R.id.btn_m05_updateactivity);
        _close =  (ImageView) _view.findViewById(R.id.iv_m05_close);
       //Campos para los datos de la actividad
        _et_Calories= (EditText) _view.findViewById(R.id.editText);
        _tv_date = (TextView) _view.findViewById(R.id.tv_m05_dta);
        _tv_time=(TextView) _view.findViewById(R.id.tv_m05_tme);
        _tv_sport=(TextView) _view.findViewById(R.id.tv_m05_type);
        _et_km = (EditText) _view.findViewById(R.id.et_m05_dtanc);

        setHasOptionsMenu(true);

        selectedOpcionToolbar();
    }


// Aqui llena los copos can la inforacion de la actividad para posteriormente eliminarla o actualizarla
    public void inflateData(){
       // Log.e("STATIC DEL OTRO LADO ", M05PrincipalActivity.get_activit().get_name());
        _activit = M05PrincipalActivity.get_activit();
        _idActivity= String.valueOf(_activit.get_id());

        // Llena los componentes de la ventana
        String dato ;
        dato = String.valueOf(_activit.get_calor());
            _et_Calories.setText(dato);
        dato = String.valueOf(_activit.get_km());
            _et_km.setText(dato);
        dato = _activit.get_startime();
            _tv_time.setText(dato);
        dato = _activit.get_date();
            _tv_date.setText(dato);
        dato = _activit.get_name();
            _tv_sport.setText(dato);

    }


    /**
     * Metodo para la seleccion de alguna opcion en el toolbar
     */

    public void selectedOpcionToolbar(){
       // Si se escoge la opcion de eliminar, realizar accion correspondiente
       _delete.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialogConfirmation();
                   }
               }
       );
       // Si se escoge modificar, realizar la accion correspondiente
       _modify.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       makeUpdateKm();
                       makeUpdateCal();
                   }
               }

       );
        _close.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _callBack.onSwap("M05PrincipalActivityFragment",null);
            }
        });

   }

    /**
     * Dialogo emergente para confirmacion de eliminacion de actividad
     */


    public void dialogConfirmation() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(R.string._ttl_m05_questiondeleteactivity);
        builder.setMessage(R.string._dlg_m05_quetiondeleteactivity)
                .setPositiveButton(R.string._dlg_m05_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        makeDelete();
                        dialog();

                    }
                })
                .setNegativeButton(R.string._dlg_m05_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Dialogo emergente una vez borrado la actividad
     */
    public void dialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(R.string._ttl_m05_information);
        builder.setMessage(R.string._tst_m05_messageconfirmation)
                .setPositiveButton(R.string._dlg_m05_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        _callBack.onSwap("M05PrincipalActivityFragment",null);
                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void dialogDone() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(R.string._ttl_m05_information);
        builder.setMessage(R.string._dlg_m05_doneUpdate)
                .setPositiveButton(R.string._dlg_m05_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        _callBack.onSwap("M05PrincipalActivityFragment",null);
                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void makeDelete()
    {
        String consult =M05UrlConsul._urlDeleteAct+_idActivity;

        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.e("RESPONSE ", response);
                                Toast.makeText(getContext(), R.string._tst_m05_messageupdate,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("no trajo nada","");
                        Toast.makeText(getContext(), R.string._tst_m05_messageerrordelete,
                                Toast.LENGTH_SHORT).show();

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


    public void makeUpdateKm()
    {
        String consult =M05UrlConsul._urlUpdateKm(_idActivity,String.valueOf(_et_km.getText()));

        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.e("RESPONSE ", response);
                                Toast.makeText(getContext(), R.string._tst_m05_messageupdate,
                                        Toast.LENGTH_SHORT).show();
                                dialogDone();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("no trajo nada","");
                        Toast.makeText(getContext(), R.string._tst_m05_messageerrormodify,
                                Toast.LENGTH_SHORT).show();

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    public void makeUpdateCal()
    {
        String consult =M05UrlConsul._urlUpdateCal(_idActivity,String.valueOf( _et_Calories.getText()));
        //Log.e("URI DE LA CONSULTAAAAAAAAAAAAA*********"+ consult);

        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.e("RESPONSE ", response);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("no trajo nada","");
                        Toast.makeText(getContext(), R.string._tst_m05_messageerrormodify,
                                Toast.LENGTH_SHORT).show();

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}

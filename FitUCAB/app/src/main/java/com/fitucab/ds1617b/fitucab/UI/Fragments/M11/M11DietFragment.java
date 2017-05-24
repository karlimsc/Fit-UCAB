package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;
import android.widget.ArrayAdapter;
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */

public class M11DietFragment extends Fragment {


    private TextView _tv_m11_cantidadCalorias;
    private AutoCompleteTextView _tv_m11_nombreAlimento;
    private EditText _tv_m11_peso;
    private ImageButton _btn_m11_agregar;
    private Spinner _spinner_comida;
    private ImageButton _btn_m11_aceptar;
    private ImageButton _btn_m11_rechazar;
    private ImageButton _btn_m11__diet_borrar;
    private TableLayout _tl_m11_listaDieta;
    private View _view;
    private int _contadorAlimentos;
    private OnFragmentSwap _callBack;

    public M11DietFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo que se llama automaticamente cuando la la actividad anfitriona usa el fragmento.
     * @param activity Recibe la actividad anfitriona en la que va a mostrarse.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m11_diet, container, false);


        _tv_m11_cantidadCalorias = (TextView) _view.findViewById(R.id.tv_m11_cantidadCalorias);
        _tv_m11_nombreAlimento = (AutoCompleteTextView) _view.findViewById(R.id.tv_m11_nombreAlimento);
        _tv_m11_peso = (EditText) _view.findViewById(R.id.tv_m11_peso);
        _btn_m11_agregar = (ImageButton) _view.findViewById(R.id.btn_m11_agregar);
        _spinner_comida = (Spinner) _view.findViewById(R.id.spinner_comida);
        _btn_m11_aceptar = (ImageButton) _view.findViewById(R.id.btn_m11_aceptar);
        _btn_m11_rechazar = (ImageButton) _view.findViewById(R.id.btn_m11_rechazar);
        _tl_m11_listaDieta = (TableLayout) _view.findViewById(R.id.tl_m11_listaDieta);
        _btn_m11__diet_borrar = (ImageButton) _view.findViewById(R.id.btn_m11_diet_borrar);
        setAlimento();
        AddListenerBack();
        return _view;
    }

    /**
     * Metodo que inicializa el listener para agregar alimentos a la dieta.
     */
    public void setAlimento(){
        _btn_m11_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAlimento =  _tv_m11_nombreAlimento.getText().toString();
                String pesoAlimento = _tv_m11_peso.getText().toString();
                LlenaTabla(nombreAlimento, pesoAlimento);
            }
        });
    }

    /**
     * Metodo que inicializa el listener para volver atras y cancelar la carga de la dieta.
     */
    public void AddListenerBack()
    {
        _btn_m11_rechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M11FoodhomeActivity",null);
            }
        });
    }

    /**
     * Metodo que realiza la carga de el alimento a la lista de la dieta
     * @param nombreAlimento Recibe nombre del alimento a cargar,
     * @param pesoAlimento Recibe el peso del alimento a cargar.
     */
    public void LlenaTabla( String nombreAlimento, String pesoAlimento)
    {
        final TableRow tableRow = new TableRow(getContext());
        _tl_m11_listaDieta.addView(tableRow);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
        TableLayout.LayoutParams.WRAP_CONTENT , TableLayout.LayoutParams.WRAP_CONTENT);
        TextView currentText = new TextView(getContext());
        currentText.setText(nombreAlimento+"             "+pesoAlimento);
        currentText.setTextSize(currentText.getText().length());
        currentText.setTextColor(Color.BLACK);
        currentText.setId(_contadorAlimentos);//Un ID para fila del TableRow
        tableRow.setLayoutParams(params);
        tableRow.addView(currentText);
        AgregaColumna(pesoAlimento , tableRow , params);
        currentText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Confirmacion para eliminar alimento de la tabla de la die
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Desea continuar con la Eliminacion?")
                        .setTitle("Advertencia")
                        .setCancelable(false)
                        //Acciones para cuando dice no
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Acciones para cuando dice si
                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Elimina el alimento del TableRow
                                _tl_m11_listaDieta.removeView(tableRow);


                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    /**
     * Metodo para agregar columna con el peso del alimento al TableLayout.
     * @param pesoAlimento Recibe el peso del alimento.
     * @param tableRow Recibe la fila sobre la cual se creara la columna.
     * @param params Recibe el Layout.
     */
    public void AgregaColumna ( String pesoAlimento , TableRow tableRow, TableLayout.LayoutParams params )
    {
        TextView currentText = new TextView(getContext());
        currentText.setText(pesoAlimento);
        currentText.setTextSize(pesoAlimento.length());
        currentText.setTextColor(Color.BLACK);
        currentText.setId(_contadorAlimentos);
        tableRow.setLayoutParams(params);
        tableRow.addView(currentText);
    }

}

package com.example.juanmacedo.fitucab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Logros.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Logros extends Fragment {

    //**VARIABLES
    TextView _texto, _detalle;
    String _nombreLogro;
    BarChart _barra;
    BarData _data;
    BarDataSet _set1;
    IBarDataSet _barDataSet;
    ArrayList<BarEntry> _entradas;
    ArrayList<String> _dias;
    ArrayList<IBarDataSet> _barDataSets;
    public static String _TAG = "Logros";
    private OnFragmentInteractionListener _mListener;
    int _grafica;
    //FIN VARIABLES

    public Logros(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout._graficobarra, null);

        _nombreLogro = getArguments().getString("item");
        _texto = (TextView)view.findViewById(R.id.id_texto);
        //IMPRIME EN EL LOG PARA PRUEBAS
        Log.d("DATOOO2", getArguments().getString("item"));
        //IMPRIME EN PANTALLA ANDROID DE FORMA EMERGENTE
        Toast.makeText(getActivity(), getArguments().getString("item"), Toast.LENGTH_LONG).show();
        //DEPENDIENDO DEL RETO, ENTRA EN UN IF DIFERENTE PARA GRAFICA
        _grafica = 1;
        //A DISCUTIR SI NO SE USARA GRAFICA
        if(_grafica == 1){

            _texto = (TextView) view.findViewById(R.id.logro);
            _texto.setText(getArguments().getString("item"));

            _detalle = (TextView) view.findViewById(R.id.detalleLogro);
            _detalle.setText("Logro desbloqueado despues de haber completado los 5 km de distancia en trote");
            //ID DEL BARCHART
            _barra = (BarChart) view.findViewById(R.id.barra);

            //FUNCIONES QUE LLENAN
            Entradas();
            Etiquetas();

            _barDataSets = new ArrayList<>();

            //SE INTRODUCE DATOS CON ETIQUETA
            _set1 = new BarDataSet(_entradas, "Semana");
            _barDataSets.add(_set1);


           // _data = new BarData(_dias, _set1);
            _barra.setData(_data);
            _barra.setEnabled(true);
        }

        return view;


    }

    public void Entradas(){

        //ARRAY DE DATOS DEL LOGRO
        _entradas = new ArrayList<>();

        _entradas.add(new BarEntry(44f,0, "Lunes"));
        _entradas.add(new BarEntry(88f,1, "Martes"));
        _entradas.add(new BarEntry(66f,2, "Miercoles"));
        _entradas.add(new BarEntry(12f,3, "Jueves"));
        _entradas.add(new BarEntry(19f,4, "Viernes"));
        _entradas.add(new BarEntry(91f,5, "Sabado"));

    }

    public void Etiquetas(){

        //ARRAY DE DIAS DE ETIQUETA
        _dias = new ArrayList<>();

        _dias.add("Lunes");
        _dias.add("Martes");
        _dias.add("Miercoles");
        _dias.add("Jueves");
        _dias.add("Viernes");
        _dias.add("Sabado");
        _dias.add("Domingo");
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (_mListener != null) {
            _mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            _mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mListener = null;
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

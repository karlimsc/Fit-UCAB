package com.example.juanmacedo.fitucab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Logros.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Logros extends Fragment {

    //**VARIABLES
    TextView texto, detalle;
    String nombreLogro;
    BarChart barra;
    BarData Data;
    BarDataSet set1;
    IBarDataSet barDataSet;
    ArrayList<BarEntry> entradas;
    ArrayList<String> dias;
    ArrayList<IBarDataSet> barDataSets;
    public static String TAG = "Logros";
    private OnFragmentInteractionListener mListener;
    //VARIABLES

    public Logros(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.graficobarra, null);

        nombreLogro = getArguments().getString("item");
        texto = (TextView)view.findViewById(R.id.id_texto);
        Log.d("DATOOO2", getArguments().getString("item"));                                         //IMPRIME EN EL LOG PARA PRUEBAS
        Toast.makeText(getActivity(), getArguments().getString("item"), Toast.LENGTH_LONG).show();  //IMPRIME EN PANTALLA ANDROID DE FORMA EMERGENTE

        int grafica = 1;                                                                            //DEPENDIENDO DEL RETO, ENTRA EN UN IF DIFERENTE PARA GRAFICA
        if(grafica == 1){                                                                           //A DISCUTIR SI NO SE USARA GRAFICA

            texto = (TextView) view.findViewById(R.id.logro);
            texto.setText(getArguments().getString("item"));

            detalle = (TextView) view.findViewById(R.id.detalleLogro);
            detalle.setText("Logro desbloqueado despues de haber completado los 5 km de distancia en trote");

            barra = (BarChart) view.findViewById(R.id.barra);                                       //ID DEL BARCHART

            Entradas();                       //FUNCION QUE LLENA
            Etiquetas();                      //FUNCION QUE LLENA

            barDataSets = new ArrayList<>();


            set1 = new BarDataSet(entradas, "Semana");                                               //SE INTRODUCE DATOS CON ETIQUETA
            barDataSets.add(set1);


            Data = new BarData(dias, set1);
            barra.setData(Data);
            barra.setEnabled(true);
        }

        return view;


    }

    public void Entradas(){

        entradas = new ArrayList<>();     //ARRAY DE DATOS DEL LOGRO

        entradas.add(new BarEntry(44f,0, "Lunes"));
        entradas.add(new BarEntry(88f,1, "Martes"));
        entradas.add(new BarEntry(66f,2, "Miercoles"));
        entradas.add(new BarEntry(12f,3, "Jueves"));
        entradas.add(new BarEntry(19f,4, "Viernes"));
        entradas.add(new BarEntry(91f,5, "Sabado"));

    }

    public void Etiquetas(){

        dias = new ArrayList<>();         //ARRAY DE DIAS DE ETIQUETA

        dias.add("Lunes");
        dias.add("Martes");
        dias.add("Miercoles");
        dias.add("Jueves");
        dias.add("Viernes");
        dias.add("Sabado");
        dias.add("Domingo");
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
}

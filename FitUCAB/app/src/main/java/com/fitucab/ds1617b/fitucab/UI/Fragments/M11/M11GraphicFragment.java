package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Diet;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;

import com.fitucab.ds1617b.fitucab.UI.Activities.M11Food;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * A simple {@link Fragment} subclass.
 */



public class M11GraphicFragment extends Fragment {

    private CheckBox _cb_m11_consumidas;
    private CheckBox _cb_m11_quemadas;
    private View _view;
    private OnFragmentSwap _callBack;

    private static final String _TAG = M11Food.class.getSimpleName();
    // variable del grafico
    private LineChart _lineChart;
    // setcomp1 contendra el conjunto de datos de la calorias quemadas
    private LineDataSet _dataQuemadas;
    // setcomp2 contendra el conjunto de datos de la calorias consumidas
    private LineDataSet _dataConsumidas;
    // setcomp3 contendra el conjunto de datos de la diferencia de las calorias quemadas y consumidas
    private LineDataSet _dataDiferencia;
    private TabLayout tabLayout;
    private ArrayList<Integer> arrayDiferencial = new ArrayList<>();
    private ArrayList<Diet> _monthCalorie;
    private ArrayList<Diet> _dayCalorie;
    private ArrayList<Diet> _weekCalorie;


    public M11GraphicFragment() {
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

        _view = inflater.inflate(R.layout.fragment_m11_graphic, container, false);
        // obtengo el grafico del archivo xml
        _lineChart = (LineChart) _view.findViewById(R.id.chart);
        // la documentacion dice que hay que poner esto.. xd
        Utils.init(getResources());
        manageCheckboxConsumidas();
        manageCheckboxQuemadas();
        manageTabs();
        verGraficoDia();

        // Inflate the layout for this fragment
        return _view;
    }

    public void manageTabs() {
        tabLayout =  (TabLayout) _view.findViewById(R.id.tablayout);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Log.i(_TAG, "Filtro por dia");
                        verGraficoDia();

                        break;
                    case 1:
                        Log.i(_TAG, "Filtro por semana");
                        verGraficoSemana();
                        break;
                    case 2:
                        Log.i(_TAG, "Filtro por mes");
                        verGraficoMes();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void manageCheckboxConsumidas() {
        _cb_m11_consumidas = (CheckBox) _view.findViewById(R.id.cb_m11_consumidas);
        _cb_m11_consumidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateDeactivateQuemadasConsumidas(_cb_m11_consumidas, "Consumidas", _dataConsumidas);
            }
        });
    }

    public void manageCheckboxQuemadas() {
        _cb_m11_quemadas = (CheckBox) _view.findViewById(R.id.cb_m11_quemadas);
        _cb_m11_quemadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateDeactivateQuemadasConsumidas(_cb_m11_quemadas, "Quemadas", _dataQuemadas);
            }
        });
    }


    public void setDataGraphic(ArrayList<Integer> dataQuemadas, ArrayList<Diet> dataConsumidas) {
        try {

            List<Entry> calQuemadas = new ArrayList<Entry>();
            List<Entry> calConsumidas = new ArrayList<Entry>();
            List<Entry> calDiferencial = new ArrayList<Entry>();

            for(int i=0; i<dataQuemadas.size(); i++){
                calQuemadas.add(new Entry( (float) i,(float) dataQuemadas.get(i)));
            }

            for(int i=0; i<dataConsumidas.size(); i++){
                calConsumidas.add(new Entry( (float) i,(float) dataConsumidas.get(i).get_calorie()));
            }

            for(int i=0; i<arrayDiferencial.size(); i++){
                calDiferencial.add(new Entry( (float) i,(float) arrayDiferencial.get(i)));
            }

            Log.i(_TAG, "Tamano del entry de consumidas: " + calConsumidas.size());
            Log.i(_TAG, "Tamano del entry de quemadas: " + calQuemadas.size());
            Log.i(_TAG, "Tamano del entry de diferencia: " + calDiferencial.size());

            // creo el dataset de caloria quemadas y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            _dataQuemadas = new LineDataSet(calQuemadas, "Quemadas");
            _dataQuemadas.setColor(Color.BLUE);
            _dataQuemadas.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria consumidas y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            _dataConsumidas = new LineDataSet(calConsumidas, "Consumidas");
            _dataConsumidas.setColor(Color.YELLOW);
            _dataConsumidas.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria diferencial y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            _dataDiferencia = new LineDataSet(calDiferencial, "Diferencial");
            _dataDiferencia.setColor(Color.GREEN);
            _dataDiferencia.setAxisDependency(AxisDependency.LEFT);

            // los LineDataSets anteriores(setComp1, setComp2 y setComp3)
            // los introduzco en una sola estructura de datos
            List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(_dataDiferencia);
            // creo el objeto LineData que es lo que se le pasa a la vista para que
            // se visualice la grafica
            LineData data = new LineData(dataSets);
            // seteo el grafico en la vista
            _lineChart.setData(data);
            // esto son propiedades de la grafica
            YAxis yAxis = _lineChart.getAxisLeft();
            XAxis xAxis = _lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            // esto refresca e inicializa la grafica
            _lineChart.invalidate(); // refresh

        }
        catch (Exception ex) {
            Log.d(_TAG, "error al intentar generar la grafica", ex);

        }

    }

    public void setDiferecial(ArrayList<Integer> dataQuemadas, ArrayList<Diet> dataConsumidas) {
        arrayDiferencial.clear();
        int q;
        int c;
        for (int i=0; i<dataConsumidas.size(); i++){
            q = dataQuemadas.get(i);
            c = dataConsumidas.get(i).get_calorie();
            arrayDiferencial.add(c-q);
        }
        Log.i(_TAG, "tamano del arreglo diferencial "+ arrayDiferencial.size());
    }


    /*
    funcion que muestra o elimina la linea que indica las calorias
    quemadas de la grafica. El metodo es llamado con la propiedad
    onClick en el archivo xml
    */
    public void activateDeactivateQuemadasConsumidas(CheckBox cb, String label, LineDataSet data){

        try {

            if (!cb.isChecked()){
                // obtengo la data del grafico
                LineData lineData = _lineChart.getData();

                // obtengo los datos pertenecientes a la linea de quemadas
                ILineDataSet quemadas = lineData.getDataSetByLabel(label,false);
                // remuevo los datos
                lineData.removeDataSet(quemadas);
                // se actualiza tanto el grafico como la variable que contiene los datos
                lineData.notifyDataChanged();
                _lineChart.notifyDataSetChanged();
                // refresco el grafico para que detecte los cambios
                _lineChart.invalidate();
            }
            // si el check esta seleccionado
            else {
                // obtengo los datos de la grafica
                LineData lineData = _lineChart.getData();
                // agrego los datos pertenecientes a calorias quemadas
                lineData.addDataSet(data);
                // notifico los cambios
                lineData.notifyDataChanged();
                _lineChart.notifyDataSetChanged();
                // refresco el grafico para que agarre los cambios
                _lineChart.invalidate();
            }

        } catch (NullPointerException e) {
            Log.w(_TAG, "Grafico no cargado", e);
            e.printStackTrace();
        } catch (Exception e){
            Log.w(_TAG, "Error durante la carga o descarga datos en la tabla", e);
        }

    }


    /*
    funcion que refrescara la grafica para poder ver los
    ultimos 12 meses de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoMes(){
        _lineChart.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //SharedPreferences.Editor editor = preferences.edit();
        //editor.putString("username", "PABLO" );
        //editor.commit();

        String usuario = ManagePreferences.getUsername(getContext());
        jsonURL.set_ip( jsonURL.getIp() + "/M11_Diet/getConsumedCalorieByMonth?username=" + usuario );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.i(_TAG, "Peticion exitosa calorias por mes");
                        _monthCalorie = new ArrayList<>();
                        _monthCalorie = gson.fromJson(response, new TypeToken<ArrayList<Diet>>(){}.getType());
                        ArrayList<Integer> prueba12mesesQ = new ArrayList<>();

                        prueba12mesesQ.add(200);
                        prueba12mesesQ.add(40);
                        prueba12mesesQ.add(100);
                        prueba12mesesQ.add(0);
                        prueba12mesesQ.add(35);
                        prueba12mesesQ.add(75);
                        prueba12mesesQ.add(0);
                        prueba12mesesQ.add(350);
                        prueba12mesesQ.add(250);
                        prueba12mesesQ.add(500);
                        prueba12mesesQ.add(200);
                        prueba12mesesQ.add(0);
                        setDiferecial(prueba12mesesQ, _monthCalorie);
                        setDataGraphic(prueba12mesesQ, _monthCalorie);
                        activateDeactivateQuemadasConsumidas(_cb_m11_quemadas, "Quemadas", _dataQuemadas);
                        activateDeactivateQuemadasConsumidas(_cb_m11_consumidas, "Consumidas", _dataConsumidas);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(_TAG, "Fallo la peticion calorias por mes: "+ error.toString());
                        Toast.makeText(_view.getContext(), "No se pudo obtener las calorias", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);

    }

    /*
    funcion que refrescara la grafica para poder ver las
    ultimas 4 semanas de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoSemana(){
        _lineChart.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //SharedPreferences.Editor editor = preferences.edit();
        //editor.putString("username", "PABLO" );
        //editor.commit();
        String usuario = ManagePreferences.getUsername(getContext());
        jsonURL.set_ip( jsonURL.getIp() + "/M11_Diet/getConsumedCalorieByWeek?username=" + usuario );
        Log.i(_TAG, "El usuario desde local storage es " + usuario);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.i(_TAG, "Peticion exitosa calorias por semana");
                        _weekCalorie = new ArrayList<>();
                        _weekCalorie = gson.fromJson(response, new TypeToken<ArrayList<Diet>>(){}.getType());
                        ArrayList<Integer> prueba4semanasQ = new ArrayList<>();

                        prueba4semanasQ.add(200);
                        prueba4semanasQ.add(40);
                        prueba4semanasQ.add(100);
                        prueba4semanasQ.add(0);
                        setDiferecial(prueba4semanasQ, _weekCalorie);
                        setDataGraphic(prueba4semanasQ, _weekCalorie);
                        activateDeactivateQuemadasConsumidas(_cb_m11_quemadas, "Quemadas", _dataQuemadas);
                        activateDeactivateQuemadasConsumidas(_cb_m11_consumidas, "Consumidas", _dataConsumidas);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(_TAG, "Fallo la peticion calorias por semana: "+ error.toString());
                        Toast.makeText(_view.getContext(), "No se pudo obtener las calorias", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);

    }

    /*
   funcion que refrescara la grafica para poder ver los
   ultimos 7 dias de calorias consumidas, quemadas y su diferencia.
   OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
   */

    public void verGraficoDia(){
        _lineChart.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //SharedPreferences.Editor editor = preferences.edit();
        //editor.putString("username", "PABLO" );
        //editor.commit();
        String usuario = ManagePreferences.getUsername(getContext());
        jsonURL.set_ip( jsonURL.getIp() + "/M11_Diet/getConsumedCalorieByDay?username=" + usuario );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.i(_TAG, "Peticion exitosa calorias por dia");
                        _dayCalorie = new ArrayList<>();
                        _dayCalorie = gson.fromJson(response, new TypeToken<ArrayList<Diet>>(){}.getType());

                        ArrayList<Integer> prueba7diasQ = new ArrayList<>();

                        // cable
                        prueba7diasQ.add(200);
                        prueba7diasQ.add(40);
                        prueba7diasQ.add(100);
                        prueba7diasQ.add(0);
                        prueba7diasQ.add(35);
                        prueba7diasQ.add(75);
                        prueba7diasQ.add(0);

                        setDiferecial(prueba7diasQ, _dayCalorie);
                        setDataGraphic(prueba7diasQ, _dayCalorie);
                        activateDeactivateQuemadasConsumidas(_cb_m11_quemadas, "Quemadas", _dataQuemadas);
                        activateDeactivateQuemadasConsumidas(_cb_m11_consumidas, "Consumidas", _dataConsumidas);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(_TAG, "Fallo la peticion calorias por dia: "+ error.toString());
                        Toast.makeText(_view.getContext(), "No se pudoeron obtener las calorias", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);


    }



}

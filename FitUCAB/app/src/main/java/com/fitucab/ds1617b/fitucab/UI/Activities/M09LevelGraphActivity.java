package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Clase M09LevelGraphActivity que maneja la actividad del grafico del nivel del Modulo 9 Gestion de Gamificacion
 *
 * @EXTENDS: AppCompatActivity
 * @AUTHORS: Juan Macedo, Cesar Boza, Bryan Teixeira
 * @VERSION: 1.0
 */
public class M09LevelGraphActivity extends AppCompatActivity {

    //Declaracion de atributos de la clase
    public IpStringConnection _ip = new IpStringConnection();
    private String _graphsURL = _ip.getIp()+"M09_ServicesGamifications/getAchievements";
    PieChart _pieChart;
    private int[] _yValues = {0, 0};
    private String[] _xValues = {"Not Completed", "Completed"};
    private int _accomplished;
    private int _notAccomplished;
    private String _error;
    private String _paramVolley = "/";

    /**
     * VOID onCreate que genera la actividad M09LevelGraphActivity
     * @param _savedInstanceState context que estara manejando la actividad
     */
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_m09_level);
        accomplishmentsRequest();

        Intent _myIntent = getIntent();
        _pieChart = (PieChart) findViewById(R.id.PieChartId);
        //Habilitamos funcion de girar la torta al tocarla
        _pieChart.setRotationEnabled(true);
        //Convertimos los valores que cargamos en porcentaje
        _pieChart.setUsePercentValues(true);
        //Habilitamos circulo interior y configuramos propiedades
        _pieChart.setHoleRadius(58f);
        _pieChart.setTransparentCircleRadius(61f);
        _pieChart.setTransparentCircleAlpha(110);
        _pieChart.setCenterText("Level: "+ _myIntent.getStringExtra("nivel"));
        _pieChart.setCenterTextSize(30);
        //Desabilitamos atributo descripcion de la grafica
        _pieChart.getDescription().setEnabled(false);
        //Funcion que agranda el tamaño de la porcion al ser tocada
        _pieChart.setHighlightPerTapEnabled(true);
        //Efecto visual inicial de la grafica
        _pieChart.animateXY(1400, 1400);

    }


    /**
     * VOID addDataSet que crea la grafica cargando todos los datos y personalizaciones
     * @PARAMS: Null
     * @RETURN: Null
     */
    private void addDataSet() {

        ArrayList<PieEntry> _entries = new ArrayList<>();
        for (int i = 0; i < _xValues.length; i++) {
            _entries.add(new PieEntry(_yValues[i], _xValues[i]
            ));
        }

        //create the data set
        PieDataSet _pieDataSet = new PieDataSet(_entries, "");
        //Espacio entre cada seccion de la grafica
        _pieDataSet.setSliceSpace(3f);
        _pieDataSet.setValueTextSize(20);
        _pieDataSet.setSelectionShift(5f);
        _pieDataSet.setDrawValues(true);

        //add colors to dataset
        ArrayList<Integer> _colors = new ArrayList<>();
        for (int c : ColorTemplate.LIBERTY_COLORS)
            _colors.add(c);
        _pieDataSet.setColors(_colors);

        //add legend to chart
        Legend _legend = _pieChart.getLegend();
        _legend.setTextSize(15);
        _legend.setForm(Legend.LegendForm.CIRCLE);
        _legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);


        //create pie data object
        PieData _pieData = new PieData(_pieDataSet);
        _pieData.setValueFormatter(new PercentFormatter());
        _pieData.setValueTextSize(20f);
        _pieChart.setEntryLabelColor(Color.BLACK);
        _pieChart.setData(_pieData);
        _pieChart.invalidate();
    }

    /**
     * VOID accomplishmentsRequest metodo que busca en base de datos todos los logros del usuario
     * @PARAMS: Null
     * @RETURN: Null
     */
    public void accomplishmentsRequest() {
        //SharedPreferences _preferences = PreferenceManager.getDefaultSharedPreferences(_context);
        //int _userID = _preferences.getInt("idUser", _user.get_idUser());
        //cableado mientras SharedPreferences no funciona
        int _userID = 2;
        //Colocamos la direccion correctamente para realizar la peticion con parametros
        _graphsURL= _graphsURL+_paramVolley+""+_userID;
        StringRequest _stringRequest = new StringRequest(_graphsURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String _response) {
                        Log.d("onResponse()", _response.toString());
                        accomplishmentsJSON(_response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onResponse()", error.toString());
            }
        });

        RequestQueue _request = Volley.newRequestQueue(this);
        _request.add(_stringRequest);
        Log.d("objetooo", _stringRequest.toString());
    }

    /**
     * VOID accomplishmentsJSON metodo para manejar el JSON obtenido del metodo accomplishmentsRequest
     * @param _json variable JSON obtenido del metodo accomplishmentsRequest
     */
    private void accomplishmentsJSON(String _json) {
        JSONObject _jsonObject = null;
        try {
            _jsonObject = new JSONObject(_json);
            _accomplished = Integer.parseInt(_jsonObject.getString("achieve"));
            _notAccomplished = Integer.parseInt(_jsonObject.getString("unachieve"));
            _yValues[0] = _notAccomplished;
            _yValues[1] = _accomplished;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Log.e(_error, "Valor NULL");
        } catch (NumberFormatException e) {
            Log.e(_error, "No es un numero");
        } catch (Exception e){
            Log.e(_error, "Excepcion no identificada en metodo accomplishmentsJSON");
        }
        addDataSet();
    }


}

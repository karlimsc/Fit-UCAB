package com.example.juanmacedo.fitucab;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
 * Created by Bryan Teixeira on 21/5/2017.
 */

public class GraficaNivel extends AppCompatActivity {


    private static String TAG = "MainActivity";

    //Deben ser cantidades en int, no porcentajes
    private int[] _valoresY = {0,0};
    private String[] _valoresX = {"Sin terminar" , "Terminados" };
    PieChart pieChart;
    private int logrado, noLogrado;
    private static String URLtamaño = "http://192.168.1.8:8080/FitUcabService_war_exploded/dbgrafica/obtener";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        traerTamaños();


        pieChart = (PieChart) findViewById(R.id.PieChartId);

        //Habilitamos funcion de girar la torta al tocarla
        pieChart.setRotationEnabled(true);
        //Convertimos los valores que cargamos en porcentaje
        pieChart.setUsePercentValues(true);
        //Habilitamos circulo interior y configuramos propiedades
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setCenterText("Nivel X\n Usuario");
        pieChart.setCenterTextSize(30);
        //Desabilitamos atributo descripcion de la grafica
        pieChart.getDescription().setEnabled(false);
        //Funcion que agranda el tamaño de la porcion al ser tocada
        pieChart.setHighlightPerTapEnabled(true);
        //Efecto visual inicial de la grafica
        pieChart.animateXY(1400, 1400);




    }

    //Funcion que realiza la carga de los datos a la grafica
    private void addDataSet() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < _valoresX.length ; i++) {
            entries.add(new PieEntry(_valoresY[i], _valoresX[i]
                    ));
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(entries,"");
        //Espacio entre cada seccion de la grafica
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setValueTextSize(20);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setDrawValues(true);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setTextSize(15);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);



        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(20f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    public void traerTamaños(){
        StringRequest stringRequest = new StringRequest(URLtamaño,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("onResponse()", response.toString());
                        showJSON2(response);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onResponse()", error.toString());
            }
        });

        RequestQueue request = Volley.newRequestQueue(this);

        request.add(stringRequest);
        Log.d("objetooo", stringRequest.toString());

    }

    private void showJSON2(String json){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);

            logrado = Integer.parseInt(jsonObject.getString("Logrado"));
            noLogrado = Integer.parseInt(jsonObject.getString("NoLogrado"));
            _valoresY[0] = logrado;
            _valoresY[1] = noLogrado;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        addDataSet();
    }



}

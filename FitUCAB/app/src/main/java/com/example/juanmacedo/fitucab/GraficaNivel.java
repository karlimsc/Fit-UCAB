package com.example.juanmacedo.fitucab;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;



/**
 * Created by Bryan Teixeira on 21/5/2017.
 */

public class GraficaNivel extends AppCompatActivity {


    private static String TAG = "MainActivity";

    //Deben ser cantidades en int, no porcentajes
    private int[] _valoresY = {30, 34};
    private String[] _valoresX = {"Terminados" , "Sin terminar" };
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);

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

        addDataSet();


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



}

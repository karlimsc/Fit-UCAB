package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
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


    public void setDataGraphic(ArrayList<Integer> dataQuemadas, ArrayList<Integer> dataConsumidas) {
        try {

            List<Entry> calQuemadas = new ArrayList<Entry>();
            List<Entry> calConsumidas = new ArrayList<Entry>();
            List<Entry> calDiferencial = new ArrayList<Entry>();

            for(int i=0; i<dataQuemadas.size(); i++){
                calQuemadas.add(new Entry( (float) i,(float) dataQuemadas.get(i)));
            }

            for(int i=0; i<dataConsumidas.size(); i++){
                calConsumidas.add(new Entry( (float) i,(float) dataConsumidas.get(i)));
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

    public void setDiferecial(ArrayList<Integer> dataQuemadas, ArrayList<Integer> dataConsumidas) {
        arrayDiferencial.clear();
        int q;
        int c;
        for (int i=0; i<dataConsumidas.size(); i++){
            q = dataQuemadas.get(i);
            c = dataConsumidas.get(i);
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
    }


    /*
    funcion que refrescara la grafica para poder ver los
    ultimos 12 meses de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoMes(){
        _lineChart.clear();
        ArrayList<Integer> prueba7diasC = new ArrayList<>();
        ArrayList<Integer> prueba7diasQ = new ArrayList<>();
        prueba7diasC.add(1400);
        prueba7diasC.add(1000);
        prueba7diasC.add(900);
        prueba7diasC.add(600);
        prueba7diasC.add(1200);
        prueba7diasC.add(1400);
        prueba7diasC.add(350);
        prueba7diasC.add(3500);
        prueba7diasC.add(2000);
        prueba7diasC.add(1000);
        prueba7diasC.add(800);
        prueba7diasC.add(650);

        prueba7diasQ.add(200);
        prueba7diasQ.add(40);
        prueba7diasQ.add(100);
        prueba7diasQ.add(0);
        prueba7diasQ.add(35);
        prueba7diasQ.add(75);
        prueba7diasQ.add(0);
        prueba7diasQ.add(350);
        prueba7diasQ.add(250);
        prueba7diasQ.add(500);
        prueba7diasQ.add(200);
        prueba7diasQ.add(0);
        setDiferecial(prueba7diasQ, prueba7diasC);
        setDataGraphic(prueba7diasQ, prueba7diasC);
        activateDeactivateQuemadasConsumidas(_cb_m11_quemadas, "Quemadas", _dataQuemadas);
        activateDeactivateQuemadasConsumidas(_cb_m11_consumidas, "Consumidas", _dataConsumidas);
    }

    /*
    funcion que refrescara la grafica para poder ver las
    ultimas 4 semanas de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoSemana(){
        _lineChart.clear();
        ArrayList<Integer> prueba7diasC = new ArrayList<>();
        ArrayList<Integer> prueba7diasQ = new ArrayList<>();
        prueba7diasC.add(2000);
        prueba7diasC.add(350);
        prueba7diasC.add(439);
        prueba7diasC.add(1000);

        prueba7diasQ.add(1000);
        prueba7diasQ.add(0);
        prueba7diasQ.add(36);
        prueba7diasQ.add(230);

        setDiferecial(prueba7diasQ, prueba7diasC);
        setDataGraphic(prueba7diasQ, prueba7diasC);
        activateDeactivateQuemadasConsumidas(_cb_m11_quemadas, "Quemadas", _dataQuemadas);
        activateDeactivateQuemadasConsumidas(_cb_m11_consumidas, "Consumidas", _dataConsumidas);
    }

    /*
   funcion que refrescara la grafica para poder ver los
   ultimos 7 dias de calorias consumidas, quemadas y su diferencia.
   OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
   */

    public void verGraficoDia(){
        _lineChart.clear();
        ArrayList<Integer> prueba7diasC = new ArrayList<>();
        ArrayList<Integer> prueba7diasQ = new ArrayList<>();
        prueba7diasC.add(1400);
        prueba7diasC.add(1000);
        prueba7diasC.add(900);
        prueba7diasC.add(600);
        prueba7diasC.add(1200);
        prueba7diasC.add(1400);
        prueba7diasC.add(350);

        prueba7diasQ.add(200);
        prueba7diasQ.add(40);
        prueba7diasQ.add(100);
        prueba7diasQ.add(0);
        prueba7diasQ.add(35);
        prueba7diasQ.add(75);
        prueba7diasQ.add(0);
        setDiferecial(prueba7diasQ, prueba7diasC);
        setDataGraphic(prueba7diasQ, prueba7diasC);
        activateDeactivateQuemadasConsumidas(_cb_m11_quemadas, "Quemadas", _dataQuemadas);
        activateDeactivateQuemadasConsumidas(_cb_m11_consumidas, "Consumidas", _dataConsumidas);

    }


}

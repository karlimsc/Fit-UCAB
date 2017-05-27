package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

import com.fitucab.ds1617b.fitucab.UI.Activities.M11Food;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.fitucab.ds1617b.fitucab.R.layout.fragment_m11_graphic;

/**
 * A simple {@link Fragment} subclass.
 */



public class M11GraphicFragment extends Fragment {

    private CheckBox _cb_m11_consumidas;
    private CheckBox _cb_m11_quemadas;
    private Button _btn_m11_dia;
    private Button _btn_m11_semana;
    private Button _btn_m11_mes;
    private Button _btn_m11_ano;
    private View _view;
    private OnFragmentSwap _callBack;

    private static final String _TAG = M11Food.class.getSimpleName();
    // variable del grafico
    private LineChart _lineChart;
    // setcomp1 contendra el conjunto de datos de la calorias quemadas
    private LineDataSet _setComp1;
    // setcomp2 contendra el conjunto de datos de la calorias consumidas
    private LineDataSet _setComp2;
    // setcomp3 contendra el conjunto de datos de la diferencia de las calorias quemadas y consumidas
    private LineDataSet _setComp3;

    
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


        _btn_m11_dia = (Button) _view.findViewById(R.id.btn_m11_dia);
        _btn_m11_semana = (Button) _view.findViewById(R.id.btn_m11_semana);
        _btn_m11_mes = (Button) _view.findViewById(R.id.btn_m11_mes);
        _btn_m11_ano = (Button) _view.findViewById(R.id.btn_m11_ano);

        try {

            // obtengo el grafico del archivo xml
            _lineChart = (LineChart) _view.findViewById(R.id.chart);
            // la documentacion dice que hay que poner esto.. xd
            Utils.init(getResources());
            // declaro las variables donde se guardaran los datos de entrada
            // es decir la cantidad de calorias.
            List<Entry> calQuemadas = new ArrayList<Entry>();
            List<Entry> calConsumidas = new ArrayList<Entry>();
            List<Entry> calDiferencial = new ArrayList<Entry>();

            // datos de las calorias quemadas
            Entry c1e1 = new Entry(0f, 100000f);
            calQuemadas.add(c1e1);
            Entry c1e2 = new Entry(1f, 140000f);
            calQuemadas.add(c1e2);
            Entry c1e3 = new Entry(2f, 120000f);
            calQuemadas.add(c1e3);
            Entry c1e4 = new Entry(3f, 140000f);
            calQuemadas.add(c1e4);


            // datos de la calorias consumidas
            Entry c2e1 = new Entry(0f, 130000f);
            calConsumidas.add(c2e1);
            Entry c2e2 = new Entry(1f, 115000f);
            calConsumidas.add(c2e2);
            Entry c2e3 = new Entry(2f, 90000f);
            calConsumidas.add(c2e3);
            Entry c2e4 = new Entry(3f, 105000f);
            calConsumidas.add(c2e4);



            // datos de las calorias consumidas - quemadas
            Entry c3e1 = new Entry(0f, 30000f);
            calDiferencial.add(c3e1);
            Entry c3e2 = new Entry(1f, -25000f);
            calDiferencial.add(c3e2);
            Entry c3e3 = new Entry(2f, -30000f);
            calDiferencial.add(c3e3);
            Entry c3e4 = new Entry(3f, -35000f);
            calDiferencial.add(c3e4);

            // creo el dataset de caloria quemadas y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            _setComp1 = new LineDataSet(calQuemadas, "Quemadas");
            _setComp1.setColor(Color.BLUE);
            _setComp1.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria consumidas y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            _setComp2 = new LineDataSet(calConsumidas, "Consumidas");
            _setComp2.setColor(Color.YELLOW);
            _setComp2.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria diferencial y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            _setComp3 = new LineDataSet(calDiferencial, "Diferencial");
            _setComp3.setColor(Color.GREEN);
            _setComp3.setAxisDependency(AxisDependency.LEFT);

            // los LineDataSets anteriores(setComp1, setComp2 y setComp3)
            // los introduzco en una sola estructura de datos
            List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(_setComp1);
            dataSets.add(_setComp2);
            dataSets.add(_setComp3);
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




        // Inflate the layout for this fragment
        return _view;
    }

    /*
   funcion que muestra o elimina la linea que indica las calorias
   consumidas de la grafica. El metodo es llamado con la propiedad
   onClick en el archivo xml
   */
    public void activarDesactivarConsumidas (View _view) {
        // obtengo el checkbox que indica si desea ver o no
        // la linea de calorias consumidas
         _cb_m11_consumidas = (CheckBox) _view.findViewById(R.id.cb_m11_consumidas);
        // pregunto si el check no esta seleccionado
        if (!_cb_m11_consumidas.isChecked()) {
            // obtengo la data del grafico
            LineData lineData = _lineChart.getData();
            //ILineDataSet set = lineData.getDataSetByIndex(0);

            // obtengo los datos pertenecientes a la linea de consumidas
            ILineDataSet consumidas = lineData.getDataSetByLabel("Consumidas",false);
            // remuevo los datos
            lineData.removeDataSet(consumidas);
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
            // agrego los datos pertenecientes a calorias consumidas
            lineData.addDataSet(_setComp2);
            // notifico los cambios
            lineData.notifyDataChanged();
            _lineChart.notifyDataSetChanged();
            // refresco el grafico para que agarre los cambios
            _lineChart.invalidate();
        }
    }

    /*
    funcion que muestra o elimina la linea que indica las calorias
    quemadas de la grafica. El metodo es llamado con la propiedad
    onClick en el archivo xml
    */
    public void activarDesactivarQuemadas(View _view) {
        // obtengo el checkbox que indica si desea ver o no
        // la linea de calorias quemadas
         _cb_m11_quemadas = (CheckBox) _view.findViewById(R.id.cb_m11_quemadas);
        // pregunto si el check no esta seleccionado
        if (!_cb_m11_quemadas.isChecked()){
            // obtengo la data del grafico
            LineData lineData = _lineChart.getData();
            //ILineDataSet set = lineData.getDataSetByIndex(0);

            // obtengo los datos pertenecientes a la linea de quemadas
            ILineDataSet quemadas = lineData.getDataSetByLabel("Quemadas",false);
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
            lineData.addDataSet(_setComp1);
            // notifico los cambios
            lineData.notifyDataChanged();
            _lineChart.notifyDataSetChanged();
            // refresco el grafico para que agarre los cambios
            _lineChart.invalidate();
        }
    }

    /*
    esta funcion ya no va.
    igual que su correspodiente boton del xml
    */
    public void verGraficaAno(View _view){
        _lineChart.clear();
    }

    /*
    funcion que refrescara la grafica para poder ver los
    ultimos 12 meses de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoMes(View _view){
        _lineChart.clear();
    }

    /*
    funcion que refrescara la grafica para poder ver las
    ultimas 4 semanas de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoSemana(View _view){
        _lineChart.clear();
    }

    /*
   funcion que refrescara la grafica para poder ver los
   ultimos 7 dias de calorias consumidas, quemadas y su diferencia.
   OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
   */



}

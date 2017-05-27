package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.AdapterItem;
import com.fitucab.ds1617b.fitucab.Model.Global;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M05.M05ModifyFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Se implementa como View.OnClickListener para accionar los Dialgos
 */

public class M05PrincipalActivity extends AppCompatActivity implements
        View.OnClickListener {

    Toolbar toolbar;
    ArrayList<Activit> _activits = new ArrayList<Activit>();
    ListView _listView;
    ArrayAdapter<Activit> adaptador;
    // Atributo para la posicion de la seleccion
    int selection;
    Gson gson = new Gson();
    private TextView _tvdisplaydate;
    private FloatingActionButton _fabChangeDate;

    // Para saber si hay elemntos seleccionados
    int pase = 0;
    int size;

    //Para las fechas
    private int _year;
    private int _month;
    private int _day;

    //Para la hora
    private int _hour;
    private int _min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m05_principal_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        _listView = (ListView) findViewById(R.id.lv_m05_listactivity);
        _fabChangeDate = (FloatingActionButton) findViewById(R.id.fab_m05_datepickerserch);
        // Se pone en modo de escucha
        _fabChangeDate.setOnClickListener(this);

        _tvdisplaydate = (TextView) findViewById(R.id.tv_m05_actualdate) ;


        // Diseño del toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhiteSmoke));
        getSupportActionBar().setTitle(R.string._ttl_m05_activity);



        // Llena la lista de objetos
         inflateActivitys();

        //Instanciamos el adaptador, le pasamos el arraylist y al listview la pasamos nuestro adapter
        // como adaptador de contenido
        adaptador = new ArrayAdapter<Activit>(this, android.R.layout.simple_list_item_1, _activits);

        selectedElement();
    }

    /**
     * Metodo para iniciar la seleccion de elemntos
     */
 public void selectedElement (){

      // Para la seleccion individual
     _listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

     _listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
             // Obtención del manejador de fragmentos
             FragmentManager fragmentManager = getFragmentManager();
             new M05ModifyFragment().show(fragmentManager, "M05ModifyFragment");
             return true;
         }
     });


     _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), R.string._tst_m05_seletectitemonetouch,
                        Toast.LENGTH_SHORT).show();

            }
     });
 }

public void listElement(){

    //Por defecto toma la posicion -1 la cual no existe
    selection = _listView.getCheckedItemPosition();
    if (selection != -1){
                Toast.makeText(getApplicationContext(), "Posicion seleccionada "+String.valueOf(selection),
                        Toast.LENGTH_SHORT).show();
    }

}

    /**
     * Matodo que llena los atributos que se veran en la lista
     */
    public void inflateActivitys() {
        //Aqui deberia haber un metodo que llene el ArrayList con los objetos
        //este add solo es una prueba para que se pueda ver ellist view personalizado

       //El metodo conecta y llena el array list _activits
         makeRequest();

        _listView.setAdapter(new AdapterItem(this, _activits));
    }


    /**
     * Infla el menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_m05_principal, menu);
        return true;
    }

    /**
     * Acciones  que son consecuencia de opcionar algun item del action bar
     *
     * @param item Seleccion del item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.itm_m05_start_activity){
            Intent newActivity = new Intent(this, M05AddExerciseActivity.class);
            startActivity(newActivity);
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * Se escucha el View que se este opciondo y segun sea el caso acciona dialogo apropiado,
     * Para este caso no es necesario la hora
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == _fabChangeDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            _year = c.get(Calendar.YEAR);
            _month = c.get(Calendar.MONTH);
            _day = c.get(Calendar.DAY_OF_MONTH);
            final SimpleDateFormat format = new SimpleDateFormat("E MMM d yyyy");

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            //_tvdisplaydate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR,year);
                            calendar.set(Calendar.MONTH,monthOfYear);
                            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                            _tvdisplaydate.setText(format.format(calendar.getTime()));

                        }
                    }, _year, _month, _day);
            datePickerDialog.show();
        }
    }

    //Para la consulta de todos las actividades realizadas

    public void makeRequest()
    {
        String consult = Global._url +"M05_ServicesSport/getSport?idSpo=1";

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, consult, (String)null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                final Type tipyList = new TypeToken<List<Activit>>(){}.getRawType();
                                _activits = gson.fromJson(response.toString(), tipyList);
                                //final Activit activity = gson.fromJson(response.toString(),Activit.class);
                                final Activit a1 = _activits.get(0);
                                Log.e ("NOMBRE DEL DEPORTE: ",a1.get_name());

                                /*
                                Log.i("RESPONSE",response.toString());
                                M05_textview_speed_tag.setText(response.toString());
                                // mTxtDisplay.setText("Response: " + response.toString());

                            */
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("no trajo nada","");

                    }
                });
// Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }


}



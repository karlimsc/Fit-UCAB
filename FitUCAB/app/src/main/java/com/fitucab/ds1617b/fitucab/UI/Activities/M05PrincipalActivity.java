package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.AdapterItem;
import com.fitucab.ds1617b.fitucab.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
        selectedElement();
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


    }

    /**
     * Metodo para iniciar la seleccion de elemntos
     */
 public void selectedElement (){
     _listView = (ListView) findViewById(R.id.lv_m05_listactivity);


     // Se pone en escucha si se selecciona un item
     _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Para la seleccion individual
                _listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

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
        _activits.add(new Activit(12, "YOGA", 12.43, 34.34, "12-12-12"));
        _activits.add(new Activit(12, "futbol", 12.43, 34.34, "12-12-12"));
        _activits.add(new Activit(12, "tennis", 12.43, 34.34, "12-12-12"));
        _activits.add(new Activit(12, "escala", 12.43, 34.34, "12-12-12"));
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.itm_m05_delete) {
            listElement();
            if(selection == -1) {
                Toast.makeText(getApplicationContext(), R.string._tst_m05_messagedelete,
                        Toast.LENGTH_SHORT).show();
            }
            else {
                dialogConfirmation();
            }
            return true;
        }
        if (id == R.id.itm_m05_modify) {
            Toast.makeText(getApplicationContext(), R.string._tst_m05_messagemodify,
                    Toast.LENGTH_SHORT).show();

            return true;
        }
        if (id == R.id.itm_m05_start_activity){
            Intent newActivity = new Intent(this, M05AddExerciseActivity.class);
            startActivity(newActivity);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Dialogo emergente para confirmacion de eliminacion de actividad
     */

    public void dialogConfirmation() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(R.string._ttl_m05_questiondeleteactivity);
        builder.setMessage(R.string._dlg_m05_quetiondeleteactivity)
                .setPositiveButton(R.string._dlg_m05_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string._dlg_m05_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
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


}


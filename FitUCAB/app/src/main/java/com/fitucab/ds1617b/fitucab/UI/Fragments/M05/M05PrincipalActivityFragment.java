package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Activit;

import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M05AddExerciseActivity;
import com.fitucab.ds1617b.fitucab.UI.Activities.M05PrincipalActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.security.AccessController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by elberg on 27/05/17.
 */

public class M05PrincipalActivityFragment extends Fragment implements
        View.OnClickListener{

    public ArrayList<Activit> _activits;

    Toolbar toolbar;
    Gson gson = new Gson();

    ListView _listView;

    // Atributo para la posicion de la seleccion
    int selection;

    private TextView _tvdisplaydate;
    private FloatingActionButton _fabChangeDate;


    //Para las fechas
    private int _year;
    private int _month;
    private int _day;

    //Para la hora
    private int _hour;
    private int _min;

    int pos=0;

    View _view;

    private OnFragmentSwap _callBack;
    private Activit _act;

    // Variables que se muetran en la lista
    String _sport,_date,_time,_calories,_empty;

    ManagePreferences manageId=new ManagePreferences();
    int userId;




    /**
     * Constuctor del fragmento Activity Principal
     */
    public M05PrincipalActivityFragment() {
    }



    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            _callBack = (OnFragmentSwap) activity;

        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.activity_m05_principal_activity, container, false);
        userId= manageId.getIdUser(_view.getContext());
        setupViewValues();
        return _view;

    }

    private void setupViewValues() {
        toolbar = (Toolbar) _view.findViewById(R.id.toolbar);
        //Permite que se visualice el menu en el action bar
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        _listView = (ListView) _view.findViewById(R.id.lv_m05_listactivity);
        _fabChangeDate = (FloatingActionButton) _view.findViewById(R.id.fab_m05_datepickerserch);

        // Se pone en modo de escucha
        _fabChangeDate.setOnClickListener(this);
        _tvdisplaydate = (TextView) _view.findViewById(R.id.tv_m05_actualdate);

        // Diseño del toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhiteSmoke));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string._ttl_m05_activity);

        _sport = getResources().getString(R.string._tv_m05_sport);
        _date = getResources().getString(R.string._tv_m05_starDate);
        _time = getResources().getString(R.string._tv_m05_starHour);
        _calories = getResources().getString(R.string._tv_m05_calories);
        _empty = getResources().getString(R.string._tst_m05_messageempty);
        //Llena el ListView
        makeRequest();
       // Opciones para la seleccion del Item
        selectedItem();
    }


    /**
     * Infla el menu
     *
     * @param menu
     * @return
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_m05_principal, menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.menu_m05_principal, menu);
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
            Intent newActivity = new Intent(getContext(), M05AddExerciseActivity.class);
            startActivity(newActivity);
        }
        else if (id == R.id.itm_m05_refresh){
            makeRequest();
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

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
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

    /**
     * Este make request se trae todas las actividades de los usuaios 
     */

    public void makeRequest()
    {                                     // Aqui va ManagePreferences.getIdUser()
        String consult =M05UrlConsul._urlActivitys+String.valueOf(userId);

        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pos =0;
                                ArrayList<Activit> at = new ArrayList<Activit>();
                                ArrayList<String> tab = new ArrayList<String>();
                                at = gson.fromJson(response,
                                        new TypeToken<List<Activit>>(){}.getType());
                                _activits = at;
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, tab);
                                _listView.setAdapter(adapter);
                                adapter.addAll(tab);

                                for (int i = 0; i < at.size(); i++ ){

                                    tab.add(_sport+" "+at.get(i).get_name()+"\n"+
                                            _date+" "+at.get(i).get_date()+"\n"+
                                            _time+" "+at.get(i).get_startime()+"\n"+
                                            _calories+": "+at.get(i).get_calor()
                                    );
                                }

                                //Muestra un mensaje al usuario que no posee elemntos en la lista
                                if (at.size()== 0){
                                    Toast.makeText(getContext(), R.string._tst_m05_messagefoundactivity,
                                            Toast.LENGTH_SHORT).show();
                                    //para que no escucheel 1er item en caso de que no tenga actiadas
                                    pos=1;
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(), R.string._tst_m05_messagereload,
                                Toast.LENGTH_SHORT).show();

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }


    /**
     * Metodo para iniciar la seleccion de elemntos
     */

    public void selectedItem(){
        // Para la seleccion individual
        _listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



        _listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

              M05PrincipalActivity.set_activit(_activits.get(position));
              Log.e("EN EL STATIC", String.valueOf(M05PrincipalActivity.get_activit().get_calor()));
              _callBack.onSwap("M05ModifyFragment", null);

                return true;
            }
        });



        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), R.string._tst_m05_seletectitemonetouch,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }


}

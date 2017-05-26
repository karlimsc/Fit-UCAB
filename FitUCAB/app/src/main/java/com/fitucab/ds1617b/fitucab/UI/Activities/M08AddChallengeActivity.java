package com.fitucab.ds1617b.fitucab.UI.Activities;

import com.fitucab.ds1617b.fitucab.R;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class M08AddChallengeActivity extends AppCompatActivity implements View.OnClickListener {


    Button botonFechaInicio, botonFechaFin;
    EditText textoFechaInicio, textoFechaFin;
    private int diaI,mesI,anoI,diaF,mesF,anoF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m08_add_challenge);



        botonFechaInicio = (Button)findViewById(R.id.botonFechaInicio);
        botonFechaFin = (Button)findViewById(R.id.botonFechaFin);
        textoFechaInicio = (EditText)findViewById(R.id.textoFechaInicio);
        textoFechaFin = (EditText)findViewById(R.id.textoFechaFin);

        botonFechaInicio.setOnClickListener(this);
        botonFechaFin.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v==botonFechaInicio){
            final Calendar c= Calendar.getInstance();
            diaI=c.get(Calendar.DAY_OF_MONTH);
            mesI=c.get(Calendar.MONTH);
            anoI=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    textoFechaInicio.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                }
            }
            ,diaI,mesI,anoI);
            datePickerDialog.show();
        }
        if (v==botonFechaFin){
            final Calendar c= Calendar.getInstance();
            diaF=c.get(Calendar.DAY_OF_MONTH);
            mesF=c.get(Calendar.MONTH);
            anoF=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    textoFechaFin.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                }
            }
                    ,diaF,mesF,anoF);
            datePickerDialog.show();

        }
    }
}

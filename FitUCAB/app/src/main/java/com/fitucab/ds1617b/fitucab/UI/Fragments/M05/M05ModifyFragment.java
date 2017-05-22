package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fitucab.ds1617b.fitucab.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Fragment del tipo dialogo que permite eliminar y modicicar actividad
 */

public class M05ModifyFragment extends DialogFragment  {
    TextView _tv_date, _tv_time;

    // Valores para los parametros  de fecha
    int _year, _month, _day;
    //Valorea para la hora
    int _hour, _min;


    public M05ModifyFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialogActivity();
    }

    /**
     * Metodo que crea el dialogo para la modificacion y eliminacion de la actividad
     * @return Dialogo
     */

    private Dialog createDialogActivity() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_m05_modify, null);

        builder.setView(v);

        ImageView delete = (ImageView) v.findViewById(R.id.iv_m05_deleteactivity);
        Button modify = (Button) v.findViewById(R.id.btn_m05_updateactivity);

        // Si se escoge la opcion de eliminar, realizar accion correspondiente
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogConfirmation();

                    }
                }
        );
        // Si se escoge modificar, realizar la accion correspondiente
        modify.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dismiss();
                    }
                }

        );

        return builder.create();

    }


    /**
     * Dialogo emergente para confirmacion de eliminacion de actividad
     */


    public void dialogConfirmation() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(R.string._ttl_m05_questiondeleteactivity);
        builder.setMessage(R.string._dlg_m05_quetiondeleteactivity)
                .setPositiveButton(R.string._dlg_m05_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .setNegativeButton(R.string._dlg_m05_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

}

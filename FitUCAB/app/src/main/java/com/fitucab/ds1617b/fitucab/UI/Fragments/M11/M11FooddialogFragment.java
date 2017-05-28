package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitucab.ds1617b.fitucab.R;


public class M11FooddialogFragment extends DialogFragment {


    public M11FooddialogFragment() {
        // Required empty public constructor
    }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            //Infla el fragmento

            builder.setView(inflater.inflate(R.layout.fragment_m11_fooddialog, null))
                    //Conecta con el XML que contiene la personalizacion del Dialog

                    .setTitle("Agregar Alimento Personalizado")
                    .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Si preciona el boton Agregar el alimento personalizado
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Si preciona el boton cancelar solo se cerrar el Dialog
                            dialog.cancel();
                        }

                    });

            return builder.create();
        }




}

package com.example.elberg.listarender;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.elberg.listarender.R.id.iv_m05_imagereference;

public class MainActivity extends AppCompatActivity {

    ArrayList<Activ> _activs = new ArrayList<Activ>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv_m05_listactivity);


          //Aqui deberia haber un metodo que llene el ArrayList con los objetos
        //este add solo es una prueba para que se pueda ver ellist view personalizado
        _activs.add(new Activ(12,"YOGA",12.43,34.34,"12-12-12"));

        listView.setAdapter(new AdapterItem(this, _activs));


    }

    /**
     * Metodo que llama al adapter item para cargar la lista
     */
    public void inflateList (){
        ListView lv = (ListView) findViewById(R.id.lv_m05_listactivity);
        AdapterItem adapter = new AdapterItem(this, _activs);
        lv.setAdapter(adapter);
    }

}

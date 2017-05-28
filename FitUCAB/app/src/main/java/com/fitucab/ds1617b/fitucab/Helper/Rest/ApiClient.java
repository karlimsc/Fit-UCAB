package com.fitucab.ds1617b.fitucab.Helper.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 20/05/17.
 */

public class ApiClient {
    //En Base URL, va la direccion de tu pc donde tengas corriendo el servidor:8888/WebServicesFitUCAB_war_exploded/
    public static final String BASE_URL= "http://186.93.48.131:8888/PruebaDeServicioWeb_war_exploded/";
    private static Retrofit retrofit = null;

    /**
     * Singleton encargado de devolver una unica instancia de la clase Retrofit
     * @return retorna una instancia de la clase Retrofit
     */
     public static Retrofit getClient(){

        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
        return retrofit;
    }
}

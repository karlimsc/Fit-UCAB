package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import com.fitucab.ds1617b.fitucab.Model.Global;

/**
 * Created by elberg on 26/05/17.
 */

public class M05UrlConsul {

    // Deporte por id de deporte - consulta para traer id , nombre y met
    public static String _urlSportid = Global._url +"M05_ServicesSport/getSport?idSpo=1";

    // Elimina Actividad por id del mismo
    public static String _urlDeleteAct = Global._url+"M05_ServicesActivity/deleteActivity?idReg=";
}

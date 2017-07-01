package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;

/**
 * Created by elberg on 26/05/17.
 * Esta clase contiene todos las consultas http
 */

public class M05UrlConsul {
    private static IpStringConnection IP= new IpStringConnection();

    /**
     * Deporte por id de deporte - consulta para traer id , nombre y met
     */

    // public static String _urlSportid = IP.getIp() +"M05_ServicesSport/getSport?idSpo=1";

    /**
     * Elimina Actividad por id del mismoactualizar
     */

    public static String _urlDeleteAct = IP.getIp()+"M05_ServicesActivity/deleteActivity?idAct=";

    /**
     * Actualiza km
     * @param id
     * @param km
     * @return direccion http para
     */

    public static String _urlUpdateKm(String id, String km) {
        String dir;
        dir =IP.getIp() + "M05_ServicesActivity/updateKm?idAct="+id+"&km="+km;
        return dir;
    }

    /**
     * Actualiza Calorias
     * @param id
     * @param calorias
     * @return
     */

    public static String _urlUpdateCal(String id, String calorias) {
        String dir;
        dir = IP.getIp() + "M05_ServicesActivity/updateCalor?idAct="+id+"&calorias="+calorias;
        return  dir;
    }

    /**
     * Esta consulta tre todas lasactividades de la persona
     */

    public static String _urlActivitys =IP.getIp()+"M05_ServicesActivity/getAllActivity?idPer=";

    /**
     * Crea el String para insertar actividad.
     */
    public static String _urlInsertAct(String horaIn, String horaFin,
                                       String lugIn, String lugFin,String fecha, String km,
                                       String calor, int idReg, int idSpo){
        return "insertActivity?horainicial="+horaIn+
                "&horafinal="+horaFin+"&fecha="+fecha+"&km="+km+"&calorias="+calor+
                "&lugarinicial="+lugIn+"&lugarfinal="+lugFin+
                "&idReg="+String.valueOf(idReg)+"&idSpo="+String.valueOf(idSpo);
    }

    /**
     * Insertar Actividad
     */

    public static String _urlInsertAct( String hini,String hfin, String  fech, String km, String cal,
                                     String lugini, String lugfin ,String idPer,  String idSpor){
        return IP.getIp()+"M05_ServicesActivity/insertActivity?horainicia="+hini
                +"&horafinal="+hfin
                +"&fecha="+fech
                +"&km="+km
                +"&calorias="+ cal
                +"&lugarinicial="+lugini
                +"&lugarfinal="+lugfin
                +"&idReg="+idPer
                +"&idSpo="+idSpor;
    }

    public  static String _urlIdSport =
            IP.getIp()+"M05_ServicesSport/getSport?nameSpo=";

}
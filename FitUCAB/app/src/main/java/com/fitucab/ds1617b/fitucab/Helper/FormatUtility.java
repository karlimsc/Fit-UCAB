package com.fitucab.ds1617b.fitucab.Helper;

/**
 * Created by karo on 24/05/17.
 * Métodos para dar formato a los datos.
 */

public class FormatUtility {

    /**
     * Método que convierte double en string con formato
     * de dos decimales.
     * @param d Número que se desea formatear.
     * @return El valor en string del número formateado.
     */
    public static String fmt(double d)
    {
        return String.valueOf(round(d/1000,2));
    }

    /**
     * Redondea double a dos decimales.
     * @param value Valor a redondear.
     * @param places Cantidad de lugares para decimales.
     * @return El número redondeado.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}

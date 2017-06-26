package edu.ucab.desarrollo.fitucab.exception;

import edu.ucab.desarrollo.fitucab.webService.M09_ServicesGamification;

/**
 * Excepcion personalizada del modulo 09
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class M09Exception extends Exception {



    public M09Exception(String msg){
        super(msg);
    }

    @Override
    public String toString(){
        super.toString();
        return "Error";
    }

}

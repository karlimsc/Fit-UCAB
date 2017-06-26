package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by estefania on 25/06/2017.
 */
public class CheckUserCommand {

    String _password;
    String _user;
    private static String DEFAULT_ENCODING1="UTF-8";

    public CheckUserCommand(String password, String user){
        this._password= password;
        this._user = user;
    }


    public void execute(){
        try{

        }
        catch (Exception e){

        }

    }

}

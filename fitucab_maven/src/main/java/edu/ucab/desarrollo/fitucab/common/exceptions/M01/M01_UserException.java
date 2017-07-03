package edu.ucab.desarrollo.fitucab.common.exceptions.M01;

import edu.ucab.desarrollo.fitucab.common.entities.User;

/**
 * Created by karo on 03/07/17.
 */
public class M01_UserException extends Exception{
    User userFail = new User();

    private int RESULT_CODE_FAIL = 300;
    private int RESULT_USER_FAIL = 400;

    public M01_UserException(User userFail){
        this.userFail = userFail;
    }

    public void setuserResultUserFail(){
        this.userFail.set_status(Integer.toString(RESULT_USER_FAIL));
    }

    public void setuserResultCodeFail(){
        this.userFail.set_status(Integer.toString(RESULT_CODE_FAIL));
    }

    public User getUserFail(){
        return this.userFail;
    }



}

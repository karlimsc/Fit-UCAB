package com.fitucab.ds1617b.fitucab.Helper.Rest;

import com.fitucab.ds1617b.fitucab.Model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 20/05/17.
 */

public interface ApiEndPointInterface {

    @GET("M01_ServicesUser/login_user")
    Call<User> loginUser(@Query("username") String userparam,@Query("password") String passwordparam);

    @GET("M01_ServicesUser/insertRegistry")
    Call<User> insertRegistry(@Query("username") String username,
                            @Query("password") String password,
                            @Query("email") String email,
                            @Query("sex") String sex,
                            @Query("phone")String phone,
                            @Query("birthdate") String birthdate,
                            @Query("weight") String weight,
                            @Query("height") String height
                            );
    @GET("M01_ServicesUser/restorePassword")
    Call<User> restorePassword(); //Aqui se debe pasar el correo
    Call<User> restorePassword(@Query("email") String email);
}

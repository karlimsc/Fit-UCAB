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

}

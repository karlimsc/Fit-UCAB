package com.fitucab.ds1617b.fitucab.Helper.Rest;

import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.Notification_Settings;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by root on 20/05/17.
 */

public interface ApiEndPointInterface {

    /**
     * Declaracion de servicios web a los cuales les hago peticiones
     * @param userparam
     * @param passwordparam
     * @return
     */

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
    Call<User> restorePassword(@Query("email") String email);
    
    @GET("M04_ServicesNotificationSettings/insertSetting")
    Call<Notification_Settings> insertSetting(@Query("preferenceFriends") boolean preferenceFriends,
                                              @Query("preferenceActivity") boolean preferenceActivity,
                                              @Query("preferenceTraining") boolean preferenceTraining,
                                              @Query("preferenceChallenges") boolean preferenceChallenges,
                                              @Query("preferenceHydration") boolean preferenceHydration,
                                              @Query("preferenceCalories") boolean preferenceCalories,
                                              @Query("preferenceGamification") boolean preferenceGamification,
                                              @Query("preferenceLanguage") String preferenceLanguage,
                                              @Query("preferenceUnit") String preferenceUnit,
                                              @Query("preferenceRadius") int preferenceRadius,
                                              @Query("userId") int userId);

    @GET("M04_ServicesNotificationSettings/updateSetting")
    Call<Notification_Settings>  updateSetting(@Query("preferenceFriends") boolean preferenceFriends,
                                               @Query("preferenceActivity") boolean preferenceActivity,
                                               @Query("preferenceTraining") boolean preferenceTraining,
                                               @Query("preferenceChallenges") boolean preferenceChallenges,
                                               @Query("preferenceHydration") boolean preferenceHydration,
                                               @Query("preferenceCalories") boolean preferenceCalories,
                                               @Query("preferenceGamification") boolean preferenceGamification,
                                               @Query("preferenceLanguage") String preferenceLanguage,
                                               @Query("preferenceUnit") String preferenceUnit,
                                               @Query("preferenceRadius") int preferenceRadius,
                                               @Query("userId") int userId);

    @GET("M04_ServicesNotificationSettings/getSetting")
    Call<Notification_Settings> getSetting(@Query("userId") int userId);

    @POST("M06_ServicesTraining/getAllTraining")
    Call<ArrayList<Training>> getAllTraining(@Query("userId") int userId);


    @POST("M06_ServicesTraining/getTrainingDetail")
    Call<Training> getAllTraining(@Query("userId") int userId,
                                  @Query("trainingId") int trainingId);
///////////////////////////////////////////////////////////////////////
    @GET("M06_ServicesTraining/createTraining")
    Call<Training> addTraining(@Query("trainingName") String trainingName,
                               @Query("trainingActivities") String trainingActivities,
                                @Query("userId") int userId);

    @GET("M06_ServicesTraining/updateTraining")
    Call<Training> updateTraining( @Query( "idTraining" ) int id,
                                   @Query( "trainingName" ) String name,
                                   @Query( "trainingActivities" )  String _activities);

    @GET("M06_ServicesTraining/deleteTraining")
    Call<Training> deleteTraining(@Query( "trainingId" ) int id,
                                  @Query( "trainingName" ) String name);



    @GET("M06_ServicesTraining/shareTraining")
    Call<Training> shareTraining( @Query( "trainingName" ) String name,
                                  @Query( "trainingActivities" )  String _activities,
                                  @Query( "userId" ) int id );


}

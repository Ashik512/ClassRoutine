package com.hfad.classroutine.Api;

import com.hfad.classroutine.LoginActivity;
import com.hfad.classroutine.Model.ResponseModel;
import com.hfad.classroutine.Model.SOA;
import com.hfad.classroutine.Model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("student_reg.php")
    Call<SOA> Register(@Query("user_id") String user_id,@Query("email") String email,@Query("password") String password,@Query("dept") String dept,
                       @Query("year") String year);

    @GET("login.php")
    Call<SOA> Login(@Query("email") String email,@Query("password") String password);


    @GET("insert.php")
    Call<ResponseModel> DataInsert(@Query("subject") String subject, @Query("teacher") String teacher,
                                   @Query("room_no") String room_no, @Query("start_time") String start_time,
                                   @Query("finish_time") String finish_time, @Query("day_select") String day_select,
                                   @Query("dept") String dept,@Query("year") String year);


    @GET("update.php")
    Call<ResponseModel> DataUpdate(@Query("id") String iddata, @Query("subject") String subject, @Query("teacher") String teacher,
                                   @Query("room_no") String room_no, @Query("start_time") String start_time,
                                   @Query("finish_time") String finish_time, @Query("day_select") String day_select);

    @GET("delete.php")
    Call<ResponseModel> DataDelete(@Query("id") String iddata);

    @GET("delete_by_day.php")
    Call<ResponseModel> DataDeleteByDay(@Query("day_select") String day_select,@Query("dept") String dept,@Query("year") String year);

    @GET("delete_all.php")
    Call<ResponseModel> DeleteAll(@Query("dept") String dept,@Query("year") String year);



    @GET("saturday.php")
    Call<ResponseModel> ReadFromSaturday(@Query("dept") String dept,@Query("year") String year);

    @GET("sunday.php")
    Call<ResponseModel> ReadFromSunday(@Query("dept") String dept,@Query("year") String year);

    @GET("monday.php")
    Call<ResponseModel> ReadFromMonday(@Query("dept") String dept,@Query("year") String year);

    @GET("tuesday.php")
    Call<ResponseModel> ReadFromTuesday(@Query("dept") String dept,@Query("year") String year);

    @GET("wednesday.php")
    Call<ResponseModel> ReadFromWednesday(@Query("dept") String dept,@Query("year") String year);

    @GET("thursday.php")
    Call<ResponseModel> ReadFromThursday(@Query("dept") String dept,@Query("year") String year);
}

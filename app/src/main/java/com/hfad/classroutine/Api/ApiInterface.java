package com.hfad.classroutine.Api;

import com.hfad.classroutine.Model.ResponseModel;
import com.hfad.classroutine.Model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("insert.php")
    Call<ResponseModel> DataInsert(@Query("subject") String subject, @Query("teacher") String teacher,
                                   @Query("room_no") String room_no, @Query("start_time") String start_time,
                                   @Query("finish_time") String finish_time, @Query("day_select") String day_select);

    @GET("update.php")
    Call<ResponseModel> DataUpdate(@Query("id") String id, @Query("subject") String subject, @Query("teacher") String teacher,
                                   @Query("room_no") String room_no, @Query("start_time") String start_time,
                                   @Query("finish_time") String finish_time, @Query("day_select") String day_select);

    @GET("delete.php")
    Call<ResponseModel> DataDelete(@Query("id") String id);

    @GET("saturday.php")
    Call<ResponseModel> ReadFromSaturday();

    @GET("sunday.php")
    Call<ResponseModel> ReadFromSunday();

    @GET("monday.php")
    Call<ResponseModel> ReadFromMonday();

    @GET("tuesday.php")
    Call<ResponseModel> ReadFromTuesday();

    @GET("wednesday.php")
    Call<ResponseModel> ReadFromWednesday();

    @GET("thursday.php")
    Call<ResponseModel> ReadFromThursday();
}

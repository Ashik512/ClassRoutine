package com.hfad.classroutine.Api;

import com.hfad.classroutine.Model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendBiodata(@Field("subject") String subject,
                                   @Field("teacher") String teacher,
                                   @Field("room_no") String room_no,
                                   @Field("start_time") String start_time,
                                   @Field("finish_time") String finish_time,
                                   @Field("day_select") String day_select);
    /*
    @GET("read.php")
    Call<ResponsModel> getBiodata();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponsModel> updateData( @Field("id") String id,
                                   @Field("nama") String nama,
                                   @Field("usia") String usia,
                                   @Field("domisili") String domisili);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponsModel> deleteData(@Field("id") String id);
    */
}

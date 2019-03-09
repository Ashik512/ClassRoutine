package com.hfad.classroutine.Model;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    String id,subject,teacher,room_no,start_time,finish_time,day_select;
/*
    @SerializedName("id")
    private String id;
    @SerializedName("subject")
    private String subject;
    @SerializedName("teacher")
    private String teacher;
    @SerializedName("room_no")
    private String room_no;
    @SerializedName("start_time")
    private String start_time;
    @SerializedName("finish_time")
    private String finish_time;
    @SerializedName("day_select")
    private String day_select;
    */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getDay_select() {
        return day_select;
    }

    public void setDay_select(String day_select) {
        this.day_select = day_select;
    }
}

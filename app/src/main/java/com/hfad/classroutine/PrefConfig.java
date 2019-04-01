package com.hfad.classroutine;

import android.content.Context;
import android.content.SharedPreferences;

public  class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences=this.context.getSharedPreferences("pref_file",Context.MODE_PRIVATE);
    }

    public  void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login_status", status);
        editor.commit();
    }
    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean("login_status",false);
    }

    public void writeDept(String dept){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("dept",dept);
        editor.commit();
    }
    public String readDept(){
        return sharedPreferences.getString("dept","none");
    }

    public void writeUser(String user){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user",user);
        editor.commit();
    }
    public String readUser(){
        return sharedPreferences.getString("user","none");
    }

    public void writeUserId(String userId){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("userId",userId);
        editor.commit();
    }
    public String readUserId(){
        return sharedPreferences.getString("userId","none");
    }
    public void writeYear(String year){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("year",year);
        editor.commit();
    }
    public String readYear(){
        return sharedPreferences.getString("year","none");
    }
    public void writeEmail(String email){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",email);
        editor.commit();
    }
    public String readEmail(){
        return sharedPreferences.getString("email","none");
    }
}

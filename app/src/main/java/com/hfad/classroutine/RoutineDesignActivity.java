package com.hfad.classroutine;


import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.hfad.classroutine.Api.ApiClient;
import com.hfad.classroutine.Api.ApiInterface;
import com.hfad.classroutine.Model.ResponseModel;
import com.hfad.classroutine.Model.User;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutineDesignActivity extends AppCompatActivity {

    private EditText Subject;
    private EditText Teacher_Name;
    private EditText Room_No;
    private TextView Start_Time;
    private TextView Finish_Time;
    private Spinner Day_spinner;
    private Button SaveButton;
    private ImageButton clock1;
    private ImageButton clock2;

    String amPm;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    Button button;
    String start_time = "";
    String finish_time = "";


    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_design);

        Subject = findViewById(R.id.subject_id);
        Teacher_Name = findViewById(R.id.teacher_Name_id);
        Room_No = findViewById(R.id.room_No_id);
        Start_Time = findViewById(R.id.start_time_id);
        Finish_Time = findViewById(R.id.finish_time_id);
        clock1 = findViewById(R.id.image_id1);
        clock2 = findViewById(R.id.image_id2);
        Day_spinner = (Spinner) findViewById(R.id.spinner_id);
        SaveButton = findViewById(R.id.save_button_id);

        clock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(RoutineDesignActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            amPm = "AM";
                        } else if (hourOfDay == 12) {
                            amPm = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        start_time = String.format("%02d:%02d ", hourOfDay, minutes) + amPm;
                        Start_Time.setText(String.format("%02d:%02d ", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        clock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(RoutineDesignActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            amPm = "AM";
                        } else if (hourOfDay == 12) {
                            amPm = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        finish_time = String.format("%02d:%02d ", hourOfDay, minutes) + amPm;
                        Finish_Time.setText(String.format("%02d:%02d ", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        pd = new ProgressDialog(this);


        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("sending data ... ");
                pd.setCancelable(false);
                pd.show();
                String subject = Subject.getText().toString().trim();
                String teacher = Teacher_Name.getText().toString().trim();
                String room_no = Room_No.getText().toString().trim();
                String day_select = Day_spinner.getSelectedItem().toString();

                if(subject.isEmpty() || teacher.isEmpty() || room_no.isEmpty()
                || start_time.equals("") || finish_time.equals(""))
                {
                    Toast.makeText(RoutineDesignActivity.this,"All Fields Required",
                            Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;
                }

                ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);

                Call<ResponseModel> sendbio = api.DataInsert(subject,teacher,room_no,start_time,
                        finish_time,day_select);

                sendbio.enqueue(new Callback<ResponseModel>() {

                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                        pd.dismiss();
                        Log.d("RETRO", "response : " + response.body().toString());

                          if(response.isSuccessful())
                          {
                              Toast.makeText(RoutineDesignActivity.this,"insertion success",
                                      Toast.LENGTH_SHORT).show();
                             /// Log.d("Retro ",response.body().getResponse().toString());
                          }
                          else
                          {
                              Toast.makeText(RoutineDesignActivity.this,"failed",
                                      Toast.LENGTH_SHORT).show();
                            //  Log.d("Retro ",response.body().getResponse().toString());
                          }

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.dismiss();
                        //Toast.makeText(RoutineDesignActivity.this,"Failed",
                                //Toast.LENGTH_SHORT).show();
                        Toast.makeText(RoutineDesignActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("RETRO",t.getMessage());

                    }
                });

            }
        });

    }
}

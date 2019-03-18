package com.hfad.classroutine;


import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
    private Button SaveButton, UpdateButton, DeleteButton;
    private ImageButton clock1;
    private ImageButton clock2;
    private int position = 0;

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
        UpdateButton = findViewById(R.id.update_button_id);
        DeleteButton = findViewById(R.id.delete_button_id);

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

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if (iddata != null) {
            SaveButton.setVisibility(View.GONE);
            UpdateButton.setVisibility(View.VISIBLE);
            DeleteButton.setVisibility(View.VISIBLE);

            Subject.setText(data.getStringExtra("subject"));
            Teacher_Name.setText(data.getStringExtra("teacher"));
            Room_No.setText(data.getStringExtra("room_no"));
            Start_Time.setText(data.getStringExtra("start_time"));
            Finish_Time.setText(data.getStringExtra("finish_time"));
            final String day = data.getStringExtra("day_select");

            for (int i = 0; i < Day_spinner.getCount(); i++) {
                if (Day_spinner.getItemAtPosition(i).toString().equals("day")) {
                    position = i;
                    break;
                }
            }
            Day_spinner.setSelection(position);
            start_time = data.getStringExtra("start_time");
            finish_time = data.getStringExtra("finish_time");
        }

            DeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pd.setMessage("Deleting data ... ");
                    pd.setCancelable(false);
                    pd.show();

                    ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<ResponseModel> delete_data = api.DataDelete(iddata);

                    delete_data.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if (response.isSuccessful()) {
                                pd.hide();
                                Toast.makeText(RoutineDesignActivity.this,
                                        "Deleted Successfully...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RoutineDesignActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                pd.hide();
                                Toast.makeText(RoutineDesignActivity.this,
                                        "Delete failed...", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            pd.hide();
                            Toast.makeText(RoutineDesignActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            UpdateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pd.setMessage("updating data ... ");
                    pd.setCancelable(false);
                    pd.show();

                    String subject = Subject.getText().toString().trim();
                    String teacher = Teacher_Name.getText().toString().trim();
                    String room_no = Room_No.getText().toString().trim();
                    String day_select = Day_spinner.getSelectedItem().toString();

                    if (subject.isEmpty() || teacher.isEmpty() || room_no.isEmpty()
                            || start_time.equals("") || finish_time.equals("")) {
                        Toast.makeText(RoutineDesignActivity.this, "All Fields Required",
                                Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        return;
                    }

                    ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<ResponseModel> update_data = api.DataUpdate(iddata, subject, teacher, room_no, start_time,
                            finish_time, day_select);

                    update_data.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if (response.isSuccessful()) {
                                pd.hide();
                                Toast.makeText(RoutineDesignActivity.this,
                                        "Updated Successfully...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RoutineDesignActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                pd.hide();
                                Toast.makeText(RoutineDesignActivity.this,
                                        "Update failed...", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            pd.hide();
                            Toast.makeText(RoutineDesignActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

            SaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pd.setMessage("saving data ... ");
                    pd.setCancelable(false);
                    pd.show();
                    String subject = Subject.getText().toString().trim();
                    String teacher = Teacher_Name.getText().toString().trim();
                    String room_no = Room_No.getText().toString().trim();
                    String day_select = Day_spinner.getSelectedItem().toString();

                    if (subject.isEmpty() || teacher.isEmpty() || room_no.isEmpty()
                            || start_time.equals("") || finish_time.equals("")) {
                        Toast.makeText(RoutineDesignActivity.this, "All Fields Required",
                                Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        return;
                    }

                    ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<ResponseModel> sendbio = api.DataInsert(subject, teacher, room_no, start_time,
                            finish_time, day_select);

                    sendbio.enqueue(new Callback<ResponseModel>() {

                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                            pd.dismiss();
                            Log.d("RETRO", "response : " + response.body().toString());

                            String result = response.body().getResponse().toString();

                            if (result.equals("ok")) {
                                Toast.makeText(RoutineDesignActivity.this, "insertion success",
                                        Toast.LENGTH_SHORT).show();
                                /// Log.d("Retro ",response.body().getResponse().toString());
                            } else if (result.equals("exists")) {
                                Toast.makeText(RoutineDesignActivity.this, "Routine allready exists...",
                                        Toast.LENGTH_SHORT).show();
                                //  Log.d("Retro ",response.body().getResponse().toString());
                            } else {
                                Toast.makeText(RoutineDesignActivity.this, "failed...",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            pd.dismiss();
                            //Toast.makeText(RoutineDesignActivity.this,"Failed",
                            //Toast.LENGTH_SHORT).show();
                            Toast.makeText(RoutineDesignActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("RETRO", t.getMessage());

                        }
                    });

                }
            });

        }
    }


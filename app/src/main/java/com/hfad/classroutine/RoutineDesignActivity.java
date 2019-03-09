package com.hfad.classroutine;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hfad.classroutine.Api.ApiRequestBiodata;
import com.hfad.classroutine.Api.Retroserver;
import com.hfad.classroutine.Model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutineDesignActivity extends AppCompatActivity {

    private EditText Subject;
    private EditText Teacher_Name;
    private EditText Room_No;
    private EditText Start_Time;
    private EditText Finish_Time;
    private Spinner Day_spinner;
    private Button SaveButton;
    //ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_design);

        Subject = findViewById(R.id.subject_id);
        Teacher_Name = findViewById(R.id.teacher_Name_id);
        Room_No = findViewById(R.id.room_No_id);
        Start_Time = findViewById(R.id.start_time_id);
        Finish_Time = findViewById(R.id.finish_time_id);
        Day_spinner = (Spinner) findViewById(R.id.spinner_id);
        SaveButton = findViewById(R.id.save_button_id);


        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pd.setMessage("sending data ... ");
               // pd.setCancelable(false);
                //pd.show();
                String subject = Subject.getText().toString().trim();
                String teacher = Teacher_Name.getText().toString().trim();
                String room_no = Room_No.getText().toString().trim();
                String start_time = Start_Time.getText().toString().trim();
                String finish_time = Finish_Time.getText().toString().trim();
                String day_select = Day_spinner.getSelectedItem().toString();

                if(subject.isEmpty() || teacher.isEmpty() || room_no.isEmpty()
                        || start_time.isEmpty() || finish_time.isEmpty())
                {
                    Toast.makeText(RoutineDesignActivity.this,"All Fields Required",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);

                Call<ResponsModel> sendbio = api.sendBiodata(subject,teacher,room_no,start_time,
                        finish_time,day_select);
                sendbio.enqueue(new Callback<ResponsModel>() {

                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {

                       // pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());

                        String value = response.body().getValue().toString();
                        String message = response.body().getMessage().toString();

                          if(value.equals("1"))
                          {
                              Toast.makeText(RoutineDesignActivity.this,message,
                                      Toast.LENGTH_SHORT).show();
                          }
                          else
                          {
                              Toast.makeText(RoutineDesignActivity.this,message,
                                      Toast.LENGTH_SHORT).show();
                          }

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        //pd.hide();
                        Toast.makeText(RoutineDesignActivity.this,"Failed",
                                Toast.LENGTH_SHORT).show();
                        Log.d("RETRO", "Failure : " + "Request Failed...");

                    }
                });

            }
        });

    }
}
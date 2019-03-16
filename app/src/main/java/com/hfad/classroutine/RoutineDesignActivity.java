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

import com.hfad.classroutine.Api.ApiClient;
import com.hfad.classroutine.Api.ApiInterface;
import com.hfad.classroutine.Model.ResponseModel;
import com.hfad.classroutine.Model.User;

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
        Day_spinner = (Spinner) findViewById(R.id.spinner_id);
        SaveButton = findViewById(R.id.save_button_id);

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
                String start_time = Start_Time.getText().toString().trim();
                String finish_time = Finish_Time.getText().toString().trim();
                String day_select = Day_spinner.getSelectedItem().toString();

                if(subject.isEmpty() || teacher.isEmpty() || room_no.isEmpty()
                        || start_time.isEmpty() || finish_time.isEmpty())
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

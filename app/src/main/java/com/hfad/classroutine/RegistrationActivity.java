package com.hfad.classroutine;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.hfad.classroutine.Model.SOA;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText user_id,email,password;
    private Spinner dept,year;
    private Button register;

    ProgressDialog pd;

    public static PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        prefConfig=new PrefConfig(this);

        user_id = findViewById(R.id.user_id);
        email = findViewById(R.id.user_email);
        password = findViewById(R.id.user_password);
        dept = findViewById(R.id.dept_id);
        year = findViewById(R.id.year_id);
        register = findViewById(R.id.register_id);

        pd = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });
    }

    private void registerStudent() {

        pd.setMessage("Registering ... ");
        pd.setCancelable(false);
        pd.show();

        String userId = user_id.getText().toString().trim();
        String EmailId = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Dept = dept.getSelectedItem().toString().trim();
        String Year = year.getSelectedItem().toString().trim();

        if(userId.length()<10)
        {
            Toast.makeText(RegistrationActivity.this, "UserId Length should be at least 10 digit", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }
        if(password.length()<6)
        {
            Toast.makeText(RegistrationActivity.this, "Password Length should be at least 6 digit", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }

        if(userId.isEmpty() || EmailId.isEmpty() || Password.isEmpty()
        || Dept.isEmpty() || Year.isEmpty())
        {
            Toast.makeText(RegistrationActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);

        Call<SOA> sendbio = api.Register(userId,EmailId,Password,Dept,Year);

        sendbio.enqueue(new Callback<SOA>() {
            @Override
            public void onResponse(Call<SOA> call, Response<SOA> response) {
                pd.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getResponse().equals("ok")){

                        Toast.makeText(RegistrationActivity.this,"Registration Success",
                                Toast.LENGTH_LONG).show();


                        prefConfig.writeLoginStatus(true);
                        prefConfig.writeUserId(response.body().getUser_id().toString());
                        prefConfig.writeEmail(response.body().getEmail().toString());
                        prefConfig.writeUser(response.body().getUser().toString());
                        prefConfig.writeDept(response.body().getDept().toString());
                        prefConfig.writeYear(response.body().getYear().toString());
                        finish();
                        Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                        intent.putExtra("value",prefConfig.readUser());
                        startActivity(intent);
                        finish();
                    }else if(response.body().getResponse().equals("exist")) {
                        pd.dismiss();
                        Toast.makeText(RegistrationActivity.this,"Student Already Exists...",
                                Toast.LENGTH_LONG).show();
                        Log.d("message", "onResponse: error");
                    }
                }else {
                    pd.dismiss();
                    Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    Log.d("message", "onResponse: failed");
                }
            }

            @Override
            public void onFailure(Call<SOA> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(RegistrationActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }

}
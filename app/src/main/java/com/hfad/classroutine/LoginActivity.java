package com.hfad.classroutine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.classroutine.Api.ApiClient;
import com.hfad.classroutine.Api.ApiInterface;
import com.hfad.classroutine.Model.SOA;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static PrefConfig prefConfig;

    private TextView studentLogin,AdminLogin,checkStudent;
    private EditText Email,Password;
    private Button Login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        prefConfig=new PrefConfig(this);

        if(prefConfig.readLoginStatus() && prefConfig.readUser().equals("Student")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("value", "Student");
            startActivity(intent);
            finish();
        }else if(prefConfig.readLoginStatus()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("value", "Admin");
            startActivity(intent);
            finish();
        }

        studentLogin  = findViewById(R.id.student_login_id);
        AdminLogin = findViewById(R.id.check_admin_id);
        Email = findViewById(R.id.login_user_email);
        Password  =findViewById(R.id.login_user_password);
        Login = findViewById(R.id.login_id);
        register = findViewById(R.id.register_id2);
        checkStudent = findViewById(R.id.check_student_id);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });

        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentLogin.setText("Admin Login");
                register.setVisibility(View.INVISIBLE);
                AdminLogin.setVisibility(View.INVISIBLE);
                checkStudent.setVisibility(View.VISIBLE);
            }
        });

        checkStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentLogin.setText("Student Login");
                register.setVisibility(View.VISIBLE);
                checkStudent.setVisibility(View.INVISIBLE);
                AdminLogin.setVisibility(View.VISIBLE);
            }
        });

    }

    private void performLogin() {

        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(LoginActivity.this,"All fields Required...",Toast.LENGTH_SHORT).show();
            return;
        }

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);

        Call<SOA> sendbio = api.Login(email,password);

        sendbio.enqueue(new Callback<SOA>() {
            @Override
            public void onResponse(Call<SOA> call, Response<SOA> response) {

                if(response.isSuccessful()){
                    if(response.body().getResponse().equals("ok")){

                        Toast.makeText(LoginActivity.this,"Login Success",
                                Toast.LENGTH_LONG).show();


                        prefConfig.writeLoginStatus(true);
                        prefConfig.writeUserId(response.body().getUser_id().toString());
                        prefConfig.writeEmail(response.body().getEmail().toString());
                        prefConfig.writeUser(response.body().getUser().toString());
                        prefConfig.writeDept(response.body().getDept().toString());
                        prefConfig.writeYear(response.body().getYear().toString());
                        if(response.body().getUser().equals("Student")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("value", "Student");
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("value", "Admin");
                            startActivity(intent);
                            finish();
                        }
                    }else {
                        //pd.dismiss();
                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        Log.d("message", "onResponse: error");
                    }
                }else {
                    //pd.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    Log.d("message", "onResponse: failed");
                }
            }

            @Override
            public void onFailure(Call<SOA> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.hfad.classroutine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView studentLogin,AdminLogin;
    private EditText Email,Password;
    private Button Login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentLogin  = findViewById(R.id.student_login_id);
        AdminLogin = findViewById(R.id.check_admin_id);
        Email = findViewById(R.id.login_user_email);
        Password  =findViewById(R.id.login_user_password);
        Login = findViewById(R.id.login_id);
        register = findViewById(R.id.register_id2);
    }
}

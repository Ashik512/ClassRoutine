package com.hfad.classroutine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationActivity extends AppCompatActivity {

    private EditText user_id,email,password;
    private Spinner dept,year;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user_id = findViewById(R.id.user_id);
        email = findViewById(R.id.user_email);
        password = findViewById(R.id.user_password);
        dept = findViewById(R.id.dept_id);
        year = findViewById(R.id.year_id);
        register = findViewById(R.id.register_id);
    }
}

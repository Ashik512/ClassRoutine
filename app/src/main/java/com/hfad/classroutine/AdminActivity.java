package com.hfad.classroutine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userName = findViewById(R.id.admin_user_name_id);
        password = findViewById(R.id.admin_pass_id);
        login = findViewById(R.id.admin_login_button_id);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(name.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(AdminActivity.this,"All Fields Required",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(name.equals("admin") && pass.equals("password"))
                {
                    Intent i = new Intent(AdminActivity.this, MainActivity.class);
                    i.putExtra("value","admin");
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(AdminActivity.this,"Wrong Information...",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}


package com.hfad.classroutine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class IdentifyActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button goButton;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        radioGroup = (RadioGroup) findViewById(R.id.rdGroup);
        goButton = findViewById(R.id.go_button_id);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                String userType = radioButton.getText().toString();

                //Log.d("value","ki vul baal");

                Toast.makeText(IdentifyActivity.this,userType,Toast.LENGTH_SHORT).show();

               if (userType.equals("Admin")) {
                    startActivity(new Intent(IdentifyActivity.this, AdminActivity.class));
                } else {
                    Intent intent = new Intent(IdentifyActivity.this, MainActivity.class);
                    intent.putExtra("value", userType);
                    startActivity(intent);
                }
            }
        });
    }
}
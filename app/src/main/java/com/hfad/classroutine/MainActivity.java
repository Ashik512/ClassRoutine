package com.hfad.classroutine;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.classroutine.Adaptar.DataAdaptar;
import com.hfad.classroutine.Adaptar.TabsAccessorAdapter;
import com.hfad.classroutine.Api.ApiInterface;

public class MainActivity extends AppCompatActivity {

    public static String Data;

    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    TabsAccessorAdapter tabsAccessorAdapter;
    private FloatingActionButton fab;
    private TextView userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("ClassRoutine");

        myViewPager = findViewById(R.id.main_tabs_pager);
        myTabLayout = findViewById(R.id.my_tabs);

        userType = findViewById(R.id.user_type_id);

        tabsAccessorAdapter = new TabsAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(tabsAccessorAdapter);
        myTabLayout.setupWithViewPager(myViewPager);

        Intent data = getIntent();
        final String message = data.getStringExtra("value");
        Data = message;

       if(message.equals("Student") && message != null)
        {
            fab.hide();
            userType.setText("Hello, Student");
            Toast.makeText(MainActivity.this,"Welcome Student...",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            userType.setText("Hello, Admin");
            Toast.makeText(MainActivity.this,"Welcome Admin...",
                    Toast.LENGTH_SHORT).show();
        }
         fab.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent AddRoutine = new Intent(MainActivity.this, RoutineDesignActivity.class);
                   startActivity(AddRoutine);
               }
           });



    }
}

package com.hfad.classroutine;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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
    String message = "";
   // private LinearLayout mlinearLayout;

    public  static PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);

        fab = findViewById(R.id.fab);
       // mlinearLayout = findViewById(R.id.linear_layout_id);

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
        message = data.getStringExtra("value");
        Data = message;

       if(message.equals("Student") && message != null)
        {
            fab.hide();
            userType.setText("Hello, Student");
            Toast.makeText(MainActivity.this,"Welcome Student...",
                    Toast.LENGTH_SHORT).show();

            //MenuItem item = menu.findItem(R.id.my_item);
           // item.setVisible(false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout,menu);

        if(message.equals("Student") && message != null)
        {
            fab.hide();
            MenuItem item = menu.findItem(R.id.delete_id);
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.logout_id)
        {
            prefConfig.writeLoginStatus(false);
            prefConfig.writeUser("none");
            prefConfig.writeDept("none");
            prefConfig.writeEmail("none");
            prefConfig.writeYear("none");
            prefConfig.writeUserId("none");
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        else if(item.getItemId() == R.id.about_us_id)
        {
           startActivity(new Intent(MainActivity.this,DeveloperActivity.class));
        }
        else if(item.getItemId() == R.id.delete_id)
        {

           startActivity(new Intent(MainActivity.this,DeleteActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}

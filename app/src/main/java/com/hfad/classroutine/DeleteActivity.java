package com.hfad.classroutine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.hfad.classroutine.Api.ApiClient;
import com.hfad.classroutine.Api.ApiInterface;
import com.hfad.classroutine.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    private Spinner day_selection;
    private Button delete,deleteAll;

    public PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        prefConfig = new PrefConfig(this);

        day_selection = findViewById(R.id.days_id);

        delete = findViewById(R.id.delete_selected_button_id);
        deleteAll = findViewById(R.id.DeleteAll_button_id);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDelete();
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDeleteAll();
            }
        });


    }

    private void performDeleteAll() {

        String dept = prefConfig.readDept();
        String year = prefConfig.readYear();

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseModel> DeleteData = api.DeleteAll(dept,year);

        DeleteData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(DeleteActivity.this,"Deleted Successfully...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                    intent.putExtra("value","");
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(DeleteActivity.this,"Deletion Failed...",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DeleteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performDelete() {

        String day_select = day_selection.getSelectedItem().toString().trim();
        String dept = prefConfig.readDept();
        String year = prefConfig.readYear();

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseModel> delete_data = api.DataDeleteByDay(day_select,dept,year);

        delete_data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(DeleteActivity.this,"Deleted Successfully...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                    intent.putExtra("value","");
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(DeleteActivity.this,"Deletion Failed...",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DeleteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

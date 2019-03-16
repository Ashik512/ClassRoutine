package com.hfad.classroutine.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.classroutine.Adaptar.DataAdaptar;
import com.hfad.classroutine.Api.ApiClient;
import com.hfad.classroutine.Api.ApiInterface;
import com.hfad.classroutine.Model.ResponseModel;
import com.hfad.classroutine.Model.User;
import com.hfad.classroutine.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaturdayFragment extends Fragment {

    private List<User> mItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    private View saturday;

    public SaturdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        saturday =  inflater.inflate(R.layout.fragment_saturday, container, false);
        recyclerView = saturday.findViewById(R.id.saturday_fragment_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FetchFromSaturday();


        return saturday;
    }



    private void FetchFromSaturday() {

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseModel> getFromSaturday = api.ReadFromSaturday();
        getFromSaturday.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if(response.isSuccessful()) {

                    Log.d("RETRO", "RESPONSE : " + response.body().getResponse());
                    mItems = response.body().getResult();

                    mAdapter = new DataAdaptar(getContext(), mItems);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d("RETRO", t.getMessage());
            }
        });


    }


}

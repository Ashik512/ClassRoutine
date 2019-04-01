package com.hfad.classroutine.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfad.classroutine.Adaptar.DataAdaptar;
import com.hfad.classroutine.Api.ApiClient;
import com.hfad.classroutine.Api.ApiInterface;
import com.hfad.classroutine.LoginActivity;
import com.hfad.classroutine.Model.ResponseModel;
import com.hfad.classroutine.Model.User;
import com.hfad.classroutine.PrefConfig;
import com.hfad.classroutine.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TuesdayFragment extends Fragment {

    private List<User> mItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private TextView noRecords;
    public static PrefConfig prefConfig;

    private View tuesday;

    public TuesdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        prefConfig=new PrefConfig(getContext());
        // Inflate the layout for this fragment
        tuesday = inflater.inflate(R.layout.fragment_tuesday, container, false);
        noRecords = tuesday.findViewById(R.id.no_record_id);
        recyclerView = tuesday.findViewById(R.id.tuesday_fragment_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FetchFromTuesday();
        return tuesday;
    }

    private void FetchFromTuesday() {

        String dept = prefConfig.readDept();
        String year = prefConfig.readYear();

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseModel> getFromTuesday = api.ReadFromTuesday(dept,year);
        getFromTuesday.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if(response.isSuccessful()) {

                    Log.d("RETRO", "RESPONSE : " + response.body().getResponse());
                    mItems = response.body().getResult();

                    mAdapter = new DataAdaptar(getContext(), mItems);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
                else
                {
                   noRecords.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d("RETRO", t.getMessage());
                noRecords.setVisibility(View.VISIBLE);
            }
        });
    }

}

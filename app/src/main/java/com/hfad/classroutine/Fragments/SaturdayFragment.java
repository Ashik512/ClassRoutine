package com.hfad.classroutine.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.classroutine.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaturdayFragment extends Fragment {

    private View saturday;

    public SaturdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        saturday =  inflater.inflate(R.layout.fragment_monday, container, false);
        return saturday;
    }

}

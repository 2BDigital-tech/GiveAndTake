package com.example.giveandtake.Center;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.giveandtake.R;

public class CenterFragment extends Fragment {

    private CenterViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(CenterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_center, container, false);

        return root;
    }
}
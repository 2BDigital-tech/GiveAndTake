package com.android.giveandtake.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.giveandtake.R;

public class HistoryFragment extends Fragment {

        private View root;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            root = inflater.inflate(R.layout.fragment_history, container, false);


            return root;
        }


    }
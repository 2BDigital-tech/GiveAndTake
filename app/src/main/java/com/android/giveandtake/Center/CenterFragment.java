package com.android.giveandtake.Center;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giveandtake.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CenterFragment extends Fragment {



    private View root;
    private RecyclerView _RecyclerView;
    private tradeAdaper _Adapter;
    private RecyclerView.LayoutManager _LayoutManager;
    private FirebaseDatabase firebaseDatabase;
    static private ArrayList<Trade> TradeList;
    private DatabaseReference RootRef;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;


    private CenterViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(CenterViewModel.class);
        root = inflater.inflate(R.layout.fragment_center, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Trades");
        RootRef = firebaseDatabase.getInstance().getReference();
        firebaseAuth = firebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();


        createToShowTrades();

        return root;
    }

    public void createToShowTrades(){

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TradeList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String current_post_id = ds.child("current_post_id").getValue(String.class);
                    String current_user_id = ds.child("current_user_id").getValue(String.class);
                    String user_post_id = ds.child("user_post_id").getValue(String.class);
                    String user_post_name = ds.child("user_post_name").getValue(String.class);
                    String current_user_name = ds.child("current_user_name").getValue(String.class);
                    String post_give = ds.child("post_give").getValue(String.class);
                    String post_take = ds.child("post_take").getValue(String.class);
                    String current_user_phone = ds.child("current_user_phone").getValue(String.class);
                    String current_user_city = ds.child("current_user_city").getValue(String.class);


                    Trade t = new Trade(R.drawable.black2people,current_post_id,current_user_id,user_post_id,user_post_name,current_user_name,post_give,post_take,current_user_phone,current_user_city);
                    if(!TradeList.contains(t)){
                        TradeList.add(t);
                    }
                }

                updateView();

                _Adapter.setOnTradeClickListener(new tradeAdaper.OnTradeClickListener(){
                    @Override
                    public void onTradeClick(final int position) {
                        TradeList.get(position);

                    }

                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
            myRef.addListenerForSingleValueEvent(eventListener);
    }

        public void updateView(){
            _RecyclerView = root.findViewById(R.id.recyclerviewCenter);
            _RecyclerView.setHasFixedSize(true);
            _LayoutManager = new LinearLayoutManager(getContext());
            _Adapter = new tradeAdaper(TradeList);
            _RecyclerView.setLayoutManager(_LayoutManager);
            _RecyclerView.setAdapter(_Adapter);
        }

}
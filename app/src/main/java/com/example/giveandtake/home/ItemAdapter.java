package com.example.giveandtake.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giveandtake.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private ArrayList<Item> Listitem;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView _ImageView;
        public TextView _NameAsk;
        public TextView _PhoneAsk;

        public ViewHolder(View itemView){
            super(itemView);
            _ImageView = itemView.findViewById(R.id.ImageView);
            _NameAsk = itemView.findViewById(R.id.NameAsk);
            _PhoneAsk = itemView.findViewById(R.id.PhoneAsk);
        }
    }

    public ItemAdapter(ArrayList<Item> myListitem){
        Listitem = myListitem;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder evh = new ViewHolder(v);
        return evh;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item correntItem = Listitem.get(position);

        holder._ImageView.setImageResource(correntItem.getImageResocure());
        holder._NameAsk.setText(correntItem.getNameAsk());
        holder._PhoneAsk.setText(correntItem.getPhoneAsk());

    }

    @Override
    public int getItemCount() {
        return Listitem.size();
    }



}

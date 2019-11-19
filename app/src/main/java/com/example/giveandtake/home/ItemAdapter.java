package com.example.giveandtake.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giveandtake.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private ArrayList<Post> Listitem;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView _ImageView;
        public TextView _NameAsk;
        public TextView _PhoneAsk;
        public TextView _GiveAsk;
        public TextView _TakeAsk;
        public EditText _freeText;


        public ViewHolder(View itemView){
            super(itemView);
            _ImageView = itemView.findViewById(R.id.ImageView);
            _NameAsk = itemView.findViewById(R.id.NameAsk);
            _PhoneAsk = itemView.findViewById(R.id.PhoneAsk);
            _GiveAsk = itemView.findViewById(R.id.GiveOption);
            _TakeAsk = itemView.findViewById(R.id.TakeOption);
            _freeText = itemView.findViewById(R.id.freeText);
        }
    }

    public ItemAdapter(ArrayList<Post> myListitem){
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
        Post correntItem = Listitem.get(position);

        holder._ImageView.setImageResource(correntItem.getImageResocure());
        holder._NameAsk.setText(correntItem.getNameAsk());
        holder._PhoneAsk.setText(correntItem.getPhoneAsk());
        holder._GiveAsk.setText(correntItem.getGive());
        holder._TakeAsk.setText(correntItem.getTake());
        //holder._freeText.setText(correntItem.getfreeText());



    }

    @Override
    public int getItemCount() {
        return Listitem.size();
    }



}

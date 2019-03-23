package com.example.task3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.myviewholder>{
    @NonNull


    private List<String> response;

    public RecyclerAdapter2 (List<String> response) {


        this.response= response;

    }
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup  parent, int i) {

        TextView tx=(TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        myviewholder mvh=new myviewholder(tx);
        return mvh;
    }

    @Override
    public void onBindViewHolder (@NonNull myviewholder holder, int position) {

        holder.recycl.setText(response.get(position));

    }

    @Override
    public int getItemCount() {
        return response.size();
    }


    public static  class  myviewholder extends RecyclerView.ViewHolder{

        TextView recycl;
        public myviewholder(@NonNull TextView itemView) {
            super(itemView);
           recycl=itemView;
        }
    }
}



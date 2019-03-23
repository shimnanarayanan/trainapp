package com.example.task3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myviewholder> {
    @NonNull


  private Context context;
     int image[];
     String name[];


    public RecyclerAdapter(@NonNull Context context, int[] img, String[] name) {
        this.context = context;
        this.image = img;
        this.name = name;
    }

    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup  parent, int viewtype) {

       View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridlayout,parent,false);
       myviewholder myviewholder=new myviewholder(layout);
       return myviewholder;
    }

    @Override
    public void onBindViewHolder (@NonNull myviewholder holder, final int position) {

        holder.img.setImageResource(image[position]);
        holder.tx.setText(name[position]);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                switch (position) {


                    case 0:

                        Intent i = new Intent(context, Running.class);
                        context.startActivity(i);
                        break;


                    case 1:

                        Intent i1 = new Intent(context, Seatavailability.class);
                        context.startActivity(i1);
                            break;

                    case 2:

                        Intent i2= new Intent(context, Cancelledtrain.class);
                        context.startActivity(i2);
                        break;


                    case 3:

                        Intent i3= new Intent(context, Rescheduledtrain.class);
                        context.startActivity(i3);
                        break;


                    case 4:

                        Intent i4= new Intent(context, Trainroute.class);
                        context.startActivity(i4);
                        break;

                    case 5:

                        Intent i5= new Intent(context,Trainarrivals.class);
                        context.startActivity(i5);
                        break;


                }





            }
        });



    }



    @Override
    public int getItemCount() {
        return image.length;
    }


public static  class  myviewholder extends RecyclerView.ViewHolder{

     TextView tx;
     ImageView img;
    public myviewholder(@NonNull View itemView) {
        super(itemView);

        img=(ImageView )itemView.findViewById(R.id.imageView);
       tx=(TextView ) itemView.findViewById(R.id.textView);



    }
}
}



package com.example.mid2application;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    Context context;
    private RecyclerViewInterface recyclerViewInterface;
    ArrayList<AddressModel> list;


    public RecycleAdapter() {

    }

    public RecycleAdapter(Context context, ArrayList<AddressModel> list,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list=list;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.address_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.job.setText("Job: "+list.get(position).getJob());
        holder.phone.setText(list.get(position).getPhone());
        holder.presentAddress.setText("Present Adress: "+list.get(position).getPresent_address());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,job,phone,presentAddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.recycleName);
            job=itemView.findViewById(R.id.recycleJob);
            phone=itemView.findViewById(R.id.recyclePhone);
            presentAddress=itemView.findViewById(R.id.recyclePresentAddress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}

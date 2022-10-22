package com.example.mid2application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListRecycle extends AppCompatActivity implements RecyclerViewInterface{
RecyclerView recyclerView;
ArrayList<AddressModel> arrayList;
TextView noItemText;
MyDBHelper dbHelper;
Button addNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_recycle);
        noItemText=findViewById(R.id.noItemText);
        addNew=findViewById(R.id.addNew);
        arrayList=new ArrayList<>();
        dbHelper=new MyDBHelper(this);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
        if(arrayList.size()==0){
            recyclerView.setVisibility(View.GONE);
            noItemText.setVisibility(View.VISIBLE);
        }





        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ViewListRecycle.this,DataEntryForm.class);
                startActivity(i);
            }
        });

    }

    public void onStart(){
        super.onStart();
        getData();
    }

    public void getData(){
        arrayList=dbHelper.fetchAddress();
        RecycleAdapter adapter=new RecycleAdapter(this,arrayList, (RecyclerViewInterface) this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(ViewListRecycle.this,DetailActivity.class);
        intent.putExtra("id",arrayList.get(position).getId());
        intent.putExtra("name",arrayList.get(position).getName());
        intent.putExtra("phNumber",arrayList.get(position).getPhone());
        intent.putExtra("present",arrayList.get(position).getPresent_address());
        intent.putExtra("job",arrayList.get(position).getJob());
        intent.putExtra("permanent",arrayList.get(position).getPermanent_address());

        startActivity(intent);
    }
}
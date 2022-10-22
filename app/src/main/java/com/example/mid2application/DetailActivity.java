package com.example.mid2application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private EditText nameET,jobET,phoneET,presentAddressET,permanentAddressET;
    private Button updateBtn,deleteBtn;
    private String name,phone,job,presentAddress,permanentAddress;
    MyDBHelper dbHelper;
    int id;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbHelper=new MyDBHelper(this);
        i=getIntent();
        initialize();
        getSetData();
        setListeners();

    }



    public void initialize(){
        nameET=findViewById(R.id.inputName);
        jobET=findViewById(R.id.inputJob);
        phoneET=findViewById(R.id.inputPhone);
        presentAddressET=findViewById(R.id.inputPresentAddress);
        permanentAddressET=findViewById(R.id.inputParmanentAddress);
        updateBtn=findViewById(R.id.updateBtn);
        deleteBtn=findViewById(R.id.deleteBtn);
    }
    public void getSetData(){
        name=i.getStringExtra("name");
        job=i.getStringExtra("job");
        phone=i.getStringExtra("phNumber");
        presentAddress=i.getStringExtra("present");
        permanentAddress=i.getStringExtra("permanent");
        id=i.getIntExtra("id",0);
        nameET.setText(name);
        phoneET.setText(phone);
        jobET.setText(job);
        presentAddressET.setText(presentAddress);
        permanentAddressET.setText(permanentAddress);
    }

    private void setListeners() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=nameET.getText().toString();
                job=jobET.getText().toString();
                phone=phoneET.getText().toString();
                presentAddress=presentAddressET.getText().toString();
                permanentAddress=permanentAddressET.getText().toString();

                dbHelper.updateAddressById(id,name,phone,job,presentAddress,permanentAddress);
                Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog("Are you sure you want to delete this address?","Delete","Yes","No");
            }
        });
    }
    private void showDialog(String message, String title, String btn1,String btn2){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);

        builder.setCancelable(false)
                .setPositiveButton(btn1,new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id1){
                        if(dbHelper.deleteAddress(id)){
                            Intent intent=new Intent(DetailActivity.this,ViewListRecycle.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Failed to delete",Toast.LENGTH_SHORT).show();
                        }


                    }
                })
                .setNegativeButton(btn2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert=builder.create();
        alert.show();
    }
}
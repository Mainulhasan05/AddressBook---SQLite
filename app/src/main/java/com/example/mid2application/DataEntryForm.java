package com.example.mid2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataEntryForm extends AppCompatActivity {
private EditText nameET,jobET,phoneET,presentAddressET,permanentAddressET;
private Button saveBtn;
private String name,phone,job,presentAddress,permanentAddress;
MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry_form);
        dbHelper=new MyDBHelper(this);


        initialize();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(formValidation()){
                    dbHelper.addAddress(name,phone,job,presentAddress,permanentAddress);
                    Toast.makeText(DataEntryForm.this,"Data Saved Successfully",Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clearForm();
                        }
                    },1000);
                }
            }
        });
    }

    public void initialize(){
        nameET=findViewById(R.id.inputName);
        jobET=findViewById(R.id.inputJob);
        phoneET=findViewById(R.id.inputPhone);
        presentAddressET=findViewById(R.id.inputPresentAddress);
        permanentAddressET=findViewById(R.id.inputParmanentAddress);
        saveBtn=findViewById(R.id.saveBtn);
    }

    public boolean formValidation(){
         name=nameET.getText().toString();
         job=jobET.getText().toString();
         phone=phoneET.getText().toString();
         presentAddress=presentAddressET.getText().toString();
         permanentAddress=permanentAddressET.getText().toString();

        if(name.length()!=0 && job.length()!=0 && phone.length()!=0 &&presentAddress.length()!=0 && permanentAddress.length()!=0){
            return true;
        }
        else{
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void clearForm(){
        nameET.setText("");
        jobET.setText("");
        phoneET.setText("");
        presentAddressET.setText("");
        permanentAddressET.setText("");

        name=phone=job=presentAddress=permanentAddress="";


    }
}
package com.example.mid2application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="addressbook";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="address";
    private static final String KEY_ID="id",KEY_NAME="name";
    private static final String KEY_PRESENT_ADDRESS="present",KEY_PERMANENT_ADDRESS="permanent";
    private static final String KEY_PHONE="phone",KEY_JOB="job";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("
                +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" TEXT,"+KEY_PHONE+" TEXT,"
                +KEY_JOB+" TEXT,"+KEY_PRESENT_ADDRESS+" TEXT,"
                +KEY_PERMANENT_ADDRESS+" TEXT"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addAddress(String name,String phone,String job,String presentAddress,String permanentAddress){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE,phone);
        values.put(KEY_JOB,job);
        values.put(KEY_PRESENT_ADDRESS,presentAddress);
        values.put(KEY_PERMANENT_ADDRESS,permanentAddress);

        db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<AddressModel> fetchAddress(){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        ArrayList<AddressModel> arrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            if(cursor!=null){
                AddressModel model=new AddressModel();
                model.setId(cursor.getInt(0));
                model.setName(cursor.getString(1));
                model.setPhone(cursor.getString(2));
                model.setJob(cursor.getString(3));
                model.setPresent_address(cursor.getString(4));
                model.setPermanent_address(cursor.getString(5));
                arrayList.add(model);
            }

        }
        return  arrayList;
    }

    public void updateAddressById(int id,String name,String phone,String job,String presentAddress,String permanentAddress){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE,phone);
        values.put(KEY_JOB,job);
        values.put(KEY_PRESENT_ADDRESS,presentAddress);
        values.put(KEY_PERMANENT_ADDRESS,permanentAddress);
        db.update(TABLE_NAME,values,KEY_ID+" = ?",new String[]{String.valueOf(id)});
    }

    public boolean deleteAddress(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return true;
    }
}

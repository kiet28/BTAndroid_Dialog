package com.example.student.btandroid_dialog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "tacgia_list", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table TacGia(id text primary key, name text, address text, email text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists TacGia";
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertTacGia(TacGia tacGia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", tacGia.getId());
        contentValues.put("name", tacGia.getName());
        contentValues.put("address", tacGia.getAddress());
        contentValues.put("email", tacGia.getEmal());
        db.insert("TacGia", null, contentValues);
        return true;
    }

    public TacGia getTacGia(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from TacGia where id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();
        TacGia tacGia = new TacGia(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
        cursor.close();
        return tacGia;
    }

    public ArrayList<TacGia> getAllTacGia() {
        ArrayList<TacGia> list = new ArrayList<TacGia>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from TacGia";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            list.add(new TacGia(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean deleteTacGia(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "delete from TacGia where id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor == null){
            return false;
        }
        else{
            cursor.moveToFirst();
            cursor.close();
        }
        return true;
    }
    public boolean updateTacGia(String id,String name,String address,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("email",email);
        db.update("TacGia",contentValues, "id="+id,null);
        return true;
    }
}
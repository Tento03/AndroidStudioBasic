package com.example.sqllite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

public class Mahasiswa extends SQLiteOpenHelper {
    Context context;
    Cursor cursor;
    SQLiteDatabase database;

    public static String nama_database="data";
    public static String nama_tabel="mahasiswa";

    public Mahasiswa(@Nullable Context context) {
        super(context, nama_database, null,3);
        this.context=context;
        database=getReadableDatabase();
        database=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE IF NOT EXISTS "+nama_tabel+"(id varchar(50),nama varchar(50),alamat varhcar(50))";
        sqLiteDatabase.execSQL(query);
    }

    String random(){
        int acak=new Random().nextInt(888+1)+100;
        return String.valueOf(acak);
    }

    void simpan_data(String nama,String alamat){
        database.execSQL(
                "INSERT INTO "+nama_tabel+" VALUES "+ "('"+random()+"','"+nama+"','"+alamat+"')"
        );
        Toast.makeText(context,"Data tersimpan",Toast.LENGTH_SHORT).show();
    }
    void update_data(String id,String nama,String alamat){
        database.execSQL(
                "UPDATE "+nama_tabel+" SET nama='"+nama+"',alamat='"+alamat+"'"+ "WHERE id='"+id+"'"
        );
        Toast.makeText(context,"Data berhasil diupdate",Toast.LENGTH_SHORT).show();
    }
    void delete_data(String id){
        database.execSQL(
                "DELETE FROM "+nama_tabel+ " WHERE id='"+id+"'"
        );
        Toast.makeText(context,"Data berhasil dihapus",Toast.LENGTH_SHORT).show();
    }

    Cursor tampil_data(){
        Cursor cursor=database.rawQuery("SELECT * FROM "+nama_tabel,null);
        return  cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


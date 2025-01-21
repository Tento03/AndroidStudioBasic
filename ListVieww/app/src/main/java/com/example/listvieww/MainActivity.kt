package com.example.listvieww

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    lateinit var listview:ListView;
    lateinit var nama:ArrayList<String>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tambah_data();
        listview=findViewById(R.id.listview);
        var arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,nama);
        listview.adapter=arrayAdapter;
    }

    fun tambah_data(){
        nama=ArrayList<String>();
        nama.add("tento");
        nama.add("luffy");
        nama.add("Zoro");
        nama.add("sanji");
    }
}